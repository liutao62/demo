package com.javasedemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RefilectiveDemo {
    public static void main(String[] args) {
        method1();

    }

    public static void method1(String ... str){
        //method 1:
        Class cls;
        try {
            cls = Class.forName("com.javasedemo.Producer");
            Producer o = (Producer) cls.newInstance();
            System.out.println(o.getClass());
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {

                System.out.println(method.getName());
                if (method.getName().startsWith("get")){
                    method.setAccessible(true);
                    System.out.println("invoke return : " + method.invoke(o));
                }
                if (method.getName().startsWith("set")){
                    method.setAccessible(true);
                    System.out.println("invoke return : " + method.invoke(o,BOX.Test));
                }

            }

            System.out.println("----");
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                System.out.println("========");
                System.out.println("field.getName()  : = " + field.getName());
                System.out.println("o.getBox()  :  " + o.getBox());
                field.setAccessible(true);
                field.set(o,null);
                System.out.println(o.getBox());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
