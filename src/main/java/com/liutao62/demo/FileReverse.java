package com.liutao62.demo;

import java.io.*;

public class FileReverse {

    public static void main(String[] args) throws IOException {
        File srcFile = new File("D:/test.txt");
        File targetFile = new File("D:/test1.txt");
        StringBuffer strBuf = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"));
        int tempchar;
        while ((tempchar = bufferedReader.read()) != -1) {
            strBuf.append((char) tempchar);
        }
        bufferedReader.close();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8"));

        char[] chars = strBuf.toString().toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            writer.write(chars[i]);
        }
        writer.flush();
        writer.close();

    }
}
