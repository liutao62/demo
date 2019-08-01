package com.javasedemo;

public class Main {

    public static void main(String[] args) {
        System.out.println("A");
        new Main();
        new Main();

        updateX(x);
        updateY(y);
        System.out.println(x + " " + y);

        System.out.println(Test2.a);

        System.out.println(Test3.a);

        System.out.println(Test4.a);

    }

    public Main() {
        System.out.println("B");
    }

    {
        System.out.println("C");
    }

    static {
        System.out.println("D");
    }

    //--------------------------
    private static int x = 10;
    private static Integer y = 10;

    public static void updateX(int value) {
        value = 3 * value;
    }

    public static void updateY(Integer value) {
        value = 3 * value;
    }

    //-------------------
}
class Test2{
    public static final String a="JD";

    static {
        System.out.print("OK");
    }
}

class Test3{
    public static final String a=new String("JD");

    static {
        System.out.print("OK");
    }
}
class Test4{
    static {
        System.out.print("OK");
    }

    public static final String a=new String("JD");
}