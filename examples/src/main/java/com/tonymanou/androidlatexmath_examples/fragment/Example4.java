/* Example4.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://jlatexmath.sourceforge.net
 *
 * Copyright (C) 2016 Antoine MANN
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

package com.tonymanou.androidlatexmath_examples.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.tonymanou.androidlatexmath_examples.ExampleActivity.BaseExample;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 * This example shows how to draw a table and an image with various sizes and angles.
 */
public class Example4 extends BaseExample {

    @Override
    protected void doExample() {
        String latex = "\\begin{array}{|c|c|c|c|}\n";
        latex += "\\multicolumn{4}{c}{\\shadowbox{\\text{\\Huge An image from the \\LaTeX3 project}}}\\cr\n";
        latex += "\\hline\n";
        latex += "\\text{Left}\\includegraphics{lion.png}\\text{Right} & \\text{Left}\\includegraphics[width=3cm,interpolation=bicubic]{lion.png}\\text{Right} & \\text{Left}\\includegraphics[angle=45,width=3cm]{lion.png}\\text{Right} & \\text{Left}\\includegraphics[angle=160]{lion.png}\\text{Right} \\cr\n";
        latex += "\\hline\n";
        latex += "\\text{normal} & \\text{width=3cm} & \\text{angle=45, width=3cm} & \\text{angle=160}\\cr\n";
        latex += "\\hline\n";
        latex += "\\end{array}\n";

        TeXFormula formula = new TeXFormula(latex);
        TeXIcon icon = formula.createTeXIconBuilder()
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .setSize(10)
                .build();

        icon.setInsets(new Rect(5, 5, 5, 5));

        Bitmap bitmap = Bitmap.createBitmap(icon.getIconWidth(), icon.getIconHeight(), Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.WHITE);
        icon.paintIcon(new Canvas(bitmap), 0, 0);

        imageView.setImageBitmap(bitmap);
    }
}
