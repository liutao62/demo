package com.liutao62.demo;

/**
 * 方法覆盖重写。两同两小一大
 * 两同：方法名和参数列表相同
 * 两小：返回类型、抛出异常小等于父类方法
 * 一大：访问类型大于等于父类方法
 */
public class OverrideDemo {
    public static void main(String[] args) {
        Super obj = new Sub();
        Sub.staticMethod();
    }
}


class Super extends OverrideDemo {
    protected OverrideDemo method(int i) throws RuntimeException {
        return null;
    }

    public static void staticMethod(){
        System.out.println("super");
    }
}

class Sub extends Super {
    @Override
    public Super method(int i) throws ArrayIndexOutOfBoundsException {
        return null;
    }
    //继承
//    public static void staticMethod(){}     //隐藏

    public static void staticMethod(int i){}     //重载
}