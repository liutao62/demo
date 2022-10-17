import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2021/5/13 20:38
 * @description
 */
public class Demo {
    private static final int GIFT_SIZE = 10;

    public static void main(String[] args) {
        // 1_141526_524_main.json 1_141526_287.json 2_141529_814.json
        List<String> fileList = Arrays.asList("1_141526_524_main.json", "1_141526_287.json", "2_141529_814.json", "2_141529_488_main.json");
        fileList.forEach(fileName -> {
            StringBuilder builder = new StringBuilder();
            try (FileReader fileReader = new FileReader("/Users/liutao/Downloads/" + fileName)) {
                int read;
                while ((read = fileReader.read()) != -1) {
                    builder.append((char) read);
                }
            } catch (Exception e) {

            }
            List<Record> records = JSONArray.parseArray(builder.toString(), Record.class);
            List<Record> collect = records.stream().filter(record -> "20162".equals(record.staffCode) || "20104".equals(record.staffCode)).collect(Collectors.toList());
            System.out.println(collect);
            System.out.println("deal finish file name = " + fileName);
        });

        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        lookup.fi

    }

}

@Data
class Record {
    String staffCode, date, time;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
