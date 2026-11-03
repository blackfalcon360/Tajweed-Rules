package com.blackfalcon.periodictable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

/** Animated Bohr-style atom: nucleus and electron shells with moving electrons. */
public class BohrView extends View {
    int[] shells = new int[0];
    int color = 0xFF42A5F5;
    String label = "";
    boolean dark = true;
    final long t0 = System.nanoTime();
    final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    final float d;

    public BohrView(Context c) {
        super(c);
        d = c.getResources().getDisplayMetrics().density;
    }

    public void set(int[] shells, int color, String label, boolean dark) {
        this.shells = shells;
        this.color = color;
        this.label = label;
        this.dark = dark;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas c) {
        int w = getWidth(), h = getHeight();
        float cx = w / 2f, cy = h / 2f;
        float maxR = Math.min(w, h) / 2f - 8 * d;
        int n = Math.max(1, shells.length);
        float nucR = maxR * 0.17f;
        float step = (maxR - nucR * 1.25f) / n;
        double t = (System.nanoTime() - t0) / 1e9;

        // shells
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1.2f * d);
        p.setColor(dark ? 0x55FFFFFF : 0x55000000);
        for (int i = 0; i < shells.length; i++) {
            c.drawCircle(cx, cy, nucR * 1.25f + step * (i + 1) - step * 0.5f, p);
        }
        // electrons
        p.setStyle(Paint.Style.FILL);
        for (int i = 0; i < shells.length; i++) {
            float r = nucR * 1.25f + step * (i + 1) - step * 0.5f;
            int cnt = shells[i];
            double speed = (i % 2 == 0 ? 1 : -1) * 0.9 / (i + 1);
            for (int k = 0; k < cnt; k++) {
                double ang = 2 * Math.PI * k / cnt + t * speed;
                float ex = cx + (float) (r * Math.cos(ang));
                float ey = cy + (float) (r * Math.sin(ang));
                p.setColor(0xFFFFFFFF);
                c.drawCircle(ex, ey, 4.4f * d, p);
                p.setColor(color);
                c.drawCircle(ex, ey, 3.2f * d, p);
            }
        }
        // nucleus
        p.setColor(color);
        c.drawCircle(cx, cy, nucR, p);
        p.setColor(Pal.textOn(color));
        p.setTextAlign(Paint.Align.CENTER);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        p.setTextSize(nucR * 0.9f);
        c.drawText(label, cx, cy + nucR * 0.32f, p);
        postInvalidateOnAnimation();
    }
}
