/* ExampleActivity.java
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

package com.tonymanou.androidlatexmath_examples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class ExampleActivity extends AppCompatActivity {

    static final String BUNDLE_EXAMPLE_NAME = "exampleName";
    static final String BUNDLE_EXAMPLE_CLASS = "exampleClass";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_example);

        BaseExample example = retrieveExample(getIntent());
        if (example != null) {
            example.doExample(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private BaseExample retrieveExample(Intent intent) {
        if (intent != null) {
            String exampleName = intent.getStringExtra(BUNDLE_EXAMPLE_NAME);
            if (exampleName != null) {
                setTitle(exampleName);
            }

            String exampleClassName = intent.getStringExtra(BUNDLE_EXAMPLE_CLASS);
            if (exampleClassName != null) {
                try {
                    return (BaseExample) Class.forName(exampleClassName).newInstance();
                } catch (Exception e) {
                    reportException(this, e);
                    return null;
                }
            }
        }
        Toast.makeText(this, "No example parameter given", Toast.LENGTH_LONG).show();
        return null;
    }

    private static void reportException(Context context, Exception e) {
        Toast.makeText(context, e.getClass().getName() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }

    public static abstract class BaseExample {

        protected ImageView imageView;
        private Context context;

        public void doExample(Activity activity) {
            context = activity;
            imageView = (ImageView) activity.findViewById(R.id.example_image);

            try {
                doExample();
            } catch (Exception e) {
                reportException(activity, e);
            }
        }

        protected abstract void doExample() throws Exception;

        protected Context getContext() {
            return context;
        }
    }
}
