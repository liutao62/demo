package com.javasedemo;

public class Base{
    private String name = "base";
    public Base(){
        say();
    }
    public void say(){
        System.out.println(name);
    }

    static class Sub extends Base{
        private String name = "sub";

        public Sub(){
            
        }
        public void say(){
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        Base b = new Sub();
    }
}
