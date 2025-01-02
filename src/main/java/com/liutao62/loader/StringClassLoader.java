package com.liutao62.loader;

import com.google.common.collect.Maps;
import com.itranswarp.compiler.JavaStringCompiler;
import org.codehaus.commons.compiler.CompilerFactoryFactory;
import org.codehaus.commons.compiler.ICompiler;
import org.codehaus.commons.compiler.ICompilerFactory;
import org.codehaus.commons.compiler.util.ResourceFinderClassLoader;
import org.codehaus.commons.compiler.util.resource.MapResourceCreator;
import org.codehaus.commons.compiler.util.resource.MapResourceFinder;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.StringResource;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liutao
 * @date 2023/3/20
 */
public class StringClassLoader {

    private static final String packagePrefix = "com.liutao62.loader";

    public static final String script = "package com.liutao62.loader;\n" +
            "\n" +
            "import java.util.HashMap;\n" +
            "import java.util.Map;\n" +
            "\n" +
            "public class StringScript {\n" +
            "    \n" +
            "    \n" +
            "    public static void eval(Map<String, Object> args) {\n" +
            "        System.out.println(\"I'm script\" + args);\n" +
            "    }\n" +
            "}";

    public static void main(String[] args) throws Exception {
        StopWatch stopWatch = new StopWatch("compilier");
        stopWatch.start("JavaStringCompiler");
        JavaStringCompiler compiler = new JavaStringCompiler();
        String scriptClassName = "StringScript";
        Map<String, byte[]> results = compiler.compile(scriptClassName + ".java", script);
        Class<?> clazz = compiler.loadClass(packagePrefix + "." + scriptClassName, results);

        Method eval = clazz.getMethod("eval", Map.class);

        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("1111", 22222);
        eval.invoke(null, hashMap);
        stopWatch.stop();

        stopWatch.start("ICompilerFactory");

        ICompilerFactory defaultCompilerFactory = CompilerFactoryFactory.getDefaultCompilerFactory(ClassLoader.getSystemClassLoader());

        ICompiler compilerNew = defaultCompilerFactory.newCompiler();

// Store generated .class files in a Map:
        Map<String, byte[]> classes = new HashMap<String, byte[]>();
        compilerNew.setClassFileCreator(new MapResourceCreator(classes));

// Now compile two units from strings:
//        compilerNew.compile(new Resource[]{
//                new StringResource(
//                        "pkg1/A.java",
//                        "package pkg1; public class A { public static int meth() { return pkg2.B.meth(); } }"
//                ),
//                new StringResource(
//                        "pkg2/B.java",
//                        "package pkg2; public class B { public static int meth() { return 77;            } }"
//                ),
//        });

        compilerNew.compile(new Resource[]{
                new StringResource(
                        scriptClassName + ".java",
                        script
                )
        });

// Set up a class loader that uses the generated classes.
        ClassLoader cl = new ResourceFinderClassLoader(
                new MapResourceFinder(classes),    // resourceFinder
                ClassLoader.getSystemClassLoader() // parent
        );

        hashMap.put("1111", 33333);
        Class<?> aClass = cl.loadClass(packagePrefix + "." + scriptClassName);
        Object meth = aClass.getDeclaredMethod("eval", Map.class).invoke(null, hashMap);
        System.out.println(meth);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
