AndroidLaTeXMath
======

Introduction
------

*AndroidLaTeXMath* is a port to Android of [JLaTeXMath](https://github.com/opencollab/jlatexmath), a
mathematical formulas-oriented LaTeX rendering library.

It is still a work in progress, many things could be improved and a better wrapper should be developed.


How to use
------

Add the following dependency in your ```build.gradle```:

```
dependencies {
    compile 'com.tonymanou.library:androidlatexmath:1.0.0'
}
```

Before using the library, you must initialize it from the ```onCreate()``` method of your application class:

 - by initializing the AssetHelper (to allow the library to retrieve fonts from assets), 
 - by letting the TeXFormula class retrieve the current screen density.

```java
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssetHelper.initialize(this);
        TeXFormula.setDensityFrom(this);
    }
}
```

**N.B.: Fonts, pictures and files referenced from your LaTeX formulas must be put in the ```assets``` folder (not in ```res```!)**

Now, you can create your LaTeX formula and render it to a Canvas:

```java
TeXFormula formula = new TeXFormula("\\text{Hello world}");
TeXIcon icon = formula.createTeXIconBuilder()
       .setStyle(TeXConstants.STYLE_DISPLAY)
       .setSize(16)
       .build();
icon.paintIcon(canvas, 0, 0);
```

Have fun!

Do not hesitate to take a look at [the various samples](examples/src/main/java/com/tonymanou/androidlatexmath_examples/fragment/)
provided with the library for more information.


Original JLaTeXMath README (shortened a little)
------

<p>JLaTeXMath is a Java library. Its main purpose is to display mathematical formulas written in LaTeX. JLaTeXMath is the best Java library to display LaTeX code.</p>
<p>This library is used by numerous important projects like <a href="http://www.scilab.org/">Scilab</a>, <a href="http://www.geogebra.org/">Geogebra</a>, <a href="http://freeplane.sourceforge.net">Freeplane</a>, <a href="http://www.mathpiper.org/">Mathpiper</a>, <a href="http://db-maths.nuxit.net/CaRMetal/index_en.html">CaRMetal</a>, <a href="http://ultrastudio.org/">Ultrastudio</a>, etc.

<p> You can now follow the development of <i>JLaTeXMath</i> or ask for questions or requests in using the mailing-list <a href="mailto:jlatexmath@lists.forge.scilab.org">jlatexmath@lists.forge.scilab.org</a>. The releases are announced on it.
</p>
<p>You can subscribe to this mailing-list by checking <a href="http://lists.scilab.org/mailman/listinfo/jlatexmath">jlatexmath mailing list</a></p>
<p>The default encoding is UTF-8.</p>
<p>The most of LaTeX commands are available and :</p>
<ol type="i">
<li>macros from <i>amsmath</i> and symbols from <i>amssymb</i> and <i>stmaryrd</i>;</li>
<li><code>\includegraphics</code> (without options);</li>
<li>the TeX macro <code>\over</code>;</li>
<li>accents from <i>amsxtra</i> package;
<li>the macros <code>\definecolor</code>, <code>\textcolor</code>, <code>\colorbox</code> and <code>\fcolorbox</code> from the package <i>color</i>;</li>
<li>the macros <code>\rotatebox</code>, <code>\reflectbox</code> and <code>\scalebox</code> from the package <i>graphicx</i>;
<li>the most of latin unicode characters are available and cyrillic or greek characters are detected for the loading of the different fonts;</li>
<li>the commands <code>\newcommand</code> and <code>\newenvironment</code>;</li>
<li>the environments <code>array<\code>, <code>matrix</code>, <code>pmatrix</code>,..., <code>eqnarray</code>, <code>cases</code>;</li>
<li>the vertical and horizontal lines are handled in array environment;</li>
<li>the commands to change the size of the font are available : <code>\tiny</code>, <code>\small</code>,...,<code>\LARGE</code>, <code>\huge</code>, <code>\Huge</code>,
<li>and probably other things I forgot...</li>
</ol>
There is no dependency and no external programs to install : <i>JLaTeXMath</i> is fully functional by itself.</p>
<p>Few examples are available in the source distribution, they show how to use <i>JLaTeXMath</i> and for developpers how to write new commands in using Java.</p>
<p>A first example :</p>
<p style="align: center"><img src="https://raw.githubusercontent.com/opencollab/jlatexmath/master/images/Formula1.png"/></p>
<p>a second one :</p>
<p><img src="https://raw.githubusercontent.com/opencollab/jlatexmath/master/images/Formula2.png"/></p>
<p>and a third one :</p>
<p><img src="https://raw.githubusercontent.com/opencollab/jlatexmath/master/images/Formula3.png"/></p>
<p><i>JLaTeXMath</i> is a fork of the excellent project <a href="http://jmathtex.sourceforge.net/">JMathTeX</a>.</p>
