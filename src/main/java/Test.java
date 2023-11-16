import com.google.common.collect.Lists;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/10/14 16:55
 * @description
 */
//@Slf4j
public class Test {
    public static void target(int i) {
        // 空方法
    }

    public static void main(String[] args) throws Exception {
        // v1 2642
//        Class<?> klass = Class.forName("Test");
//        Method method = klass.getMethod("target", int.class);
//
//        long current = System.currentTimeMillis();
//        for (int i = 1; i <= 1_000_000_000; i++) {
//            method.invoke(null, 128);
//        }
//        long temp = System.currentTimeMillis();
//        System.out.println(temp - current);

        // v2 2295
//        Class<?> klass = Class.forName("Test");
//        Method method = klass.getMethod("target", int.class);
//        Object[] arg = new Object[1]; // 在循环外构造参数数组
//        arg[0] = 128;
//        long current = System.currentTimeMillis();
//        for (int i = 1; i <= 1_000_000_000; i++) {
//            method.invoke(null, arg);
//        }
//        long temp = System.currentTimeMillis();
//        System.out.println(temp - current);

        // v4 2474
        // 在运行指令中添加如下两个虚拟机参数：
        // -Djava.lang.Integer.IntegerCache.high=128
        // -Dsun.reflect.noInflation=true
//        Class<?> klass = Class.forName("Test");
//        Method method = klass.getMethod("target", int.class);
//        // 关闭权限检查
//        method.setAccessible(true);
//
//        long current = System.currentTimeMillis();
//        for (int i = 1; i <= 1_000_000_000; i++) {
//            method.invoke(null, 128);
//        }
//        long temp = System.currentTimeMillis();
//        System.out.println(temp - current);

        // 7299
//        Class<?> klass = Class.forName("Test");
//        Method method = klass.getMethod("target", int.class);
//        // 关闭权限检查
//        method.setAccessible(true);
//
//        polluteProfile();
//        long current = System.currentTimeMillis();
//        for (int i = 1; i <= 1_000_000_000; i++) {
//            method.invoke(null, 128);
//        }
//        long temp = System.currentTimeMillis();
//        System.out.println(temp - current);

        ArrayList<String> list1 = Lists.newArrayList();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("/Users/liutao/Downloads/11.txt"));
        BufferedWriter bw = null;
        OutputStreamWriter osw = null;
        BufferedReader buffreader = null;
        InputStream is = null;
        is = new FileInputStream("/Users/liutao/Downloads/11.txt");
        InputStreamReader inputreader = new InputStreamReader(is);
        buffreader = new BufferedReader(inputreader);
        String line;
        while ((line = buffreader.readLine()) != null) {
            list1.add(line);
        }


        ArrayList<String> list2 = Lists.newArrayList();
        is = new FileInputStream("/Users/liutao/Downloads/22.txt");
        inputreader = new InputStreamReader(is);
        buffreader = new BufferedReader(inputreader);
        while ((line = buffreader.readLine()) != null) {
            list2.add(line);
        }

        list1.removeAll(list2);

        StringBuilder stringBuilder = new StringBuilder("('");
        String collect = list1.stream().collect(Collectors.joining("','"));
        stringBuilder.append(collect).append("')");

        System.out.println();

    }

    public static void polluteProfile() throws Exception {
        Method method1 = Test.class.getMethod("target1", int.class);
        Method method2 = Test.class.getMethod("target2", int.class);
        for (int i = 0; i < 2000; i++) {
            method1.invoke(null, 0);
            method2.invoke(null, 0);
        }
    }

    public static void target1(int i) {
    }

    public static void target2(int i) {
    }
}
