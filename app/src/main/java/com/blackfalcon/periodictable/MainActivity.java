package com.blackfalcon.periodictable;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.*;
import android.text.Editable;
import android.text.TextWatcher;
import java.util.*;

public class MainActivity extends Activity {
    Store st;
    LinearLayout root, content, chips;
    EditText search;
    String category = "All";
    int bg, panel, fg, muted, accent = 0xFF26A69A;
    ArrayList<Rule> rules = new ArrayList<>();

    static class Rule {
        String cat, title, ur, ar, desc, urDesc, arDesc, example, note;
        Rule(String c,String t,String u,String a,String d,String ud,String ad,String ex,String n){
            cat=c; title=t; ur=u; ar=a; desc=d; urDesc=ud; arDesc=ad; example=ex; note=n;
        }
    }

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        st = new Store(this);
        loadRules();
        build();
    }

    String tr(String en,String ur,String ar) {
        String l=st.lang();
        return l.equals("ur")?ur:(l.equals("ar")?ar:en);
    }
    void colors() {
        boolean dark=st.dark();
        bg=dark?0xFF101719:0xFFF3F6F5;
        panel=dark?0xFF1B2729:Color.WHITE;
        fg=dark?0xFFF1F5F4:0xFF172322;
        muted=dark?0xFFB0BFBC:0xFF60716D;
    }
    int dp(float v){return (int)(v*getResources().getDisplayMetrics().density+.5f);}
    GradientDrawable shape(int color,int radius,int stroke){
        GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(dp(radius));
        if(stroke!=0)d.setStroke(dp(1),stroke); return d;
    }
    TextView text(String s,int size,int color,boolean bold){
        TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(color);
        if(bold)t.setTypeface(Typeface.DEFAULT,Typeface.BOLD); return t;
    }
    LinearLayout column(){LinearLayout l=new LinearLayout(this);l.setOrientation(1);return l;}
    LinearLayout row(){LinearLayout l=new LinearLayout(this);l.setOrientation(0);l.setGravity(Gravity.CENTER_VERTICAL);return l;}
    void loadRules(){
        add("Foundations","Makharij al-Huruf","مَخَارِجُ الْحُرُوف","مَخَارِجُ الْحُرُوف",
          "The articulation points: where each Arabic letter originates in the mouth or throat. The five main areas are the oral cavity, throat, tongue, lips and nasal passage.",
          "حروف کی ادائیگی کے مقامات۔ بنیادی طور پر پانچ مقامات ہیں: جوف، حلق، زبان، ہونٹ اور ناک۔",
          "مواضع خروج الحروف من الفم أو الحلق. وهي خمسة مواضع رئيسية: الجوف والحلق واللسان والشفتان والخيشوم.",
          "ء هـ ع ح غ خ | ق ك | ج ش ي | ف ب م و | ن م","Learn each letter's correct point of articulation.");
        add("Foundations","Sifat al-Huruf","صِفَاتُ الْحُرُوف","صِفَاتُ الْحُرُوف",
          "Characteristics of letters, such as breathiness (Hams), strength (Shiddah), elevation (Isti'la) and softness (Rikhwah).",
          "حروف کی صفات، مثلاً ہمس، شدت، استعلاء اور رخاوت۔",
          "صفات الحروف كالجهر والهمس والشدة والرخاوة والاستعلاء والاستفال.",
          "خُصَّ ضَغْطٍ قِظْ","Study the paired and unpaired characteristics with a qualified teacher.");
        add("Noon & Tanween","Izhar Halqi","إِظْهَارٌ حَلْقِيّ","إِظْهَارٌ حَلْقِيّ",
          "Pronounce noon sakinah or tanween clearly when followed by one of the six throat letters: ء ه ع ح غ خ.",
          "نون ساکن یا تنوین کے بعد حلق کے چھ حروف آئیں تو نون واضح پڑھیں۔",
          "إظهار النون الساكنة أو التنوين إذا جاء بعدها أحد حروف الحلق الستة.",
          "مِنْ هَادٍ  |  عَلِيمٌ حَكِيمٌ","No merging and no hidden nasal sound.");
        add("Noon & Tanween","Idgham with Ghunnah","إِدْغَامٌ بِغُنَّة","إِدْغَامٌ بِغُنَّة",
          "Merge noon sakinah or tanween into ي ن م و with a nasal sound held for approximately two counts.",
          "نون ساکن یا تنوین کے بعد ی، ن، م، و آئے تو غنہ کے ساتھ ادغام کریں۔",
          "إدخال النون الساكنة أو التنوين في الياء والنون والميم والواو مع غنة مقدارها حركتان.",
          "مَنْ يَعْمَلْ  |  مِنْ مَالٍ","Remember the letters يَنْمُو.");
        add("Noon & Tanween","Idgham without Ghunnah","إِدْغَامٌ بِغَيْرِ غُنَّة","إِدْغَامٌ بِغَيْرِ غُنَّة",
          "Merge noon sakinah or tanween into ل or ر without nasalisation.",
          "نون ساکن یا تنوین کے بعد ل یا ر آئے تو بغیر غنہ ادغام کریں۔",
          "إدغام النون الساكنة أو التنوين في اللام أو الراء من غير غنة.",
          "مِنْ رَبِّهِمْ  |  هُدًى لِلْمُتَّقِينَ","Do not prolong the nasal sound.");
        add("Noon & Tanween","Iqlab","إِقْلَاب","إِقْلَاب",
          "Change noon sakinah or tanween into a hidden meem sound with ghunnah when followed by ب.",
          "نون ساکن یا تنوین کے بعد ب آئے تو مخفی میم میں بدل کر غنہ کریں۔",
          "قلب النون الساكنة أو التنوين ميماً مخفاة مع الغنة عند الباء.",
          "مِنْ بَعْدِ  |  سَمِيعٌ بَصِيرٌ","Look for the small م sign in many Mushafs.");
        add("Noon & Tanween","Ikhfa Haqiqi","إِخْفَاءٌ حَقِيقِيّ","إِخْفَاءٌ حَقِيقِيّ",
          "Conceal noon sakinah or tanween with ghunnah when followed by one of the remaining 15 letters.",
          "باقی پندرہ حروف میں سے کوئی حرف آئے تو نون کو غنہ کے ساتھ چھپا کر پڑھیں۔",
          "إخفاء النون الساكنة أو التنوين عند أحد الحروف الخمسة عشر مع الغنة.",
          "مِنْ قَبْلُ  |  أَنْفُسَكُمْ","The ghunnah is generally held for two counts.");
        add("Meem Sakinah","Ikhfa Shafawi","إِخْفَاءٌ شَفَوِيّ","إِخْفَاءٌ شَفَوِيّ",
          "When meem sakinah is followed by ب, conceal the meem with a light ghunnah. The lips come together gently.",
          "میم ساکن کے بعد ب آئے تو ہونٹ نرمی سے ملا کر غنہ کے ساتھ اخفا کریں۔",
          "إخفاء الميم الساكنة عند الباء مع غنة، مع إطباق الشفتين برفق.",
          "تَرْمِيهِمْ بِحِجَارَةٍ","Both letters are pronounced using the lips.");
        add("Meem Sakinah","Idgham Shafawi","إِدْغَامٌ شَفَوِيّ","إِدْغَامٌ شَفَوِيّ",
          "When meem sakinah is followed by another م, merge them into one stressed meem with ghunnah.",
          "میم ساکن کے بعد دوسرا میم آئے تو دونوں کو غنہ کے ساتھ ملا دیں۔",
          "إدغام الميم الساكنة في ميم بعدها مع الغنة.",
          "لَهُمْ مَا يَشَاءُونَ","Also called Idgham Mithlayn Saghir.");
        add("Meem Sakinah","Izhar Shafawi","إِظْهَارٌ شَفَوِيّ","إِظْهَارٌ شَفَوِيّ",
          "Pronounce meem sakinah clearly when followed by any letter other than ب or م. Take special care before ف and و.",
          "ب اور م کے علاوہ کسی حرف سے پہلے میم ساکن کو واضح پڑھیں، خصوصاً ف اور و سے پہلے۔",
          "إظهار الميم الساكنة عند سائر الحروف غير الباء والميم، مع الانتباه للفاء والواو.",
          "أَنْعَمْتَ عَلَيْهِمْ","Keep the lips naturally closed for meem.");
        add("Ghunnah","Ghunnah","غُنَّة","غُنَّة",
          "A nasal sound produced from the nasal passage. It is most apparent in نّ and مّ and is commonly held for two counts.",
          "ناک سے نکلنے والی آواز غنہ کہلاتی ہے۔ نّ اور مّ میں دو حرکات کے برابر ہوتی ہے۔",
          "صوت يخرج من الخيشوم، ويظهر بوضوح في النون والميم المشددتين بمقدار حركتين.",
          "إِنَّ  |  ثُمَّ","Do not confuse ghunnah with simply stretching a vowel.");
        add("Qalqalah","Qalqalah","قَلْقَلَة","قَلْقَلَة",
          "A slight echoing/bouncing sound when one of ق ط ب ج د is sakin. The five letters are remembered as قُطْبُ جَدٍّ.",
          "ق، ط، ب، ج، د ساکن ہوں تو ہلکی سی گونج پیدا کریں۔",
          "اضطراب الصوت عند النطق بحرف من قطب جد إذا كان ساكناً.",
          "يَجْعَلُونَ  |  أَحَدْ","Do not add a full vowel after the qalqalah letter.");
        add("Madd","Madd Tabii","مَدٌّ طَبِيعِيّ","مَدٌّ طَبِيعِيّ",
          "The natural elongation. A madd letter ا و ي is prolonged for two counts when no hamzah or sukoon follows in the same word.",
          "مد کے حروف ا، و، ي کو بنیادی طور پر دو حرکات کھینچنا مد طبیعی ہے۔",
          "المد الأصلي الذي لا تقوم ذات حرف المد إلا به، ومقداره حركتان.",
          "قَالَ  |  يَقُولُ  |  قِيلَ","The standard basic length is two counts.");
        add("Madd","Madd Muttasil","مَدٌّ مُتَّصِل","مَدٌّ مُتَّصِل",
          "A madd letter followed by hamzah in the same word. In the common Hafs narration it is usually prolonged four or five counts.",
          "ایک ہی لفظ میں حرف مد کے بعد ہمزہ آئے تو مد متصل ہے؛ حفص میں عموماً چار یا پانچ حرکات۔",
          "أن يأتي بعد حرف المد همز في الكلمة نفسها، ويمد غالباً أربعاً أو خمس حركات في رواية حفص.",
          "جَاءَ  |  السَّمَاءِ","Follow the recitation tradition and teacher used in your Mushaf.");
        add("Madd","Madd Munfasil","مَدٌّ مُنْفَصِل","مَدٌّ مُنْفَصِل",
          "A madd letter at the end of one word followed by hamzah at the start of the next. Its length varies by transmission; commonly four or five counts in Hafs by the Shatibiyyah route.",
          "ایک لفظ کے آخر میں حرف مد اور اگلے لفظ کے شروع میں ہمزہ ہو تو مد منفصل ہے۔ مقدار روایت کے مطابق بدل سکتی ہے۔",
          "أن يقع حرف المد في آخر كلمة والهمز في أول الكلمة التالية، ومقداره يختلف باختلاف طريق الرواية.",
          "يَا أَيُّهَا  |  إِنَّا أَعْطَيْنَاكَ","Use one consistent recitation route.");
        add("Madd","Madd Lazim","مَدٌّ لَازِم","مَدٌّ لَازِم",
          "A madd letter followed by an original, permanent sukoon. It is prolonged for six counts.",
          "حرف مد کے بعد اصلی ساکن حرف آئے تو مد لازم ہے، اسے چھ حرکات کھینچتے ہیں۔",
          "أن يأتي بعد حرف المد سكون أصلي ثابت وصلاً ووقفاً، ويمد ست حركات.",
          "الضَّالِّينَ  |  آلآنَ","It occurs in words and in certain opening letters of surahs.");
        add("Lam & Ra","Rules of Lam in Allah","لَامُ لَفْظِ الْجَلَالَة","لَامُ لَفْظِ الْجَلَالَة",
          "The lam in the name Allah is heavy (Tafkhim) after fathah or dammah, and light (Tarqiq) after kasrah.",
          "لفظ اللہ کا لام زبر یا پیش کے بعد موٹا، جبکہ زیر کے بعد باریک پڑھا جاتا ہے۔",
          "تفخم لام لفظ الجلالة بعد الفتح والضم، وترقق بعد الكسر.",
          "قَالَ اللَّهُ  |  بِسْمِ اللَّهِ","This rule concerns the lam in the divine name.");
        add("Lam & Ra","Rules of Ra","أَحْكَامُ الرَّاء","أَحْكَامُ الرَّاء",
          "Ra may be heavy (Tafkhim) or light (Tarqiq) depending on its vowel and surrounding letters. There are detailed cases and exceptions.",
          "راء بعض حالتوں میں موٹی اور بعض میں باریک پڑھی جاتی ہے؛ اس کے تفصیلی قواعد ہیں۔",
          "تفخم الراء أو ترقق بحسب حركتها وما قبلها وما بعدها، ولها حالات وتفصيلات.",
          "رَبِّ  |  فِرْعَوْنَ","Study the full conditions with a teacher; this is a summary.");
        add("Stopping","Waqf & Ibtida","الْوَقْفُ وَالِابْتِدَاء","الْوَقْفُ وَالِابْتِدَاء",
          "Waqf means stopping during recitation; Ibtida means beginning again. Stop signs and meaning help determine suitable places.",
          "وقف تلاوت کے دوران رکنے اور ابتدا دوبارہ شروع کرنے کو کہتے ہیں۔ علامات اور معنی کا خیال رکھیں۔",
          "الوقف قطع الصوت زمناً للتنفس، والابتداء استئناف القراءة، مع مراعاة المعنى وعلامات المصحف.",
          "م  لا  ج  صلى  قلى","Do not stop or restart in a way that changes the intended meaning.");
        add("Recitation"," isti'adhah & Basmalah","الِاسْتِعَاذَةُ وَالْبَسْمَلَة","الاستعاذة والبسملة",
          "Before recitation, seek refuge with Allah. The basmalah is read at the beginning of surahs, with the well-known exception of Surah At-Tawbah.",
          "تلاوت سے پہلے استعاذہ پڑھیں۔ سورتوں کے آغاز میں بسم اللہ پڑھی جاتی ہے، سوائے سورۃ التوبہ کے۔",
          "يستحب الاستعاذة قبل القراءة، وتقرأ البسملة في أوائل السور عدا سورة التوبة.",
          "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِ\nبِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ","Learn the recognised ways of joining or separating them.");
    }
    void add(String c,String t,String u,String a,String d,String ud,String ad,String ex,String n){
        rules.add(new Rule(c,t,u,a,d,ud,ad,ex,n));
    }

    void build(){
        colors();
        root=column(); root.setBackgroundColor(bg); root.setPadding(dp(16),dp(12),dp(16),dp(8));
        setContentView(root);
        LinearLayout header=row();
        LinearLayout titles=column();
        titles.addView(text("TAJWEED",12,accent,true));
        titles.addView(text(tr("Rules of Qur'anic Recitation","قرآن کی تجوید کے قواعد","أحكام تجويد القرآن"),22,fg,true));
        header.addView(titles,new LinearLayout.LayoutParams(0,-2,1));
        TextView settings=text("⚙",26,fg,true); settings.setGravity(Gravity.CENTER); settings.setPadding(dp(12),dp(5),dp(12),dp(5));
        settings.setOnClickListener(v->settings());header.addView(settings);
        root.addView(header);
        TextView sub=text(tr("Learn • Explore • Practise","سیکھیں • سمجھیں • مشق کریں","تعلّم • اكتشف • تدرّب"),13,muted,false);
        sub.setPadding(0,dp(4),0,dp(12));root.addView(sub);
        search=new EditText(this); search.setSingleLine(true);search.setTextSize(15);search.setTextColor(fg);
        search.setHintTextColor(muted);search.setHint(tr("Search Tajweed rules...","قواعد تلاش کریں...","ابحث عن أحكام التجويد..."));
        search.setPadding(dp(14),0,dp(14),0);search.setBackground(shape(panel,14,0));
        root.addView(search,new LinearLayout.LayoutParams(-1,dp(48)));
        HorizontalScrollView hsv=new HorizontalScrollView(this);hsv.setHorizontalScrollBarEnabled(false);
        chips=row();hsv.addView(chips); LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(-1,dp(48));cp.topMargin=dp(8);root.addView(hsv,cp);
        ScrollView scroll=new ScrollView(this); content=column();scroll.addView(content);
        LinearLayout.LayoutParams sp=new LinearLayout.LayoutParams(-1,0,1);sp.topMargin=dp(4);root.addView(scroll,sp);
        search.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int st,int c,int a){}public void onTextChanged(CharSequence s,int st,int before,int count){render();}public void afterTextChanged(Editable e){}});
        render();
    }

    void render(){
        if(content==null)return;
        content.removeAllViews(); chips.removeAllViews();
        LinkedHashSet<String> cats=new LinkedHashSet<>();cats.add("All");for(Rule r:rules)cats.add(r.cat);
        for(String c:cats){
            TextView chip=text(tr(c,catUr(c),catAr(c)),12,c.equals(category)?Color.WHITE:fg,true);
            chip.setPadding(dp(14),dp(8),dp(14),dp(8));chip.setBackground(shape(c.equals(category)?accent:panel,18,0));
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-2,-2);lp.rightMargin=dp(7);chips.addView(chip,lp);
            chip.setOnClickListener(v->{category=c;render();});
        }
        String q=search==null?"":search.getText().toString().toLowerCase(Locale.ROOT);
        int count=0;
        for(int i=0;i<rules.size();i++){
            Rule r=rules.get(i);
            if(!category.equals("All")&&!category.equals(r.cat))continue;
            if(!q.isEmpty()&&!(r.title.toLowerCase().contains(q)||r.ur.contains(q)||r.ar.contains(q)||r.cat.toLowerCase().contains(q)||r.example.toLowerCase().contains(q)))continue;
            content.addView(ruleCard(r,i));count++;
        }
        if(count==0){TextView empty=text(tr("No rules found.","کوئی قاعدہ نہیں ملا۔","لم يتم العثور على أحكام."),15,muted,false);empty.setPadding(dp(8),dp(30),dp(8),dp(20));content.addView(empty);}
        TextView footer=text(tr(count+" rules shown • Offline learning guide","دکھائے گئے قواعد: "+count+" • آف لائن رہنما","الأحكام المعروضة: "+count+" • دليل يعمل دون إنترنت"),11,muted,false);
        footer.setGravity(Gravity.CENTER);footer.setPadding(0,dp(16),0,dp(18));content.addView(footer);
    }
    View ruleCard(Rule r,int index){
        LinearLayout card=column();card.setPadding(dp(15),dp(13),dp(15),dp(13));card.setBackground(shape(panel,16,st.dark()?0:0xFFE1E9E6));
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2);p.bottomMargin=dp(9);card.setLayoutParams(p);
        TextView categoryText=text(tr(r.cat,catUr(r.cat),catAr(r.cat)).toUpperCase(),10,accent,true);card.addView(categoryText);
        LinearLayout line=row();TextView title=text(r.title,17,fg,true);line.addView(title,new LinearLayout.LayoutParams(0,-2,1));
        TextView arrow=text("›",26,muted,true);line.addView(arrow);card.addView(line);
        TextView arabic=text(r.ar,23,fg,true);arabic.setGravity(Gravity.RIGHT);arabic.setPadding(0,dp(6),0,dp(4));card.addView(arabic);
        TextView desc=text(st.lang().equals("ur")?r.urDesc:st.lang().equals("ar")?r.arDesc:r.desc,13,muted,false);desc.setMaxLines(2);card.addView(desc);
        card.setOnClickListener(v->detail(r));return card;
    }
    void detail(Rule r){
        String desc=st.lang().equals("ur")?r.urDesc:st.lang().equals("ar")?r.arDesc:r.desc;
        StringBuilder b=new StringBuilder();
        b.append(r.ar).append("\n\n").append(desc).append("\n\n");
        b.append(tr("Examples","مثالیں","أمثلة")).append("\n").append(r.example).append("\n\n");
        b.append(tr("Learning note","یاد رکھنے کی بات","ملاحظة تعليمية")).append("\n").append(r.note);
        TextView body=text(b.toString(),16,fg,false);body.setPadding(dp(20),dp(12),dp(20),dp(12));
        body.setTextIsSelectable(true);
        new AlertDialog.Builder(this).setTitle(r.title).setView(body).setPositiveButton(tr("Close","بند کریں","إغلاق"),null).show();
    }
    String catUr(String c){String[] e={"All","Foundations","Noon & Tanween","Meem Sakinah","Ghunnah","Qalqalah","Madd","Lam & Ra","Stopping","Recitation"};String[] u={"تمام","بنیادی قواعد","نون ساکن اور تنوین","میم ساکن","غنہ","قلقلہ","مد","لام اور راء","وقف","تلاوت"};for(int i=0;i<e.length;i++)if(c.equals(e[i]))return u[i];return c;}
    String catAr(String c){String[] e={"All","Foundations","Noon & Tanween","Meem Sakinah","Ghunnah","Qalqalah","Madd","Lam & Ra","Stopping","Recitation"};String[] a={"الكل","المقدمات","النون الساكنة والتنوين","الميم الساكنة","الغنة","القلقلة","المد","اللام والراء","الوقف","التلاوة"};for(int i=0;i<e.length;i++)if(c.equals(e[i]))return a[i];return c;}
    void settings(){
        String[] langs={"English","اردو","العربية"};
        String[] vals={"en","ur","ar"};
        new AlertDialog.Builder(this).setTitle(tr("Settings","ترتیبات","الإعدادات"))
          .setItems(new String[]{tr("Language: ","زبان: ","اللغة: ")+langs[st.lang().equals("en")?0:st.lang().equals("ur")?1:2],
            tr("Switch light / dark mode","لائٹ / ڈارک موڈ تبدیل کریں","تبديل الوضع الفاتح / الداكن")},(d,w)->{
                if(w==0){new AlertDialog.Builder(this).setTitle(tr("Choose language","زبان منتخب کریں","اختر اللغة")).setSingleChoiceItems(langs,st.lang().equals("en")?0:st.lang().equals("ur")?1:2,(x,k)->{st.setLang(vals[k]);x.dismiss();build();}).show();}
                else{st.setDark(!st.dark());build();}
            }).show();
    }
    @Override public void onBackPressed(){new AlertDialog.Builder(this).setMessage(tr("Exit Tajweed Rules?","ایپ بند کرنا چاہتے ہیں؟","هل تريد الخروج؟")).setNegativeButton(tr("Cancel","منسوخ","إلغاء"),null).setPositiveButton(tr("Exit","بند کریں","خروج"),(d,w)->finish()).show();}
}
