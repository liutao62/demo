package com.mytest;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        File dir = new File("F:/downloads/图解数据结构和算法/视频");
        File[] files = dir.listFiles();
        for (File file : files) {
          //  file.renameTo(new File(dir,))
            String replace = file.getName().replace("-尚硅谷-老韩图解Java数据结构和算法", "");
            file.renameTo(new File(dir,replace));
        }
    }
}
