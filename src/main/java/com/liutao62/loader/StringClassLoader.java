package com.liutao62.loader;

import com.google.common.collect.Maps;
import com.itranswarp.compiler.JavaStringCompiler;

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
        JavaStringCompiler compiler = new JavaStringCompiler();
        String scriptClassName = "StringScript";
        Map<String, byte[]> results = compiler.compile(scriptClassName + ".java", script);
        Class<?> clazz = compiler.loadClass(packagePrefix + "." + scriptClassName, results);

        Method eval = clazz.getMethod("eval", Map.class);

        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("1111", 22222);
        eval.invoke(null, hashMap);
    }
}
