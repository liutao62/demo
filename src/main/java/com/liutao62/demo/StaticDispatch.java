package com.liutao62.demo;

/**
 * 静态分派根据编译类型调用（调用其他类的方法），动态分派根据实际类型（调用父子类的方法）
 */
public class StaticDispatch {
    static class Animal {}
    static class Cat extends Animal {}
    static class Dog extends Animal {}

    public void enjoy(Animal obj) {
        System.out.println("动物的叫声，至于是哪种动物，还无法确定");
    }

    public void enjoy(Cat obj) {
        System.out.println("喵喵喵喵~");
    }

    public void enjoy(Dog obj) {
        System.out.println("汪汪汪汪~");
    }

    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        StaticDispatch receiver = new StaticDispatch();

        receiver.enjoy(dog);//point1
        receiver.enjoy(cat);//point2

        receiver.enjoy((Dog)dog);//point3

        dog = new Cat();
        receiver.enjoy(dog);//point4
    }
}
