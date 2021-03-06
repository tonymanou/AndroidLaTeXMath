/* Font.java
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

import android.graphics.Typeface;

import java.awt.font.TextAttribute;
import java.util.Map;

public class Font {

    public static final int PLAIN = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;

    public final Typeface typeface;
    private int style;
    private float size;

    private Font(Typeface typeface, int style, float size) {
        this.typeface = typeface;
        this.size = size;
        this.style = style;
    }

    public Font(String fontName, int style, float size) {
        this(Typeface.create(fontName, toTypefaceStyle(style)), style, size);
    }

    public Font deriveFont(Map<TextAttribute, Object> map) {
        return new Font(typeface, style, size); // FIXME no effect for now
    }

    public Typeface deriveTypeface(int type) {
        int style = toTypefaceStyle(type);
        if (typeface == null) {
            return Typeface.defaultFromStyle(style);
        } else if (style == 0) {
            return typeface;
        } else {
            return Typeface.create(typeface, style);
        }
    }

    public static Font createFont(Class<?> base, String resourceName, float pixelsPerPoint) {
        return new Font(AssetHelper.loadTypeFace(base, resourceName), PLAIN, pixelsPerPoint);
    }

    private static int toTypefaceStyle(int style) throws IllegalArgumentException {
        switch (style) {
            case PLAIN:
                return Typeface.NORMAL;
            case BOLD:
                return Typeface.BOLD;
            case ITALIC:
                return Typeface.ITALIC;
            case BOLD | ITALIC:
                return Typeface.BOLD_ITALIC;
            default:
                throw new IllegalArgumentException("Unknown font style: " + style);
        }
    }
}
