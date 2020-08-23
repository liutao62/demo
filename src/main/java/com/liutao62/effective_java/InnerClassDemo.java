package com.liutao62.effective_java;

/**
 * @author liutao
 * @date Created in 2020/8/22 19:39
 * @description
 */
public class InnerClassDemo {
}

class Outer {
    private int a;

    /**
     * 每一个实例都隐含一个外围实例，且这种关联关系不能被修改
     * <p>
     * 常见用法：定义一个 adapter ，
     * 例如 Map 接口的实现类往往使用非静态成员类 (HashMap 使用 EntrySet) 来实现他们的集合视图，Set List 使用非静态成员类实现迭代器
     */
    private class innerClass {
        private int a;
        private int b;

        public void sayHi() {
            System.out.println(a);
            System.out.println(Outer.this.a);
        }
    }

    /**
     * 常见用法：1、作为共有的辅助类，仅当与他的外部类一起使用时才有意义，例如 HashMap 中的 Entry ？？？尬住。。是 Node 实现了 Map.Entry
     * why:因为 Node 的方法 getKey getValue setValue 并不需要访问该 Map 所以使用 static 修饰
     */
    private static class innerStaticClass {
        private int a;
        private int c;

        public void sayHi() {
            System.out.println(a);
            System.out.println();
        }
    }
}
