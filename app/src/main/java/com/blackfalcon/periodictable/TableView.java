package com.blackfalcon.periodictable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import java.util.Locale;

/** The periodic table drawn on a canvas: pinch to zoom, drag, fling, double-tap, tap an element. */
public class TableView extends View {
    public interface Listener {
        void onPick(int z);
        void onMarker(int cat);
    }

    final float cell;
    final float pad;
    final float gap;
    final float contentW, contentH;
    final int[][] grid = new int[10][19];
    final boolean[] match = new boolean[Elements.N + 1];
    final Paint fill = new Paint(Paint.ANTI_ALIAS_FLAG);
    final Paint txt = new Paint(Paint.ANTI_ALIAS_FLAG);
    final RectF rect = new RectF();
    final OverScroller scroller;
    final ScaleGestureDetector sgd;
    final GestureDetector gd;

    float scale = 1f, tx = 0f, ty = 0f, fitScale = 1f;
    boolean inited = false;
    int mode = 0, hlCat = -1;
    boolean dark = true, filtering = false;
    String query = "", title = "", sub = "", hint = "";
    Listener lis;

    public TableView(Context c) {
        super(c);
        cell = 60f * c.getResources().getDisplayMetrics().density;
        pad = cell * 0.25f;
        gap = cell * 0.6f;
        contentW = 18 * cell + 2 * pad;
        contentH = 9 * cell + gap + 2 * pad;
        for (int z = 1; z <= Elements.N; z++) {
            int[] p = Elements.pos(z);
            grid[p[0]][p[1]] = z;
            match[z] = true;
        }
        scroller = new OverScroller(c);
        sgd = new ScaleGestureDetector(c, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override public boolean onScale(ScaleGestureDetector d) {
                zoomTo(scale * d.getScaleFactor(), d.getFocusX(), d.getFocusY());
                return true;
            }
        });
        gd = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onDown(MotionEvent e) {
                scroller.forceFinished(true);
                return true;
            }
            @Override public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
                tx -= dx;
                ty -= dy;
                clampT();
                invalidate();
                return true;
            }
            @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
                scroller.fling((int) tx, (int) ty, (int) vx, (int) vy,
                        (int) minTx(), (int) maxTx(), (int) minTy(), (int) maxTy());
                postInvalidateOnAnimation();
                return true;
            }
            @Override public boolean onDoubleTap(MotionEvent e) {
                if (scale > fitScale * 1.3f) zoomTo(fitScale, e.getX(), e.getY());
                else zoomTo(fitScale * 2.6f, e.getX(), e.getY());
                return true;
            }
            @Override public boolean onSingleTapConfirmed(MotionEvent e) {
                hit(e.getX(), e.getY());
                return true;
            }
        });
    }

    public void setListener(Listener l) { lis = l; }

    public void setTheme(boolean dark) { this.dark = dark; invalidate(); }

    public void setMode(int m) { mode = m; invalidate(); }

    public void setTexts(String title, String sub, String hint) {
        this.title = title; this.sub = sub; this.hint = hint;
        invalidate();
    }

    /** cat = category to highlight (-1 none); q = text search (symbol, name in any language, number). */
    public void setFilter(int cat, String q) {
        hlCat = cat;
        query = q == null ? "" : q.trim().toLowerCase(Locale.ROOT);
        filtering = cat >= 0 || query.length() > 0;
        for (int z = 1; z <= Elements.N; z++) {
            boolean m = true;
            if (cat >= 0 && Elements.cat(z) != cat) m = false;
            if (m && query.length() > 0) {
                m = Elements.SYM[z].toLowerCase(Locale.ROOT).contains(query)
                    || Elements.EN[z].toLowerCase(Locale.ROOT).contains(query)
                    || Elements.UR[z].contains(query)
                    || Elements.AR[z].contains(query)
                    || String.valueOf(z).equals(query);
            }
            match[z] = m;
        }
        invalidate();
    }

    // ---------- geometry ----------
    float minTx() { float w = contentW * scale; return w <= getWidth() ? (getWidth() - w) / 2f : getWidth() - w; }
    float maxTx() { float w = contentW * scale; return w <= getWidth() ? (getWidth() - w) / 2f : 0f; }
    float minTy() { float h = contentH * scale; return h <= getHeight() ? 0f : getHeight() - h; }
    float maxTy() { float h = contentH * scale; return h <= getHeight() ? 0f : 0f; }

    void clampT() {
        tx = Math.max(minTx(), Math.min(maxTx(), tx));
        ty = Math.max(minTy(), Math.min(maxTy(), ty));
    }

    void zoomTo(float ns, float fx, float fy) {
        ns = Math.max(fitScale, Math.min(fitScale * 7f, ns));
        tx = fx - (fx - tx) * ns / scale;
        ty = fy - (fy - ty) * ns / scale;
        scale = ns;
        clampT();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        if (w <= 0) return;
        fitScale = w / contentW;
        if (!inited) {
            scale = fitScale;
            inited = true;
        }
        scale = Math.max(fitScale, scale);
        clampT();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            tx = scroller.getCurrX();
            ty = scroller.getCurrY();
            clampT();
            postInvalidateOnAnimation();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getParent() != null) getParent().requestDisallowInterceptTouchEvent(true);
        sgd.onTouchEvent(ev);
        if (!sgd.isInProgress()) gd.onTouchEvent(ev);
        return true;
    }

    float cellX(int col) { return pad + (col - 1) * cell; }

    float cellY(int row) { return pad + (row - 1) * cell + (row >= 8 ? gap : 0f); }

    void hit(float x, float y) {
        float cx = (x - tx) / scale - pad;
        float cy = (y - ty) / scale - pad;
        if (cx < 0 || cy < 0) return;
        int col = (int) Math.floor(cx / cell) + 1;
        float rf = cy / cell;
        int row;
        if (rf < 7f) row = (int) Math.floor(rf) + 1;
        else if (rf >= 7f + gap / cell) row = (int) Math.floor(rf - gap / cell) + 1;
        else return;
        if (col < 1 || col > 18 || row < 1 || row > 9) return;
        int z = grid[row][col];
        if (z > 0) {
            if (lis != null) lis.onPick(z);
        } else if (col == 3 && (row == 6 || row == 7)) {
            if (lis != null) lis.onMarker(row == 6 ? 8 : 9);
        }
    }

    // ---------- drawing ----------
    @Override
    protected void onDraw(Canvas c) {
        c.drawColor(dark ? 0xFF000000 : 0xFFF2F2F2);
        c.save();
        c.translate(tx, ty);
        c.scale(scale, scale);
        float px = cell * scale;           // on-screen size of one cell
        float inset = cell * 0.03f;
        float rad = cell * 0.12f;
        int emptyText = dark ? 0xFFEEEEEE : 0xFF222222;

        // title in the empty area of the table
        txt.setTextAlign(Paint.Align.LEFT);
        txt.setTypeface(Typeface.DEFAULT_BOLD);
        txt.setColor(emptyText);
        txt.setTextSize(cell * 0.62f);
        c.drawText(title, cellX(3), cellY(1) + cell * 0.75f, txt);
        txt.setTypeface(Typeface.DEFAULT);
        txt.setColor(dark ? 0xFFAAAAAA : 0xFF555555);
        txt.setTextSize(cell * 0.28f);
        c.drawText(sub, cellX(3), cellY(2) + cell * 0.25f, txt);
        c.drawText(hint, cellX(3), cellY(2) + cell * 0.65f, txt);

        for (int z = 1; z <= Elements.N; z++) {
            int[] p = Elements.pos(z);
            float x = cellX(p[1]) + inset, y = cellY(p[0]) + inset, s = cell - 2 * inset;
            int col = Pal.color(mode, z);
            boolean dim = filtering && !match[z];
            int fg = Pal.textOn(col);
            fill.setStyle(Paint.Style.FILL);
            fill.setColor(col);
            fill.setAlpha(dim ? 55 : 255);
            rect.set(x, y, x + s, y + s);
            c.drawRoundRect(rect, rad, rad, fill);
            if (filtering && !dim) {
                fill.setStyle(Paint.Style.STROKE);
                fill.setStrokeWidth(cell * 0.05f);
                fill.setColor(dark ? 0xFFFFFFFF : 0xFF000000);
                c.drawRoundRect(rect, rad, rad, fill);
            }
            int a = dim ? 90 : 255;
            // symbol
            txt.setTextAlign(Paint.Align.CENTER);
            txt.setTypeface(Typeface.DEFAULT_BOLD);
            txt.setColor(fg);
            txt.setAlpha(a);
            txt.setTextSize(cell * 0.42f);
            c.drawText(Elements.SYM[z], x + s / 2f, y + s * 0.62f, txt);
            // atomic number
            if (px >= 26f) {
                txt.setTypeface(Typeface.DEFAULT);
                txt.setTextAlign(Paint.Align.LEFT);
                txt.setTextSize(cell * 0.17f);
                c.drawText(String.valueOf(z), x + cell * 0.07f, y + cell * 0.2f, txt);
            }
            // name (zoomed in) or mass
            if (px >= 52f) {
                txt.setTypeface(Typeface.DEFAULT);
                txt.setTextAlign(Paint.Align.CENTER);
                String bottom = px >= 76f ? Elements.EN[z] : Elements.mass(z);
                float size = cell * (px >= 76f ? 0.15f : 0.16f);
                txt.setTextSize(size);
                while (txt.measureText(bottom) > s * 0.94f && size > cell * 0.07f) {
                    size -= cell * 0.01f;
                    txt.setTextSize(size);
                }
                c.drawText(bottom, x + s / 2f, y + s * 0.9f, txt);
            }
        }
        // markers for lanthanides / actinides
        drawMarker(c, 6, "57\u201371", Pal.CAT[8], inset, rad);
        drawMarker(c, 7, "89\u2013103", Pal.CAT[9], inset, rad);
        c.restore();
    }

    void drawMarker(Canvas c, int row, String label, int color, float inset, float rad) {
        float x = cellX(3) + inset, y = cellY(row) + inset, s = cell - 2 * inset;
        rect.set(x, y, x + s, y + s);
        fill.setStyle(Paint.Style.STROKE);
        fill.setStrokeWidth(cell * 0.04f);
        fill.setColor(color);
        fill.setAlpha(255);
        c.drawRoundRect(rect, rad, rad, fill);
        txt.setTextAlign(Paint.Align.CENTER);
        txt.setTypeface(Typeface.DEFAULT_BOLD);
        txt.setColor(color);
        txt.setAlpha(255);
        txt.setTextSize(cell * 0.2f);
        c.drawText(label, x + s / 2f, y + s * 0.58f, txt);
    }
}
