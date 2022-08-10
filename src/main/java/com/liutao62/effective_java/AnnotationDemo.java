package com.liutao62.effective_java;

import lombok.experimental.Accessors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author liutao
 * @date Created in 2020/8/26 23:25
 * @description
 */
public class AnnotationDemo {
    public String name;

    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName(AnnotationDemo.class.getName());
        System.out.println(AnnotationDemo.class.getName());
        Constructor<?>[] declaredConstructors = clz.getDeclaredConstructors();
        System.out.println(declaredConstructors);
        Object o = declaredConstructors[0].newInstance();
        for (Method method : clz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyTest.class)) {
                // 如果实例对象不传实例则会报错
//                method.invoke(null);
                String[] value = method.getAnnotation(MyTest.class).value();

                if (value == null || value.length == 0) {
                    method.invoke(o);
                } else if ("SLKJL".equals(value[0])) {
                    // 如果直接传数组会 wrong number of arguments
                    // String[] str 相当于一个 Object ，直接传一个数组过去就会参数列表不一致
//                    method.invoke(o, (Object) value);
                    method.invoke(o, new Object[]{value[0], "12", "23"});
                    continue;
                }
                // 数组也相当于可变参数，所以不 continue 会报错
                else if (method.isVarArgs()) {
                    method.invoke(o, new Object[]{value});
                }

            }
        }
    }

    @MyTest
    public static void test() {
        System.out.println("hello MyTest!");
    }

    @MyTest
    public void noStaticTest() {
        System.out.println("no static");
    }

    @MyTest({"default value", "array value"})
    public void defaultValueTest(String[] str) {
        System.out.println("--------");
        Arrays.stream(str).forEach(System.out::println);
    }

    @MyTest({"var", "args", "more"})
    public void varArgsTest(String... arr) {
        Optional<String> reduce = Arrays.stream(arr).reduce((s1, s2) -> s1 + s2);
        System.out.println(reduce.get());
    }

    @MyTest("SLKJL")
    public void args(String id, String name, String age) {
        System.out.println(id + name + age);
    }
}

@Accessors
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyTest {
    String[] value() default {};
}