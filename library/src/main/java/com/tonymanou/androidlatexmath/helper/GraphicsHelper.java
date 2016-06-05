/* Drawing.java
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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

public class GraphicsHelper {

    private final Canvas canvas;
    private final Paint fillPaint, paint;
    private final Paint strokePaint;
    private final Stroke stroke;
    private final RectF rectF;
    private int currentColor;

    public GraphicsHelper(Canvas canvas) {
        this.canvas = canvas;

        paint = fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        stroke = new Stroke(strokePaint);

        rectF = new RectF();
        currentColor = Color.MAGENTA;
    }

    // ========== STROKE ==========

    public Stroke getStroke() {
        return new Stroke(stroke);
    }

    public void setStroke(Stroke stroke) {
        paint.setStrokeWidth(stroke.width);
        paint.setStrokeCap(stroke.cap);
        paint.setStrokeJoin(stroke.join);
        paint.setStrokeMiter(stroke.miter);
    }

    public void setStroke(float width) {
        paint.setStrokeWidth(stroke.width = width);
    }

    public void setStroke(float width, Paint.Cap cap, Paint.Join join) {
        paint.setStrokeWidth(stroke.width = width);
        paint.setStrokeCap(stroke.cap = cap);
        paint.setStrokeJoin(stroke.join = join);
    }

    public void setStroke(float width, Paint.Cap cap, Paint.Join join, float miter) {
        paint.setStrokeWidth(stroke.width = width);
        paint.setStrokeCap(stroke.cap = cap);
        paint.setStrokeJoin(stroke.join = join);
        paint.setStrokeMiter(stroke.miter = miter);
    }

    public static class Stroke {

        private float width;
        private Paint.Cap cap;
        private Paint.Join join;
        private float miter;

        private Stroke(Stroke stroke) {
            width = stroke.width;
            cap = stroke.cap;
            join = stroke.join;
            miter = stroke.miter;
        }

        private Stroke(Paint paint) {
            width = paint.getStrokeWidth();
            cap = paint.getStrokeCap();
            join = paint.getStrokeJoin();
            miter = paint.getStrokeMiter();
        }
    }

    // ========== COLOR / RECTANGLE ==========

    public int getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(int color) {
        paint.setColor(currentColor = color);
    }

    public void setRect(float left, float top, float right, float bottom) {
        rectF.set(left, top, right, bottom);
    }

    // ========== DRAW ==========

    public void fillRect(float left, float top, float right, float bottom, int color) {
        paint.setColor(color);
        canvas.drawRect(left, top, right, bottom, paint);
        paint.setColor(currentColor);
    }

    public void fillRect(float left, float top, float right, float bottom) {
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public void fillRect(int color) {
        paint.setColor(color);
        canvas.drawRect(rectF, paint);
        paint.setColor(currentColor);
    }

    public void drawRect(float left, float top, float right, float bottom) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(left, top, right, bottom, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    public void drawRect(int color) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        canvas.drawRect(rectF, paint);
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.FILL);
    }

    public void drawRect() {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    public void drawRoundRect(float rx, float ry) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rectF, rx, ry, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    public void drawLine(float startX, float startY, float stopX, float stopY) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    public void drawArc(int startAngle, int sweepAngle, boolean useCenter, int color) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, paint);
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.FILL);
    }

    public void drawOval(int color) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.FILL);
    }

    public void fillOval(int color) {
        paint.setColor(color);
        canvas.drawOval(rectF, paint);
        paint.setColor(currentColor);
    }

    public void drawText(String text, int x, int y, Paint paint) {
        paint.setColor(currentColor);
        canvas.drawText(text, x, y, paint);
    }

    public void drawText(char[] text, int index, int count, int x, int y) {
        canvas.drawText(text, index, count, x, y, paint);
    }

    public void drawBitmap(Bitmap bitmap, int left, int top) {
        canvas.drawBitmap(bitmap, left, top, null);
    }

    // ========== FONT ==========

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
    }

    public Typeface getTypeface() {
        return paint.getTypeface();
    }

    public void setFontSize(float textSize) {
        paint.setTextSize(textSize);
    }

    // ========== MATRIX ==========

    public int matrixSave() {
        return canvas.save(Canvas.MATRIX_SAVE_FLAG);
    }

    public void matrixRestoreToCount(int count) {
        canvas.restoreToCount(count);
    }

    public float[] getMatrix() {
        float[] m = new float[9];
        canvas.getMatrix().getValues(m);
        return m;
    }

    public void matrixTranslate(float dx, float dy) {
        canvas.translate(dx, dy);
    }

    public void matrixScale(float sx, float sy) {
        canvas.scale(sx, sy);
    }

    public void matrixRotate(float degrees) {
        canvas.rotate(degrees);
    }

    public void matrixRotate(float degrees, float px, float py) {
        canvas.rotate(degrees, px, py);
    }
}
