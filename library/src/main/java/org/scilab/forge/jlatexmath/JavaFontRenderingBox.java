/* ScaleBox.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2009 DENIZET Calixte
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
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
 * 
 */

package org.scilab.forge.jlatexmath;

import android.graphics.Paint;
import android.graphics.Typeface;

import com.tonymanou.androidlatexmath.helper.Font;
import com.tonymanou.androidlatexmath.helper.GraphicsHelper;

import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.util.Map;

/**
 * A box representing a scaled box.
 */
public class JavaFontRenderingBox extends Box {

    private static Font font = new Font("Serif", Font.PLAIN, 10);

    private String str;
    private Paint paint;

    public JavaFontRenderingBox(String str, int type, float size, Font f, boolean kerning) {
        this.str = str;

        if (kerning && TextAttribute.KERNING != null) {
            Map<TextAttribute, Object> map = new Hashtable<TextAttribute, Object>();
            map.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
            map.put(TextAttribute.LIGATURES, TextAttribute.LIGATURES_ON);
            f = f.deriveFont(map);
        }

        Typeface tf = f.deriveTypeface(type);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTypeface(tf);
        if (type > 0) {
            int typefaceStyle = tf != null ? tf.getStyle() : 0;
            int need = type & ~typefaceStyle;
            paint.setFakeBoldText((need & Font.BOLD) != 0);
            paint.setTextSkewX((need & Font.ITALIC) != 0 ? -0.25f : 0);
        } else {
            paint.setFakeBoldText(false);
            paint.setTextSkewX(0);
        }
        paint.setTextSize(size);

        Paint.FontMetrics metrics = paint.getFontMetrics();
        this.height = -metrics.ascent;
        this.depth = metrics.descent + metrics.leading + metrics.ascent;
        this.width = paint.measureText(str) + 0.4f;
    }

    public JavaFontRenderingBox(String str, int type, float size) {
        this(str, type, size, font, true);
    }

    public static void setFont(String name) {
        font = new Font(name, Font.PLAIN, 10);
    }

    @Override
    public void draw(GraphicsHelper g, float x, float y) {
        int save = g.matrixSave();
        drawDebug(g, x, y);
        g.matrixTranslate(x, y);
        g.drawText(str, 0, 0, paint);
        g.matrixRestoreToCount(save);
    }

    public int getLastFontId() {
        return 0;
    }
}
