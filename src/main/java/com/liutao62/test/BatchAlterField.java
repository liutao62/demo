package com.liutao62.test;

import java.io.*;

/**
 * @author liutao
 * @date Created in 2020/11/17 16:39
 * @description
 */
public class BatchAlterField {
    public static void main(String[] args) {
        File srcFile = new File("D:\\yonyou\\hrcloud\\attend2005\\HR_HRKQ\\nccloud\\script\\dbcreate\\SQLSERVER\\00001\\tb_hr_hrkq_attend.sql");
        String targetFile = "C:/Users/liutao/Desktop/alter_dameng.sql";
        BufferedWriter writer = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            boolean end = true;
            String tableName = null;
            while ((line = bufferedReader.readLine()) != null) {
                int index = line.indexOf("create table");
                if (index > -1) {
                    tableName = line.substring(line.indexOf("create table") + "create table".length(), line.indexOf("("));
//                    System.out.println("create table " + tableName.replace("ts_","T_") + " as select * from " + tableName + ";");
                }
                int dateIndex = line.indexOf(" datetime");
                if (dateIndex > -1) {
                    System.out.println("alter table " + tableName + "modify " + line.substring(0, line.indexOf(" datetime")) + " datetime;");
                }
            }
        } catch (Exception e) {

        }
    }
}
