package com.liutao62;

/**
 * @author liutao
 * @date 2024/2/26
 */
public class ExcelWriter {
    public static void main(String[] args) {
        // 同一文件写不同sheet
//        File result = new File("/Users/liutao/Downloads/yms配置差异.xlsx");
//        File tmp = new File("/Users/liutao/Downloads/tmp.xlsx");
//
//        ExcelWriter writer = null;
//        if (tmp.exists()) {
//            writer = EasyExcel.write().file(result)
//                    .autoCloseStream(false)
//                    .withTemplate(tmp)
//                    .needHead(true)
//                    .head(DemoData.class)
//                    .build();
//        } else {
//            writer = EasyExcel.write(tmp, DemoData.class).needHead(true).build();
//        }
//        WriteSheet sheet = EasyExcel.writerSheet(app).head(DemoData.class).needHead(true).build();
//        writer.write(objects, sheet);
//        writer.finish();
//
//        if (result.exists()) {
//            tmp.delete();
//            result.renameTo(tmp);
//        }
    }
}
