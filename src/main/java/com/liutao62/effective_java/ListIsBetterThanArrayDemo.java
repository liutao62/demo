package com.liutao62.effective_java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 * @date Created in 2020/8/22 20:25
 * @description
 */
public class ListIsBetterThanArrayDemo {
    public static void main(String[] args) {
        /**
         * reason: 数组是协变的，Long 是 Object 的子类，那么 Long[] 就是 Object[] 的子类，所以会检查不出来
         * 数组是 具体化 的，在运行时才知道并检查他们的元素类型约束
         */
        // 运行报错
        Object[] arr = new Long[1];
        arr[0] = "望天！";

        /**
         * 而列表（尤指泛型）刚好是编译时期检查，运行时擦除
         */
        // 不能编译
        //List<Object> list = new ArrayList<Long>();


        // 唯一可以具体化的时无显示通配符类型
        List<?>[] lists = new ArrayList<?>[5];
        // generic array creation，创建泛型数组失败最好的解决办法是使用集合类型 List<E> ,而非 E[]
        // 使用集合类型 List<E> 会损失一些性能和简洁性，但是换回来的是更高类型安全性和互用性
//        List<String>[] list = new ArrayList<String>[5];

        // 假设定义泛型数组合法
//        List<String>[] strList = new ArrayList<String>[5];
//        List<Integer> integers = Arrays.asList(42);
//        Object[] objects = strList;
//        objects[0] = integers;
        // 在运行时会发生 CCE ，违背了泛型系统提供的基本保证，所以定义泛型数组报错
//        String s = strList[0].get(0);


    }
}
