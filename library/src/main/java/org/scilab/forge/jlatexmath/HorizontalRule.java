/* HorizontalRule.java
 * =========================================================================
 * This file is originally part of the JMathTeX Library - http://jmathtex.sourceforge.net
 * 
 * Copyright (C) 2004-2007 Universiteit Gent
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

import android.graphics.Canvas;

import java.awt.Color;

/**
 * A box representing a horizontal line.
 */
public class HorizontalRule extends Box {

    private float speShift = 0;
    
    public HorizontalRule(float thickness, float width, float s) {
        this(thickness, width, s, true, null);
    }

    public HorizontalRule(float thickness, float width, float s, boolean trueShift) {
        this(thickness, width, s, trueShift, null);
    }

    public HorizontalRule(float thickness, float width, float s, Color c) {
        this(thickness, width, s, true, c);
    }

    private HorizontalRule(float thickness, float width, float s, boolean trueShift, Color c) {
        super(c, null);
        height = thickness;
        this.width = width;
        if (trueShift) {
            shift = s;
        } else {
            shift = 0;
            speShift = s;
        }
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int save = canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.translate(x, y);
        if (speShift == 0) {
            rectF.set(0, 0, width, -height);
	} else {
            rectF.set(0, 0, width, speShift - height);
	}
        canvas.drawRect(rectF, paint);
        canvas.restoreToCount(save);
    }
    
    public int getLastFontId() {
	return TeXFont.NO_FONT;
    }
}
