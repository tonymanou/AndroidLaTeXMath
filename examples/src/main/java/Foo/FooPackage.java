/* FooPackage.java
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

package Foo;

import android.graphics.Color;
import android.graphics.Matrix;

import com.tonymanou.androidlatexmath.helper.GraphicsHelper;

import org.scilab.forge.jlatexmath.Atom;
import org.scilab.forge.jlatexmath.Box;
import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.SpaceAtom;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXEnvironment;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXParser;

public class FooPackage {

    /*
     * The macro fooA is equivalent to \newcommand{\fooA}[2]{\frac{\textcolor{red}{#2}}{#1}}
     */
    public Atom fooA_macro(TeXParser tp, String[] args) throws ParseException {
        return new TeXFormula("\\frac{\\textcolor{red}{" + args[2] + "}}{" + args[1] + "}").root;
    }

    public Atom fooB_macro(TeXParser tp, String[] args) throws ParseException {
        float f = Float.parseFloat(args[1]);
        return new MyAtom(f);
    }

    public Atom fooC_macro(TeXParser tp, String[] args) throws ParseException {
        float f = Float.parseFloat(args[1]);
        return new MyAtom(f, args[2].length() != 0);
    }

    public Atom fooD_macro(TeXParser tp, String[] args) throws ParseException {
        float f = Float.parseFloat(args[1]);
        return new MyAtom(f, args[2].length() == 0);
    }

    private class MyAtom extends Atom {

        private float f;
        private boolean filled = false;

        MyAtom(float f) {
            this.f = f;
        }

        MyAtom(float f, boolean filled) {
            this.f = f;
            this.filled = filled;
        }

        @Override
        public Box createBox(TeXEnvironment env) {
            return new MyBox((int) f, new SpaceAtom(TeXConstants.UNIT_POINT, f, 0, 0).createBox(env).getWidth(), filled);
        }
    }

    private class MyBox extends Box {

        private boolean filled;
        private int radius;

        MyBox(int r, float f, boolean filled) {
            this.filled = filled;
            this.radius = (int) (r * TeXFormula.PIXELS_PER_POINT);
            this.width = f;
            this.height = f / 2;
            this.depth = f / 2;
        }

        @Override
        public void draw(GraphicsHelper g, float x, float y) {
            int save = g.matrixSave();

            g.matrixTranslate(x, y - height);
            float[] m = g.getMatrix();
            g.matrixScale(Math.abs(1 / m[Matrix.MSCALE_X]), Math.abs(1 / m[Matrix.MSCALE_Y]));

            g.setRect(0, 0, radius, radius);
            if (filled) {
                g.fillOval(Color.RED);
            } else {
                g.drawOval(Color.RED);
            }

            g.matrixRestoreToCount(save);
        }

        @Override
        public int getLastFontId() {
            return 0;
        }
    }
}
