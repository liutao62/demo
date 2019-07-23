package com.mytest;

public class ExtendsTest {

}
class Super{
    public Super(){
    }
    public void say(){
        System.out.println("super say");
    }
}
class Sub extends Super{
    public Sub() {
    }

    public void say(){
        System.out.println("sub say");
    }

    public void useSuperSay(){

    }
}