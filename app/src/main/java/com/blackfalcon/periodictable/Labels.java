package com.blackfalcon.periodictable;

/** Localized names for categories, states and modes. */
public class Labels {
    public static String cat(Store st, int c) {
        switch (c) {
            case 0: return st.tr("قلوی دھات", "Alkali metal", "فلز قلوي");
            case 1: return st.tr("قلوی ارضی دھات", "Alkaline earth metal", "فلز قلوي ترابي");
            case 2: return st.tr("عبوری دھات", "Transition metal", "فلز انتقالي");
            case 3: return st.tr("بعد از عبوری دھات", "Post-transition metal", "فلز ما بعد الانتقالي");
            case 4: return st.tr("نیم دھات", "Metalloid", "شبه فلز");
            case 5: return st.tr("غیر دھات", "Nonmetal", "لا فلز");
            case 6: return st.tr("ہیلوجن", "Halogen", "هالوجين");
            case 7: return st.tr("نجیب گیس", "Noble gas", "غاز نبيل");
            case 8: return st.tr("لینتھینائڈ", "Lanthanide", "لانثانيدات");
            case 9: return st.tr("ایکٹینائڈ", "Actinide", "أكتينيدات");
            default: return st.tr("نامعلوم خواص", "Unknown properties", "خصائص غير معروفة");
        }
    }

    public static String state(Store st, char s) {
        switch (s) {
            case 's': return st.tr("ٹھوس", "Solid", "صلب");
            case 'l': return st.tr("مائع", "Liquid", "سائل");
            case 'g': return st.tr("گیس", "Gas", "غاز");
            default: return st.tr("نامعلوم / متوقع", "Unknown / predicted", "غير معروف / متوقع");
        }
    }

    public static String mode(Store st, int m) {
        switch (m) {
            case 1: return st.tr("حالت", "State", "الحالة");
            case 2: return st.tr("بلاک", "Block", "الكتلة");
            case 3: return st.tr("برقی منفیت", "Electronegativity", "السالبية الكهربية");
            default: return st.tr("زمرہ", "Category", "الفئة");
        }
    }
}
