import java.lang.reflect.Method;

/**
 * @author liutao
 * @date Created in 2020/10/14 16:55
 * @description
 */
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
        Class<?> klass = Class.forName("Test");
        Method method = klass.getMethod("target", int.class);
        // 关闭权限检查
        method.setAccessible(true);

        polluteProfile();
        long current = System.currentTimeMillis();
        for (int i = 1; i <= 1_000_000_000; i++) {
            method.invoke(null, 128);
        }
        long temp = System.currentTimeMillis();
        System.out.println(temp - current);
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
