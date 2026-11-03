package com.blackfalcon.periodictable;

import java.util.Locale;

/**
 * All 118 elements. Pure Java (no Android classes).
 * Data line: Symbol|English|Urdu|Arabic|mass|melting K|boiling K|density g/cm3|electronegativity|year (0 = ancient)|state
 * state: s solid, l liquid, g gas, ? unknown / predicted. "-" = no value.
 */
public class Elements {
    public static final int N = 118;

    static final String[] D = {
        "H|Hydrogen|ہائیڈروجن|هيدروجين|1.008|13.99|20.27|0.00008988|2.20|1766|g",
        "He|Helium|ہیلیم|هيليوم|4.0026|-|4.22|0.0001785|-|1868|g",
        "Li|Lithium|لیتھیم|ليثيوم|6.94|453.65|1603|0.534|0.98|1817|s",
        "Be|Beryllium|بیریلیم|بريليوم|9.0122|1560|2742|1.85|1.57|1798|s",
        "B|Boron|بوران|بورون|10.81|2349|4200|2.34|2.04|1808|s",
        "C|Carbon|کاربن|كربون|12.011|3823|4098|2.267|2.55|0|s",
        "N|Nitrogen|نائٹروجن|نيتروجين|14.007|63.15|77.36|0.0012506|3.04|1772|g",
        "O|Oxygen|آکسیجن|أكسجين|15.999|54.36|90.20|0.001429|3.44|1774|g",
        "F|Fluorine|فلورین|فلور|18.998|53.53|85.03|0.001696|3.98|1886|g",
        "Ne|Neon|نیون|نيون|20.180|24.56|27.07|0.0009|-|1898|g",
        "Na|Sodium|سوڈیم|صوديوم|22.990|370.87|1156|0.971|0.93|1807|s",
        "Mg|Magnesium|میگنیشیم|مغنيسيوم|24.305|923|1363|1.738|1.31|1755|s",
        "Al|Aluminum|ایلومینیم|ألومنيوم|26.982|933.47|2792|2.70|1.61|1825|s",
        "Si|Silicon|سلیکون|سيليكون|28.085|1687|3538|2.329|1.90|1824|s",
        "P|Phosphorus|فاسفورس|فوسفور|30.974|317.3|553.7|1.82|2.19|1669|s",
        "S|Sulfur|سلفر (گندھک)|كبريت|32.06|388.36|717.87|2.067|2.58|0|s",
        "Cl|Chlorine|کلورین|كلور|35.45|171.6|239.11|0.003214|3.16|1774|g",
        "Ar|Argon|آرگون|أرجون|39.948|83.8|87.3|0.001784|-|1894|g",
        "K|Potassium|پوٹاشیم|بوتاسيوم|39.098|336.53|1032|0.862|0.82|1807|s",
        "Ca|Calcium|کیلشیم|كالسيوم|40.078|1115|1757|1.54|1.00|1808|s",
        "Sc|Scandium|سکینڈیم|سكانديوم|44.956|1814|3109|2.989|1.36|1879|s",
        "Ti|Titanium|ٹائٹینیم|تيتانيوم|47.867|1941|3560|4.54|1.54|1791|s",
        "V|Vanadium|وینیڈیم|فاناديوم|50.942|2183|3680|6.11|1.63|1801|s",
        "Cr|Chromium|کرومیم|كروم|51.996|2180|2944|7.15|1.66|1797|s",
        "Mn|Manganese|مینگنیز|منغنيز|54.938|1519|2334|7.44|1.55|1774|s",
        "Fe|Iron|آئرن (لوہا)|حديد|55.845|1811|3134|7.874|1.83|0|s",
        "Co|Cobalt|کوبالٹ|كوبالت|58.933|1768|3200|8.86|1.88|1735|s",
        "Ni|Nickel|نکل|نيكل|58.693|1728|3186|8.912|1.91|1751|s",
        "Cu|Copper|کاپر (تانبا)|نحاس|63.546|1357.77|2835|8.96|1.90|0|s",
        "Zn|Zinc|زنک (جست)|زنك|65.38|692.68|1180|7.134|1.65|1746|s",
        "Ga|Gallium|گیلیم|غاليوم|69.723|302.91|2673|5.907|1.81|1875|s",
        "Ge|Germanium|جرمینیم|جرمانيوم|72.630|1211.4|3106|5.323|2.01|1886|s",
        "As|Arsenic|آرسینک (سنکھیا)|زرنيخ|74.922|1090|887|5.776|2.18|1250|s",
        "Se|Selenium|سیلینیم|سيلينيوم|78.971|494|958|4.809|2.55|1817|s",
        "Br|Bromine|برومین|بروم|79.904|265.8|332|3.122|2.96|1826|l",
        "Kr|Krypton|کرپٹون|كريبتون|83.798|115.79|119.93|0.003749|3.00|1898|g",
        "Rb|Rubidium|روبیڈیم|روبيديوم|85.468|312.46|961|1.532|0.82|1861|s",
        "Sr|Strontium|اسٹرانشیم|سترونتيوم|87.62|1050|1655|2.64|0.95|1790|s",
        "Y|Yttrium|ایٹریم|إيتريوم|88.906|1799|3609|4.469|1.22|1794|s",
        "Zr|Zirconium|زرکونیم|زركونيوم|91.224|2128|4682|6.506|1.33|1789|s",
        "Nb|Niobium|نائیوبیم|نيوبيوم|92.906|2750|5017|8.57|1.6|1801|s",
        "Mo|Molybdenum|مولیبڈینم|موليبدينوم|95.95|2896|4912|10.22|2.16|1778|s",
        "Tc|Technetium|ٹیکنیشیم|تكنيشيوم|98|2430|4538|11.5|1.9|1937|s",
        "Ru|Ruthenium|روتھینیم|روثينيوم|101.07|2607|4423|12.37|2.2|1844|s",
        "Rh|Rhodium|روڈیم|روديوم|102.91|2237|3968|12.41|2.28|1803|s",
        "Pd|Palladium|پیلاڈیم|بالاديوم|106.42|1828.05|3236|12.02|2.20|1803|s",
        "Ag|Silver|سلور (چاندی)|فضة|107.87|1234.93|2435|10.501|1.93|0|s",
        "Cd|Cadmium|کیڈمیم|كادميوم|112.41|594.22|1040|8.69|1.69|1817|s",
        "In|Indium|انڈیم|إنديوم|114.82|429.75|2345|7.31|1.78|1863|s",
        "Sn|Tin|ٹن (قلعی)|قصدير|118.71|505.08|2875|7.287|1.96|0|s",
        "Sb|Antimony|اینٹیمنی|أنتيمون|121.76|903.78|1860|6.685|2.05|0|s",
        "Te|Tellurium|ٹیلوریم|تيلوريوم|127.60|722.66|1261|6.232|2.1|1782|s",
        "I|Iodine|آئیوڈین|يود|126.90|386.85|457.4|4.93|2.66|1811|s",
        "Xe|Xenon|زینان|زينون|131.29|161.4|165.05|0.005894|2.6|1898|g",
        "Cs|Cesium|سیزیم|سيزيوم|132.91|301.59|944|1.873|0.79|1860|s",
        "Ba|Barium|بیریم|باريوم|137.33|1000|2118|3.594|0.89|1808|s",
        "La|Lanthanum|لینتھینم|لانثانوم|138.91|1193|3737|6.145|1.10|1839|s",
        "Ce|Cerium|سیریم|سيريوم|140.12|1068|3716|6.77|1.12|1803|s",
        "Pr|Praseodymium|پراسیوڈیمیم|براسيوديميوم|140.91|1208|3793|6.773|1.13|1885|s",
        "Nd|Neodymium|نیوڈیمیم|نيوديميوم|144.24|1297|3347|7.007|1.14|1885|s",
        "Pm|Promethium|پرومیتھیم|بروميثيوم|145|1315|3273|7.26|1.13|1945|s",
        "Sm|Samarium|سمیریم|ساماريوم|150.36|1345|2067|7.52|1.17|1879|s",
        "Eu|Europium|یوروپیم|يوروبيوم|151.96|1099|1802|5.243|1.2|1901|s",
        "Gd|Gadolinium|گیڈولینیم|غادولينيوم|157.25|1585|3546|7.895|1.2|1880|s",
        "Tb|Terbium|ٹربیم|تيربيوم|158.93|1629|3503|8.229|1.1|1843|s",
        "Dy|Dysprosium|ڈسپروشیم|ديسبروسيوم|162.50|1680|2840|8.55|1.22|1886|s",
        "Ho|Holmium|ہولمیم|هولميوم|164.93|1734|2993|8.795|1.23|1878|s",
        "Er|Erbium|ایربیم|إربيوم|167.26|1802|3141|9.066|1.24|1843|s",
        "Tm|Thulium|تھولیم|ثوليوم|168.93|1818|2223|9.321|1.25|1879|s",
        "Yb|Ytterbium|ایٹربیم|إيتربيوم|173.05|1097|1469|6.965|1.1|1878|s",
        "Lu|Lutetium|لیوٹیشیم|لوتيتيوم|174.97|1925|3675|9.84|1.27|1907|s",
        "Hf|Hafnium|ہیفنیم|هافنيوم|178.49|2506|4876|13.31|1.3|1923|s",
        "Ta|Tantalum|ٹینٹلم|تانتالوم|180.95|3290|5731|16.654|1.5|1802|s",
        "W|Tungsten|ٹنگسٹن|تنغستن|183.84|3695|5828|19.25|2.36|1783|s",
        "Re|Rhenium|رینیم|رينيوم|186.21|3459|5869|21.02|1.9|1925|s",
        "Os|Osmium|اوسمیم|أوزميوم|190.23|3306|5285|22.59|2.2|1803|s",
        "Ir|Iridium|اریڈیم|إيريديوم|192.22|2719|4701|22.56|2.20|1803|s",
        "Pt|Platinum|پلاٹینم|بلاتين|195.08|2041.4|4098|21.45|2.28|1735|s",
        "Au|Gold|گولڈ (سونا)|ذهب|196.97|1337.33|3129|19.282|2.54|0|s",
        "Hg|Mercury|مرکری (پارہ)|زئبق|200.59|234.32|629.88|13.534|2.00|0|l",
        "Tl|Thallium|تھیلیم|ثاليوم|204.38|577|1746|11.85|1.62|1861|s",
        "Pb|Lead|لیڈ (سیسہ)|رصاص|207.2|600.61|2022|11.342|2.33|0|s",
        "Bi|Bismuth|بسمتھ|بزموت|208.98|544.7|1837|9.807|2.02|1753|s",
        "Po|Polonium|پولونیم|بولونيوم|209|527|1235|9.32|2.0|1898|s",
        "At|Astatine|ایسٹاٹین|أستاتين|210|575|-|-|2.2|1940|s",
        "Rn|Radon|ریڈون|رادون|222|202|211.3|0.00973|2.2|1900|g",
        "Fr|Francium|فرانشیم|فرانسيوم|223|-|-|-|0.79|1939|?",
        "Ra|Radium|ریڈیم|راديوم|226|973|2010|5.5|0.9|1898|s",
        "Ac|Actinium|ایکٹینیم|أكتينيوم|227|1500|3500|10.07|1.1|1899|s",
        "Th|Thorium|تھوریم|ثوريوم|232.04|2023|5061|11.72|1.3|1829|s",
        "Pa|Protactinium|پروٹیکٹینیم|بروتكتينيوم|231.04|1841|4300|15.37|1.5|1913|s",
        "U|Uranium|یورینیم|يورانيوم|238.03|1405.3|4404|19.1|1.38|1789|s",
        "Np|Neptunium|نیپچونیم|نبتونيوم|237|917|4273|20.45|1.36|1940|s",
        "Pu|Plutonium|پلوٹونیم|بلوتونيوم|244|912.5|3501|19.816|1.28|1940|s",
        "Am|Americium|امریشیم|أمريسيوم|243|1449|2880|12|1.13|1944|s",
        "Cm|Curium|کیوریم|كوريوم|247|1613|3383|13.51|1.28|1944|s",
        "Bk|Berkelium|برکیلیم|بركليوم|247|1259|-|14.78|1.3|1949|s",
        "Cf|Californium|کیلیفورنیم|كاليفورنيوم|251|1173|-|15.1|1.3|1950|s",
        "Es|Einsteinium|آئن سٹائنیم|أينشتانيوم|252|1133|-|8.84|1.3|1952|s",
        "Fm|Fermium|فرمیم|فرميوم|257|-|-|-|1.3|1952|?",
        "Md|Mendelevium|مینڈیلیویم|مندليفيوم|258|-|-|-|1.3|1955|?",
        "No|Nobelium|نوبیلیم|نوبليوم|259|-|-|-|1.3|1958|?",
        "Lr|Lawrencium|لارینشیم|لورنسيوم|266|-|-|-|1.3|1961|?",
        "Rf|Rutherfordium|ردرفورڈیم|روثرفورديوم|267|-|-|17|-|1964|?",
        "Db|Dubnium|ڈبنیم|دوبنيوم|268|-|-|21.6|-|1968|?",
        "Sg|Seaborgium|سی بورگیم|سيبورغيوم|269|-|-|23.5|-|1974|?",
        "Bh|Bohrium|بوہریم|بوريوم|270|-|-|26.5|-|1981|?",
        "Hs|Hassium|ہیسیم|هاسيوم|277|-|-|28.6|-|1984|?",
        "Mt|Meitnerium|مائٹنیریم|مايتنريوم|278|-|-|27.5|-|1982|?",
        "Ds|Darmstadtium|ڈارمسٹیشیم|دارمشتادتيوم|281|-|-|26.5|-|1994|?",
        "Rg|Roentgenium|رونٹجینیم|رونتجينيوم|282|-|-|23|-|1994|?",
        "Cn|Copernicium|کوپرنیشیم|كوبرنيسيوم|285|-|-|14|-|1996|?",
        "Nh|Nihonium|نیہونیم|نيهونيوم|286|-|-|16|-|2004|?",
        "Fl|Flerovium|فلیروویم|فليروفيوم|289|-|-|9.9|-|1998|?",
        "Mc|Moscovium|ماسکوویم|موسكوفيوم|290|-|-|13.5|-|2003|?",
        "Lv|Livermorium|لیورموریم|ليفرموريوم|293|-|-|12.9|-|2000|?",
        "Ts|Tennessine|ٹینیسین|تينيسين|294|-|-|7.2|-|2010|?",
        "Og|Oganesson|اوگانیسن|أوغانيسون|294|-|-|5.0|-|2002|?"
    };

    // index 1..118 (0 unused)
    public static final String[] SYM = new String[N + 1], EN = new String[N + 1],
        UR = new String[N + 1], AR = new String[N + 1];
    public static final double[] MASS = new double[N + 1], MP = new double[N + 1], BP = new double[N + 1],
        DENS = new double[N + 1], ENEG = new double[N + 1];
    public static final int[] YEAR = new int[N + 1];
    public static final char[] STATE = new char[N + 1];

    static double num(String s) { return s.equals("-") ? Double.NaN : Double.parseDouble(s); }

    static {
        for (int i = 0; i < D.length && i < N; i++) {
            String[] a = D[i].split("\\|");
            int z = i + 1;
            SYM[z] = a[0]; EN[z] = a[1]; UR[z] = a[2]; AR[z] = a[3];
            MASS[z] = num(a[4]); MP[z] = num(a[5]); BP[z] = num(a[6]);
            DENS[z] = num(a[7]); ENEG[z] = num(a[8]);
            YEAR[z] = Integer.parseInt(a[9]);
            STATE[z] = a[10].charAt(0);
        }
    }

    public static String name(int z, String lang) {
        return lang.equals("ur") ? UR[z] : (lang.equals("ar") ? AR[z] : EN[z]);
    }

    // ---------- classification ----------
    /** 0 alkali, 1 alkaline earth, 2 transition, 3 post-transition, 4 metalloid, 5 nonmetal,
     *  6 halogen, 7 noble gas, 8 lanthanide, 9 actinide, 10 unknown properties. */
    public static int cat(int z) {
        if (z >= 57 && z <= 71) return 8;
        if (z >= 89 && z <= 103) return 9;
        if (z >= 109) return 10;
        switch (z) {
            case 3: case 11: case 19: case 37: case 55: case 87: return 0;
            case 4: case 12: case 20: case 38: case 56: case 88: return 1;
            case 5: case 14: case 32: case 33: case 51: case 52: return 4;
            case 1: case 6: case 7: case 8: case 15: case 16: case 34: return 5;
            case 9: case 17: case 35: case 53: case 85: return 6;
            case 2: case 10: case 18: case 36: case 54: case 86: return 7;
            case 13: case 31: case 49: case 50: case 81: case 82: case 83: case 84: return 3;
            default: return 2;
        }
    }

    public static int period(int z) {
        if (z <= 2) return 1;
        if (z <= 10) return 2;
        if (z <= 18) return 3;
        if (z <= 36) return 4;
        if (z <= 54) return 5;
        if (z <= 86) return 6;
        return 7;
    }

    /** Position in the table grid: {row, col}. Rows 1-7 main table, 8 = lanthanides, 9 = actinides. Cols 1-18. */
    public static int[] pos(int z) {
        if (z == 1) return new int[] {1, 1};
        if (z == 2) return new int[] {1, 18};
        if (z <= 4) return new int[] {2, z - 2};
        if (z <= 10) return new int[] {2, z + 8};
        if (z <= 12) return new int[] {3, z - 10};
        if (z <= 18) return new int[] {3, z};
        if (z <= 36) return new int[] {4, z - 18};
        if (z <= 54) return new int[] {5, z - 36};
        if (z <= 56) return new int[] {6, z - 54};
        if (z <= 71) return new int[] {8, z - 57 + 3};
        if (z <= 86) return new int[] {6, z - 72 + 4};
        if (z <= 88) return new int[] {7, z - 86};
        if (z <= 103) return new int[] {9, z - 89 + 3};
        return new int[] {7, z - 104 + 4};
    }

    /** IUPAC group 1-18, or 0 for the lanthanides / actinides. */
    public static int group(int z) {
        int[] p = pos(z);
        return p[0] >= 8 ? 0 : p[1];
    }

    public static char block(int z) {
        int[] p = pos(z);
        if (p[0] >= 8) return 'f';
        if (z == 2 || p[1] <= 2) return 's';
        if (p[1] >= 13) return 'p';
        return 'd';
    }

    // ---------- electrons ----------
    static final String[] ORB = {"1s", "2s", "2p", "3s", "3p", "4s", "3d", "4p", "5s", "4d", "5p",
        "6s", "4f", "5d", "6p", "7s", "5f", "6d", "7p"};
    static final int[] CAP = {2, 2, 6, 2, 6, 2, 10, 6, 2, 10, 6, 2, 14, 10, 6, 2, 14, 10, 6};
    static final int[] ORBN = {1, 2, 2, 3, 3, 4, 3, 4, 5, 4, 5, 6, 4, 5, 6, 7, 5, 6, 7};
    static final int[] ORBL = {0, 0, 1, 0, 1, 0, 2, 1, 0, 2, 1, 0, 3, 2, 1, 0, 3, 2, 1};
    static final int[] NOBLE = {2, 10, 18, 36, 54, 86};
    static final String[] NOBLE_SYM = {"He", "Ne", "Ar", "Kr", "Xe", "Rn"};

    /** Electrons per orbital (Madelung order) including the known exceptions. */
    public static int[] occ(int z) {
        int[] o = new int[19];
        int left = z;
        for (int i = 0; i < 19; i++) {
            int t = Math.min(left, CAP[i]);
            o[i] = t;
            left -= t;
        }
        switch (z) {
            case 24: o[5] = 1; o[6] = 5; break;
            case 29: o[5] = 1; o[6] = 10; break;
            case 41: o[8] = 1; o[9] = 4; break;
            case 42: o[8] = 1; o[9] = 5; break;
            case 44: o[8] = 1; o[9] = 7; break;
            case 45: o[8] = 1; o[9] = 8; break;
            case 46: o[8] = 0; o[9] = 10; break;
            case 47: o[8] = 1; o[9] = 10; break;
            case 57: o[12] = 0; o[13] = 1; break;
            case 58: o[12] = 1; o[13] = 1; break;
            case 64: o[12] = 7; o[13] = 1; break;
            case 78: o[11] = 1; o[13] = 9; break;
            case 79: o[11] = 1; o[13] = 10; break;
            case 89: o[16] = 0; o[17] = 1; break;
            case 90: o[16] = 0; o[17] = 2; break;
            case 91: o[16] = 2; o[17] = 1; break;
            case 92: o[16] = 3; o[17] = 1; break;
            case 93: o[16] = 4; o[17] = 1; break;
            case 96: o[16] = 7; o[17] = 1; break;
            case 103: o[17] = 0; o[18] = 1; break;
            default: break;
        }
        return o;
    }

    /** Electrons in each shell n = 1..7 (trailing empty shells removed). */
    public static int[] shells(int z) {
        int[] o = occ(z);
        int[] s = new int[8];
        for (int i = 0; i < 19; i++) s[ORBN[i]] += o[i];
        int n = 7;
        while (n > 1 && s[n] == 0) n--;
        int[] r = new int[n];
        for (int i = 1; i <= n; i++) r[i - 1] = s[i];
        return r;
    }

    /** Noble-gas shorthand, e.g. "[Ar] 3d6 4s2". */
    public static String config(int z) {
        int[] o = occ(z);
        int core = -1;
        for (int i = 0; i < NOBLE.length; i++) if (NOBLE[i] < z) core = i;
        boolean[] used = new boolean[19];
        if (core >= 0) {
            int sum = 0;
            for (int i = 0; i < 19 && sum < NOBLE[core]; i++) {
                used[i] = true;
                sum += CAP[i];
            }
        }
        // sort remaining orbitals by (n, l)
        Integer[] idx = new Integer[19];
        for (int i = 0; i < 19; i++) idx[i] = i;
        java.util.Arrays.sort(idx, (a, b) -> {
            if (ORBN[a] != ORBN[b]) return ORBN[a] - ORBN[b];
            return ORBL[a] - ORBL[b];
        });
        StringBuilder sb = new StringBuilder();
        if (core >= 0) sb.append("[").append(NOBLE_SYM[core]).append("]");
        for (int k = 0; k < 19; k++) {
            int i = idx[k];
            if (used[i] || o[i] == 0) continue;
            if (sb.length() > 0) sb.append(" ");
            sb.append(ORB[i]).append(o[i]);
        }
        return sb.toString();
    }

    // ---------- formatting ----------
    public static String trim(double v) {
        String s = String.format(Locale.ENGLISH, "%.2f", v);
        if (s.indexOf('.') >= 0) {
            s = s.replaceAll("0+$", "");
            if (s.endsWith(".")) s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    /** unit: 0 = K, 1 = degrees C, 2 = degrees F. */
    public static String temp(double k, int unit) {
        if (Double.isNaN(k)) return "\u2014";
        if (unit == 1) return trim(k - 273.15) + " \u00B0C";
        if (unit == 2) return trim(k * 9.0 / 5.0 - 459.67) + " \u00B0F";
        return trim(k) + " K";
    }

    public static String density(int z) {
        double d = DENS[z];
        if (Double.isNaN(d)) return "\u2014";
        String pre = STATE[z] == '?' ? "~" : "";
        if (STATE[z] == 'g') return trim(d * 1000.0) + " g/L";
        return pre + trim(d) + " g/cm\u00B3";
    }

    public static String mass(int z) {
        double m = MASS[z];
        boolean radioactive = (z == 43 || z == 61 || z >= 84 && z != 90 && z != 91 && z != 92);
        String s = trim(m);
        return radioactive ? "[" + s + "]" : s;
    }
}
