/* Color.java
 * =========================================================================
 * This file is originally part of the AndroidLaTeXMath library -
 * http://github.com/tonymanou/AndroidLaTeXMath
 *
 * Copyright (C) 2016 Antoine MANN
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 *
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * Linking this library statically or dynamically with other modules
 * is making a combined work based on this library. Thus, the terms
 * and conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce
 * an executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under terms
 * of your choice, provided that you also meet, for each linked independent
 * module, the terms and conditions of the license of that module.
 * An independent module is a module which is not derived from or based
 * on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obliged to do so.
 * If you do not wish to do so, delete this exception statement from your
 * version.
 */

package com.tonymanou.androidlatexmath.helper;

import static android.graphics.Color.parseColor;
import static android.graphics.Color.rgb;

public class Color {

    public static final Color black = new Color(android.graphics.Color.BLACK);
    public static final Color white = new Color(android.graphics.Color.WHITE);
    public static final Color red = new Color(android.graphics.Color.RED);
    public static final Color green = new Color(android.graphics.Color.GREEN);
    public static final Color blue = new Color(android.graphics.Color.BLUE);
    public static final Color cyan = new Color(android.graphics.Color.CYAN);
    public static final Color magenta = new Color(android.graphics.Color.MAGENTA);
    public static final Color yellow = new Color(android.graphics.Color.YELLOW);

    private final int color;

    public Color(int ir, int ig, int ib) {
        color = rgb(ir, ig, ib);
    }

    public Color(float r, float g, float b) {
        color = rgb((int) (255 * r), (int) (255 * g), (int) (255 * b));
    }

    public Color(int i) {
        color = i;
    }

    public static Color decode(String s) {
        return new Color(parseColor(s));
    }

    public int getColor() {
        return color;
    }
}
