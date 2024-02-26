package com.liutao62.instrumentation;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author liutao
 * @date 2023/10/31
 */
public class InstrumentationRetransform {

    private static Instrumentation instrumentation;

    // JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new MyClassTransformer(inst));
    }
}

class MyClassTransformer implements ClassFileTransformer {

    private static Instrumentation INST;

    public MyClassTransformer(Instrumentation inst) {
        INST = inst;
    }

    @Override
    public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain, final byte[] classfileBuffer) {
        // 操作Date类
        if (className.contains("liutao62") && !"com/liutao62/Instrumentation/Test".equals(className)) {
            try {
                ClassPool pool = ClassPool.getDefault();
                CtClass ctClass = pool.get(className.replaceAll("/", "."));
                ctClass.addMethod(CtNewMethod.make("public Object myNewMethod1(Object obj) { System.out.println(\"myNewMethod1\");\n return obj; }", ctClass));
                ctClass.addMethod(CtNewMethod.make("public Object myNewMethod2(Object obj) { return obj; }", ctClass));
                ctClass.addMethod(CtNewMethod.make("public Object myNewMethod3(Object obj) { return obj; }", ctClass));

                CtMethod test1 = ctClass.getDeclaredMethod("test1");
                test1.setBody("{ System.out.println(\"com.liutao62.TestBranch.test1\");\n System.out.println(\"test1 call myNewMethod1\");\n this.myNewMethod1(null); }");
                test1.insertAfter("{ System.out.println(\"insertAfter\"); }");
                ctClass.writeFile("/Users/liutao/Downloads");
                INST.redefineClasses();
                INST.retransformClasses();
                return ctClass.toBytecode();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        // 如果返回null则字节码不会被修改
        return classfileBuffer;
    }
}