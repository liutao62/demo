package com.liutao62.effective_java;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author liutao
 * @date Created in 2020/8/16 15:28
 * @description
 */
public class BuilderDemo {
    public static void main(String[] args) {
        MyObject obj = new MyObject.MyObjectBuilder(1, 2).setC(3).setD(4).setE(5).setF(6).builder();
        System.out.println(obj);
    }
}

@ToString
class MyObject {
    private final int a, b, c, d, e, f;

    public MyObject(MyObjectBuilder builder) {
        a = builder.a;
        b = builder.b;
        c = builder.c;
        d = builder.d;
        e = builder.e;
        f = builder.f;
    }

    @Setter
    @Accessors(chain = true)
    public static class MyObjectBuilder implements Builder<MyObject> {
        // Required parameters
        private final int a, b;

        // Optional parameters
        private int c, d, e, f;

        public MyObjectBuilder(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public MyObject builder() {
            return new MyObject(this);
        }
    }
}

interface Builder<T> {
    T builder();
}
