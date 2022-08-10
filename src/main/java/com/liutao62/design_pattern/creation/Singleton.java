package com.liutao62.design_pattern.creation;

/**
 * 单例对 OOP 特性的支持不友好
 * 单例会隐藏类之间的依赖关系
 * 单例对代码的扩展性不友好
 * 单例对代码的可测试性不友好
 * 单例不支持有参数的构造函数
 */
public class Singleton {
}

class InnerClass {
    public static class Instance {
        static Instance INSTANCE = new Instance();

        private Instance() {
        }
    }

    /**
     * 使用静态内部类，延迟初始化 + 避免锁
     *
     * @return
     */
    public static Instance getInstance() {
        return Instance.INSTANCE;
    }
}

enum Instance {
    INSTANCE
}
