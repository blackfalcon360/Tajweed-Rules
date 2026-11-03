package com.blackfalcon.periodictable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/** One element: big tile, animated atom, all properties; previous / next buttons. */
public class DetailActivity extends Activity {
    Store st;
    int z = 1;
    int cBg, cCard, cText, cSub;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        st = new Store(this);
        z = Math.max(1, Math.min(Elements.N, getIntent().getIntExtra("z", 1)));
        boolean dark = st.dark();
        cBg = dark ? 0xFF000000 : 0xFFF2F2F2;
        cCard = dark ? 0xFF1A1A1A : 0xFFFFFFFF;
        cText = dark ? 0xFFFFFFFF : 0xFF111111;
        cSub = dark ? 0xFFAAAAAA : 0xFF555555;
        build();
    }

    String tr(String ur, String en, String ar) { return st.tr(ur, en, ar); }

    int dp(int v) { return (int) (v * getResources().getDisplayMetrics().density + 0.5f); }

    GradientDrawable round(int color, int r) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(dp(r));
        return g;
    }

    TextView tv(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT_BOLD);
        return t;
    }

    LinearLayout cardBox() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setBackground(round(cCard, 16));
        c.setPadding(dp(16), dp(12), dp(16), dp(12));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(dp(10), dp(6), dp(10), dp(6));
        c.setLayoutParams(lp);
        return c;
    }

    LinearLayout row(String label, String value) {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.HORIZONTAL);
        r.setPadding(0, dp(7), 0, dp(7));
        TextView l = tv(label, 14, cSub, false);
        TextView v = tv(value, 15, cText, true);
        v.setGravity(Gravity.END);
        r.addView(l, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.3f);
        r.addView(v, vp);
        return r;
    }

    TextView navBtn(String s, final int target) {
        TextView t = tv(s, 15, Color.WHITE, true);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(8), dp(12), dp(8), dp(12));
        t.setBackground(round(target < 1 || target > Elements.N ? 0xFF455A64 : 0xFF1E88E5, 12));
        t.setOnClickListener(v -> {
            if (target >= 1 && target <= Elements.N) { z = target; build(); }
        });
        return t;
    }

    void build() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(cBg);
        root.setLayoutDirection(st.rtl() ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);

        // top bar
        LinearLayout bar = new LinearLayout(this);
        bar.setOrientation(LinearLayout.HORIZONTAL);
        bar.setGravity(Gravity.CENTER_VERTICAL);
        bar.setPadding(dp(8), dp(10), dp(8), dp(6));
        TextView back = tv(st.rtl() ? "\u2192" : "\u2190", 24, cText, true);
        back.setPadding(dp(12), dp(4), dp(12), dp(4));
        back.setOnClickListener(v -> finish());
        bar.addView(back);
        bar.addView(tv(tr("عنصر", "Element", "العنصر"), 20, cText, true));
        root.addView(bar);

        scroll = new ScrollView(this);
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(0, 0, 0, dp(12));
        scroll.addView(box);
        root.addView(scroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        int cat = Elements.cat(z);
        int col = Pal.CAT[cat];
        int fg = Pal.textOn(col);

        // header card: tile + names
        LinearLayout head = cardBox();
        head.setOrientation(LinearLayout.HORIZONTAL);
        head.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout tile = new LinearLayout(this);
        tile.setOrientation(LinearLayout.VERTICAL);
        tile.setGravity(Gravity.CENTER);
        tile.setBackground(round(col, 14));
        TextView n = tv(String.valueOf(z), 13, fg, false);
        TextView sym = tv(Elements.SYM[z], 38, fg, true);
        TextView ms = tv(Elements.mass(z), 12, fg, false);
        tile.addView(n);
        tile.addView(sym);
        tile.addView(ms);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(dp(104), dp(104));
        tp.setMargins(0, 0, dp(16), 0);
        head.addView(tile, tp);
        LinearLayout names = new LinearLayout(this);
        names.setOrientation(LinearLayout.VERTICAL);
        names.addView(tv(Elements.name(z, st.lang()), 24, cText, true));
        names.addView(tv(Elements.EN[z], 14, cSub, false));
        names.addView(tv(Elements.UR[z], 14, cSub, false));
        names.addView(tv(Elements.AR[z], 14, cSub, false));
        TextView chip = tv(Labels.cat(st, cat), 12, fg, true);
        chip.setPadding(dp(10), dp(4), dp(10), dp(4));
        chip.setBackground(round(col, 12));
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cp.setMargins(0, dp(8), 0, 0);
        names.addView(chip, cp);
        head.addView(names, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        box.addView(head);

        // atom
        LinearLayout atom = cardBox();
        BohrView bv = new BohrView(this);
        bv.set(Elements.shells(z), col, String.valueOf(z), st.dark());
        atom.addView(bv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(250)));
        StringBuilder sh = new StringBuilder();
        for (int e : Elements.shells(z)) {
            if (sh.length() > 0) sh.append(" \u2022 ");
            sh.append(e);
        }
        TextView shl = tv(tr("ہر خول میں الیکٹران: ", "Electrons per shell: ", "الإلكترونات في كل غلاف: ") + sh,
                13, cSub, false);
        shl.setGravity(Gravity.CENTER);
        atom.addView(shl);
        box.addView(atom);

        // properties
        LinearLayout pr = cardBox();
        pr.addView(row(tr("ایٹمی نمبر", "Atomic number", "العدد الذري"), String.valueOf(z)));
        pr.addView(row(tr("ایٹمی کمیت (u)", "Atomic mass (u)", "الكتلة الذرية (u)"), Elements.mass(z)));
        pr.addView(row(tr("زمرہ", "Category", "الفئة"), Labels.cat(st, cat)));
        int g = Elements.group(z);
        pr.addView(row(tr("گروپ", "Group", "المجموعة"), g == 0 ? "\u2014" : String.valueOf(g)));
        pr.addView(row(tr("پیریڈ", "Period", "الدورة"), String.valueOf(Elements.period(z))));
        pr.addView(row(tr("بلاک", "Block", "الكتلة"), String.valueOf(Elements.block(z))));
        pr.addView(row(tr("کمرے کے درجہ حرارت پر حالت", "State at room temperature", "الحالة في حرارة الغرفة"),
                Labels.state(st, Elements.STATE[z])));
        pr.addView(row(tr("نقطۂ پگھلاؤ", "Melting point", "درجة الانصهار"), Elements.temp(Elements.MP[z], st.unit())));
        pr.addView(row(tr("نقطۂ ابال", "Boiling point", "درجة الغليان"), Elements.temp(Elements.BP[z], st.unit())));
        pr.addView(row(tr("کثافت", "Density", "الكثافة"), Elements.density(z)));
        double en = Elements.ENEG[z];
        pr.addView(row(tr("برقی منفیت (Pauling)", "Electronegativity (Pauling)", "السالبية الكهربية (Pauling)"),
                Double.isNaN(en) ? "\u2014" : Elements.trim(en)));
        pr.addView(row(tr("الیکٹران ترتیب", "Electron configuration", "التوزيع الإلكتروني"), Elements.config(z)));
        int y = Elements.YEAR[z];
        pr.addView(row(tr("دریافت", "Discovered", "الاكتشاف"),
                y == 0 ? tr("قدیم زمانے سے", "Known since antiquity", "معروف منذ القدم") : String.valueOf(y)));
        box.addView(pr);

        TextView note = tv(tr(
            "اقدار معیاری حوالہ جاتی اعداد ہیں (گول کیے ہوئے)۔ بھاری عناصر (خاص کر 100 کے بعد) کی بیشتر خصوصیات متوقع ہیں، ناپی ہوئی نہیں۔ [ ] میں کمیت سب سے مستحکم معلوم آئسوٹوپ کی ہے۔",
            "Values are standard reference numbers (rounded). Most properties of the heaviest elements (especially above 100) are predicted, not measured. A mass in [ ] is the most stable known isotope.",
            "القيم أرقام مرجعية قياسية (مقرّبة). معظم خصائص العناصر الثقيلة (خاصة فوق 100) متوقعة لا مقيسة. الكتلة بين [ ] هي لأكثر نظير مستقر معروف."),
            11, cSub, false);
        note.setPadding(dp(16), dp(2), dp(16), dp(6));
        box.addView(note);

        // share
        LinearLayout sh2 = cardBox();
        TextView share = tv(tr("شیئر کریں", "Share", "مشاركة"), 15, Color.WHITE, true);
        share.setGravity(Gravity.CENTER);
        share.setPadding(dp(8), dp(12), dp(8), dp(12));
        share.setBackground(round(0xFF455A64, 12));
        share.setOnClickListener(v -> shareText());
        sh2.addView(share);
        box.addView(sh2);

        // previous / next
        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setPadding(dp(10), dp(6), dp(10), dp(10));
        String prevT = z > 1 ? "\u2039  " + Elements.SYM[z - 1] : "\u2039";
        String nextT = z < Elements.N ? Elements.SYM[z + 1] + "  \u203A" : "\u203A";
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        np.setMargins(dp(4), 0, dp(4), 0);
        nav.addView(navBtn(st.rtl() ? nextT : prevT, st.rtl() ? z + 1 : z - 1), np);
        nav.addView(navBtn(st.rtl() ? prevT : nextT, st.rtl() ? z - 1 : z + 1), np);
        root.addView(nav);

        TextView credit = tv("By: Black Falcon \uD83E\uDD85", 13, cSub, false);
        credit.setGravity(Gravity.CENTER);
        credit.setPadding(0, 0, 0, dp(10));
        root.addView(credit);

        setContentView(root);
    }

    void shareText() {
        StringBuilder sb = new StringBuilder();
        sb.append(Elements.EN[z]).append(" (").append(Elements.SYM[z]).append(")  #").append(z).append("\n");
        sb.append("Atomic mass: ").append(Elements.mass(z)).append("\n");
        sb.append("Category: ").append(Labels.cat(new Store(this), Elements.cat(z))).append("\n");
        sb.append("Electron configuration: ").append(Elements.config(z)).append("\n");
        sb.append("Melting point: ").append(Elements.temp(Elements.MP[z], st.unit())).append("\n");
        sb.append("Boiling point: ").append(Elements.temp(Elements.BP[z], st.unit())).append("\n");
        sb.append("Density: ").append(Elements.density(z)).append("\n\nBy: Black Falcon");
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, sb.toString());
        startActivity(Intent.createChooser(i, tr("شیئر کریں", "Share", "مشاركة")));
    }
}
