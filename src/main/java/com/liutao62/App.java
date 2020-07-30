package com.liutao62;

import java.io.File;

public class App {
    public static void main(String[] args) {
        File file = new File("D:\\BaiduNetdiskDownload\\尚硅谷谷粒学院项目视频教程\\视频");
        File[] files = file.listFiles();
        for (File file1 : files) {
            File[] files1 = file1.listFiles();
            for (File file2 : files1) {
                System.out.println(file2.getName());
  //              file2.renameTo(new File(file2.getName().replace("尚硅谷-谷粒学院-", "")));
            }
        }
    }
}
