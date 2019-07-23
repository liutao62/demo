package com.keywords_demo;

/**运行结果：
 *   SuperClass带参构造器
 SuperClass静态代码块
 SuperClass无参构造器
 SubClass带参构造器
 SubClass静态代码块
 SuperClass无参构造器
 SubClass无参构造器
 SuperClass无参构造器
 SubClass带参构造器
 staticdemo静态代码块
 6
 SuperClass方法
 SuperClass方法
 */
public class StaticDemo {
    /*
         SuperClass带参构造器        预先检查并加载
         SuperClass静态代码块
         SuperClass无参构造器
         SubClass带参构造器
         SubClass静态代码块
     */
    private static SuperClass instance1 = new SubClass();       //6.7行执行结果
    /*
         SuperClass无参构造器
         SubClass无参构造器
     */
    private static SuperClass instance2 = new SubClass(5);      //8.9执行结果
    /*
         SuperClass无参构造器
         SubClass带参构造器
     */
    static{
        System.out.println("staticdemo静态代码块");      //10        加载完毕后首先执行静态代码块
    }
    public static void main(String[] args) {
        System.out.println(6);                          //11
        instance1.print();
        instance2.print();                              //方法隐藏，static方法调用的本质是类名（编译类型）调用，
    }
}
class SuperClass {
    private static int i = 1;
    private static SuperClass superClass = new SuperClass(2);
    static {
        System.out.println("SuperClass静态代码块");          //2
    }
    public SuperClass() {
        System.out.println("SuperClass无参构造器");
    }          //3.6.8
    public SuperClass(int i) {
        System.out.println("SuperClass带参构造器");
    }          //1
    public static void print(){                             //12.13
        System.out.println("SuperClass方法");
    }
}
class SubClass extends SuperClass {
    private int i;
    private static SubClass subClass = new SubClass(4);
    static {
        System.out.println("SubClass静态代码块");        //5
    }
    public SubClass() {
        System.out.println("SubClass无参构造器");
    }        //7
    public SubClass(int i) {
        System.out.println("SubClass带参构造器");
    }        //4.9
    public static void print(){
        System.out.println("SubClass方法");
    }
}