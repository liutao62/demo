package com.javasedemo;

public class OuterClass {

    private float f = 1.0f;
    //插入代码到这里

    /* 非静态内部类不能有静态方法
    class InnerClass{
        public static float func(){return f;}
    }*/

    /* 抽象方法不能有方法体
    abstract class InnerClass{
        public abstract float func(){}
    }*/

    /* 静态上下文没有找到 f 。静态不能访问成员
    static class InnerClass{
        protected static float func(){return f;}
    }*/

    /* 同 1、3
    public class InnerClass{
        static float func(){return f;}
    }*/
}
