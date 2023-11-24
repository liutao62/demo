package com.liutao62.report;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author liutao
 * @date 2023/11/17
 */
public class Test {

    public static void main(String[] args) {

        String s = DigestUtils.md5Hex("attend" + "liutaoq");
        System.out.println(s);
    }
}
