/* Example1.java
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
 * This example shows how to use various fonts, including greek and cyrillic, as well as using colors.
 */
public class Example1 extends BaseExample {

    @Override
    protected void doExample() {
        String latex = "\\begin{array}{lr}\\mbox{\\textcolor{Blue}{Russian}}&\\mbox{\\textcolor{Melon}{Greek}}\\\\";
        latex += "\\mbox{" + "привет мир".toUpperCase() + "}&\\mbox{" + "γειά κόσμο".toUpperCase() + "}\\\\";
        latex += "\\mbox{привет мир}&\\mbox{γειά κόσμο}\\\\";
        latex += "\\mathbf{\\mbox{привет мир}}&\\mathbf{\\mbox{γειά κόσμο}}\\\\";
        latex += "\\mathit{\\mbox{привет мир}}&\\mathit{\\mbox{γειά κόσμο}}\\\\";
        latex += "\\mathsf{\\mbox{привет мир}}&\\mathsf{\\mbox{γειά κόσμο}}\\\\";
        latex += "\\mathtt{\\mbox{привет мир}}&\\mathtt{\\mbox{γειά κόσμο}}\\\\";
        latex += "\\mathbf{\\mathit{\\mbox{привет мир}}}&\\mathbf{\\mathit{\\mbox{γειά κόσμο}}}\\\\";
        latex += "\\mathbf{\\mathsf{\\mbox{привет мир}}}&\\mathbf{\\mathsf{\\mbox{γειά κόσμο}}}\\\\";
        latex += "\\mathsf{\\mathit{\\mbox{привет мир}}}&\\mathsf{\\mathit{\\mbox{γειά κόσμο}}}\\\\";
        latex += "&\\\\";
        latex += "\\mbox{\\textcolor{Salmon}{Bulgarian}}&\\mbox{\\textcolor{Tan}{Serbian}}\\\\";
        latex += "\\mbox{здравей свят}&\\mbox{Хелло уорлд}\\\\";
        latex += "&\\\\";
        latex += "\\mbox{\\textcolor{Turquoise}{Bielorussian}}&\\mbox{\\textcolor{LimeGreen}{Ukrainian}}\\\\";
        latex += "\\mbox{прывітаньне Свет}&\\mbox{привіт світ}\\\\";
        latex += "\\end{array}";

        TeXFormula formula = new TeXFormula(latex);
        TeXIcon icon = formula.createTeXIconBuilder()
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .setSize(16)
                .build();

        icon.setInsets(new Rect(5, 5, 5, 5));

        Bitmap bitmap = Bitmap.createBitmap(icon.getIconWidth(), icon.getIconHeight(), Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.WHITE);
        icon.paintIcon(new Canvas(bitmap), 0, 0);

        imageView.setImageBitmap(bitmap);
    }
}
