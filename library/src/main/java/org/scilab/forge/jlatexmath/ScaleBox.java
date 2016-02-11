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

import android.graphics.Canvas;

/**
 * A box representing a scaled box.
 */
public class ScaleBox extends Box {

    private Box box;
    private double xscl, yscl;
    private float factor = 1;

    public ScaleBox(Box b, double xscl, double yscl) {
	this.box = b;
	this.xscl = (Double.isNaN(xscl) || Double.isInfinite(xscl)) ? 0 : xscl;
	this.yscl = (Double.isNaN(yscl) || Double.isInfinite(yscl)) ? 0 : yscl;
	width = b.width * (float) Math.abs(this.xscl);
	height = this.yscl > 0 ? b.height * (float) this.yscl : -b.depth * (float) this.yscl;
	depth = this.yscl > 0 ? b.depth * (float) this.yscl : -b.height * (float) this.yscl;
	shift = b.shift * (float) this.yscl;
    }

    public ScaleBox(Box b, float factor) {
	this(b, (double) factor, (double) factor);
	this.factor = factor;
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int save = canvas.save(Canvas.MATRIX_SAVE_FLAG);
        drawDebug(canvas, x, y);
	if (xscl != 0 && yscl != 0) {
	    float dec = xscl < 0 ? width : 0;
            canvas.translate(x + dec, y);
            canvas.scale((float) xscl, (float) yscl);
            box.draw(canvas, 0, 0);
        }
        canvas.restoreToCount(save);
    }

    public int getLastFontId() {
	return box.getLastFontId();
    }
}
