/* MainActivity.java
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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tonymanou.androidlatexmath_examples.ExampleActivity.BaseExample;
import com.tonymanou.androidlatexmath_examples.fragment.Example1;
import com.tonymanou.androidlatexmath_examples.fragment.Example2;
import com.tonymanou.androidlatexmath_examples.fragment.Example3;
import com.tonymanou.androidlatexmath_examples.fragment.Example4;
import com.tonymanou.androidlatexmath_examples.fragment.Example5;
import com.tonymanou.androidlatexmath_examples.fragment.Example6;
import com.tonymanou.androidlatexmath_examples.fragment.Example7;
import com.tonymanou.androidlatexmath_examples.fragment.ExampleMacro;
import com.tonymanou.androidlatexmath_examples.fragment.ExampleSwing2;

public class MainActivity extends AppCompatActivity {

    private final ExampleItem[] examples = new ExampleItem[]{
            new ExampleItem(Example1.class, "Example 1"),
            new ExampleItem(Example2.class, "Example 2"),
            new ExampleItem(Example3.class, "Example 3"),
            new ExampleItem(Example4.class, "Example 4"),
            new ExampleItem(Example5.class, "Example 5"),
            new ExampleItem(Example6.class, "Example 6"),
            new ExampleItem(Example7.class, "Example 7"),
            new ExampleItem(ExampleSwing2.class, "Example Swing 2"),
            new ExampleItem(ExampleMacro.class, "Example macro")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.examples_list);
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, examples));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ExampleActivity.class);
                ExampleItem item = examples[position];
                intent.putExtra(ExampleActivity.BUNDLE_EXAMPLE_NAME, item.exampleName);
                intent.putExtra(ExampleActivity.BUNDLE_EXAMPLE_CLASS, item.exampleClassName);
                startActivity(intent);
            }
        });
    }

    private static class ExampleItem {

        final String exampleName;
        final String exampleClassName;

        private ExampleItem(Class<? extends BaseExample> clazz, String name) {
            exampleName = name;
            exampleClassName = clazz.getName();
        }

        @Override
        public String toString() {
            return exampleName;
        }
    }
}
