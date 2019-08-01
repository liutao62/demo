package com.javasedemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MultiplicationTable {
    public static void main(String[] args) throws IOException {
        String fileUrl = "C:/Users/Administrator/Desktop/t7.txt";
        FileWriter fileWriter = new FileWriter(fileUrl);
        FileReader fileReader = new FileReader(fileUrl);
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i < 10;i++){
            for(int j = 1;j <= i;j++){
                fileWriter.append(j + "*" + i + "=" + j * i + " ");
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
        int read ;
        while ((read = fileReader.read()) != -1){
            System.out.print((char) read);
        }
        fileReader.close();
    }
}
