package com.blackfalcon.periodictable;

import android.content.Context;
import android.content.SharedPreferences;

public class Store {
    private final SharedPreferences p;

    public Store(Context c) {
        p = c.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    /** English is the default and is pre-selected on first launch. */
    public String lang() { return p.getString("lang", "en"); }
    public void setLang(String l) { p.edit().putString("lang", l).apply(); }
    public boolean dark() { return p.getBoolean("dark", true); }
    public void setDark(boolean v) { p.edit().putBoolean("dark", v).apply(); }
    public boolean rtl() { return !lang().equals("en"); }

    /** 0 = Kelvin, 1 = Celsius, 2 = Fahrenheit. */
    public int unit() { return p.getInt("unit", 1); }
    public void setUnit(int v) { p.edit().putInt("unit", v).apply(); }

    public int best() { return p.getInt("best", 0); }
    public void setBest(int v) { p.edit().putInt("best", v).apply(); }

    public String tr(String ur, String en, String ar) {
        String l = lang();
        if (l.equals("en")) return en;
        if (l.equals("ar")) return ar;
        return ur;
    }
}
