/* GraphicsBox.java
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
import android.graphics.Paint;
import android.graphics.RectF;

import java.awt.Color;

/**
 * A box representing a box containing a graphics.
 */
public class GeoGebraLogoBox extends Box {

    private static final int gray = new Color(102, 102, 102).getColor();
    private static final int blue = new Color(153, 153, 255).getColor();
    private static final int black = Color.BLACK.getColor();

    private final Paint paint;
    private final RectF rectF;

    public GeoGebraLogoBox(float w, float h) {
	this.depth = 0;
	this.height = h;
	this.width = w;
	this.shift = 0;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(3.79999995f);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeMiter(4f);
        rectF = new RectF();
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int save = canvas.save(Canvas.MATRIX_SAVE_FLAG);

        canvas.translate(x + 0.25f * height / 2.15f, y - 1.75f / 2.15f * height);
        canvas.scale(0.05f * height / 2.15f, 0.05f * height / 2.15f);

        int save2 = canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate((float) (-26 * Math.PI / 180), 20.5f, 17.5f);
        paint.setColor(gray);
        rectF.set(0, 0, 43, 32);
        canvas.drawArc(rectF, 0, 360, false, paint);
        canvas.restoreToCount(save2);

        rectF.set(0, 0, 8, 8);
        drawCircle(canvas, 16f, -5f);
        canvas.restoreToCount(save2);
        drawCircle(canvas, -1f, 7f);
        canvas.restoreToCount(save2);
        drawCircle(canvas, 5f, 28f);
        canvas.restoreToCount(save2);
        drawCircle(canvas, 27f, 24f);
        canvas.restoreToCount(save2);
        drawCircle(canvas, 36f, 3f);

        canvas.restoreToCount(save);
    }

    private void drawCircle(Canvas canvas, float x, float y) {
        paint.setColor(blue);
        canvas.translate(x, y);
        canvas.drawArc(rectF, 0, 360, true, paint);
        paint.setColor(black);
        canvas.drawArc(rectF, 0, 360, false, paint);
    }
    
   public int getLastFontId() {
	return 0;
    }
}
