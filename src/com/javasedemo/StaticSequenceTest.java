package com.javasedemo;

public class StaticSequenceTest {
    public static void main(String[] args) {
        System.out.println(Demo1.COUNTRY);
        System.out.println(Demo2.COUNTRY);

        MyVal1 m = new Demo3();
        System.out.println(((Demo3) m).sss());
    }
}
class Demo1{
    public static final String COUNTRY = "china good";
    static {
        System.out.println("Demo1 static");
    }
}
class Demo2{
    public static final String COUNTRY = new String("china");
    static{
        System.out.println("Demo2 static");
    }
}
interface MyVal extends MyVal1,MyVal2{
    double getValue();
}
interface MyVal1{
    int getValue1();
}
interface MyVal2{
    String getValue2();
    public static String say(){
        return "slkj";
    }
    default int sss(){
        int a = 0;
        try {
            return
                    a;
        }catch (Exception e){
            return -1;
        }finally {
            ++a;
        }
    }
}
class Demo3 implements MyVal{

    @Override
    public double getValue() {
        return 0;
    }

    @Override
    public int getValue1() {
        return 0;
    }

    @Override
    public String getValue2() {
        return null;
    }
}