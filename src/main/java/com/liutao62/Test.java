package com.liutao62;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @date Created in 2020/11/17 23:26
 * @description
 */
public class Test {
    public static void main(String[] args) {

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("123","123");
        objectObjectHashMap.put("234","123");
        Collection<Object> values = objectObjectHashMap.values();

        JavaBean javaBean = new JavaBean();
        javaBean.setId("id");
        javaBean.setName("fsfsfs");
        String jsonString = JSONObject.toJSONString(javaBean);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.put("age",11);
        try {

            JavaBean javaObject = JSON.toJavaObject(jsonObject, JavaBean.class);
            System.out.println();
        }catch (Exception e){
            System.out.println();
        }
    }

    private static class TimeWaiting implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                TimeUnit.SECONDS.sleep(100);
            }
        }
    }

    private static class Waiting implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    Waiting.class.wait();
                }
            }
        }
    }

    private static class Blocked implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    TimeUnit.SECONDS.sleep(100);
                }
            }
        }
    }
}

@Data
class JavaBean{
    String id, name;
}
