package com.liutao62.demo;

public class ConstructDemo {



    private class Super{

        //如果仅提供带参构造器，则报错父类没有默认得构造器
        /*public Super(String i){

        }*/
    }

    private class Sub extends Super{
        public Sub(){
            //隐式调用 Super()
        }
    }
}