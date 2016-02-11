/* AssetHelper.java
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

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import org.scilab.forge.jlatexmath.ResourceParseException;

import java.io.IOException;
import java.io.InputStream;

public final class AssetHelper {

    private static AssetManager assetManager;

    private AssetHelper() {
    }

    public static void initialize(Context context) {
        assetManager = context.getAssets();
    }

    public static InputStream getStream(String path) throws NotFoundException {
        ensureInitializeCalled();
        try {
            return assetManager.open(path);
        } catch (IOException e) {
            throw new NotFoundException(path, e);
        }
    }

    public static InputStream getStream(Class<?> base, String resourceName) throws NotFoundException {
        return getStream(getPath(base, resourceName));
    }

    static Typeface loadTypeFace(Class<?> base, String resourceName) {
        ensureInitializeCalled();
        return Typeface.createFromAsset(assetManager, getPath(base, resourceName));
    }

    /**
     * Get absolute resource name, but without the leading slash.
     *
     * @param base         Base class used for its package name.
     * @param resourceName Name of the resource.
     * @return Absolute path to the resource, without the leading slash.
     */
    private static String getPath(Class<?> base, String resourceName) {
        if (resourceName.startsWith("/")) {
            return resourceName.substring(1);
        } else {
            String pkg = base.getName();
            int dot = pkg.lastIndexOf('.');
            if (dot != -1) {
                pkg = pkg.substring(0, dot).replace('.', '/');
            } else {
                pkg = "";
            }
            return pkg + "/" + resourceName;
        }
    }

    private static void ensureInitializeCalled() {
        if (assetManager == null) {
            throw new IllegalStateException("Did you call AssetHelper.initialize(Context) first?");
        }
    }

    private static class NotFoundException extends ResourceParseException {

        NotFoundException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }
}
