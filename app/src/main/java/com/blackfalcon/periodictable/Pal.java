package com.blackfalcon.periodictable;

import android.graphics.Color;

/** Colors for the table. */
public class Pal {
    public static final int[] CAT = {
        0xFFEF5350, 0xFFFF9800, 0xFFFFEB3B, 0xFF66BB6A, 0xFF26C6DA, 0xFF42A5F5,
        0xFFEC407A, 0xFF7E57C2, 0xFFFFAB91, 0xFFCE93D8, 0xFF90A4AE};
    public static final int UNKNOWN = 0xFF455A64;

    public static int state(char s) {
        switch (s) {
            case 's': return 0xFF78909C;
            case 'l': return 0xFF29B6F6;
            case 'g': return 0xFFFF7043;
            default: return UNKNOWN;
        }
    }

    public static int block(char b) {
        switch (b) {
            case 's': return 0xFFEF5350;
            case 'p': return 0xFF42A5F5;
            case 'd': return 0xFFFFCA28;
            default: return 0xFF66BB6A;
        }
    }

    static int lerp(int a, int b, float t) {
        int r = (int) (Color.red(a) + (Color.red(b) - Color.red(a)) * t);
        int g = (int) (Color.green(a) + (Color.green(b) - Color.green(a)) * t);
        int bl = (int) (Color.blue(a) + (Color.blue(b) - Color.blue(a)) * t);
        return Color.rgb(r, g, bl);
    }

    /** Electronegativity 0.7 (blue) ... 2.2 (yellow) ... 4.0 (red). */
    public static int eneg(double e) {
        if (Double.isNaN(e)) return UNKNOWN;
        float t = (float) Math.max(0, Math.min(1, (e - 0.7) / 3.3));
        if (t < 0.45f) return lerp(0xFF1E88E5, 0xFFFFEB3B, t / 0.45f);
        return lerp(0xFFFFEB3B, 0xFFD32F2F, (t - 0.45f) / 0.55f);
    }

    public static int color(int mode, int z) {
        switch (mode) {
            case 1: return state(Elements.STATE[z]);
            case 2: return block(Elements.block(z));
            case 3: return eneg(Elements.ENEG[z]);
            default: return CAT[Elements.cat(z)];
        }
    }

    /** Black or white text, whichever is readable on this background. */
    public static int textOn(int bg) {
        double lum = 0.299 * Color.red(bg) + 0.587 * Color.green(bg) + 0.114 * Color.blue(bg);
        return lum > 150 ? 0xFF111111 : 0xFFFFFFFF;
    }
}
