package com.liutao62.jni;

/**
 * @author liutao
 * @date 2023/3/21
 */
public class Demo {
    static {
        try {
            System.loadLibrary("HelloNative");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        HelloNative helloNative = new HelloNative();
        helloNative.hello();
    }
}
