package com.liutao62;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date 2024/1/13
 */
public class ExcelReader {
    public static void main(String[] args) {
        String fileName = "/Users/liutao/Downloads/SYS_ALL_TAB_COLUMNS.xlsx";
        String core1 = "/Users/liutao/Downloads/information_schema_COLUMNS.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭

        List<DemoData> onlineColumnList= new ArrayList<>(18000);
        List<DemoData> premise = new ArrayList<>(18000);
        EasyExcel.read(fileName, DemoData.class, new ReadListener<DemoData>() {
            @Override
            public void invoke(DemoData o, AnalysisContext analysisContext) {
                o.setColumnName(o.getColumnName().toLowerCase());
                o.setTableName(o.getTableName().toLowerCase());
                premise.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();

        EasyExcel.read(core1, DemoData.class, new ReadListener<DemoData>() {
            @Override
            public void invoke(DemoData o, AnalysisContext analysisContext) {
                o.setColumnName(o.getColumnName().toLowerCase());
                o.setTableName(o.getTableName().toLowerCase());
                onlineColumnList.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();


        onlineColumnList.removeAll(premise);

        Map<String, List<DemoData>> collect = onlineColumnList.stream().collect(Collectors.groupingBy(DemoData::getTableName));

        String result = "/Users/liutao/Downloads/result.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(result, DemoData.class).sheet("回填缺失表和字段").doWrite(onlineColumnList);

//        System.out.println(collect);
        collect.forEach((k, v) -> {
            System.out.print("tableName:" + k + " fields:");
            v.forEach(v1 -> System.out.print(v1.getColumnName() + ","));
            System.out.println();
        });

    }

    @Data
    public static class DemoData {
        private String tableName;
        private String columnName;

        public DemoData() {

        }

        public DemoData(String columnName, String tableName) {
            this.columnName = columnName;
            this.tableName = tableName;
        }
    }
}
