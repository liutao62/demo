package com.liutao62.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author liutao
 * @date 2023/11/7
 */
public class ProcessData {
    public static void main(String[] args) throws IOException {

//        ArrayList<String> batchFillList = Lists.newArrayList("S1863406463324520454","S1863406317294059522"
//        );
                                        ArrayList<String> batchFillList = new ArrayList<>();


        ArrayList<String> legBillList = Lists.newArrayList("1734437253186322435","d2d519f641ab4663890fdc73bcf9b3c1"
        );
//                                ArrayList<String> legBillList = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = JSONObject.parseObject("{\n" +
                "    \"bpmCallbackRequestId\": \"20231107a193eaa347a34f298c4af27c5c7c1\",\n" +
                "    \"bpmCallbackRequestTs\": 1699364596183,\n" +
                "    \"businessKey\": \"hrtm_leg_work_bill_card_1791302319647227913\",\n" +
                "    \"processDefinitionKey\": \"processcenter_3c845b96\",\n" +
                "    \"processEnd\": \"true\",\n" +
                "    \"processInstId\": \"f0d6d80c-7d63-11ee-832c-669b2f4041a6\",\n" +
                "    \"source\": \"HRTM\",\n" +
                "    \"tenantId\": \"0000LIBAKQR5A0O7IA0000_HRTM\",\n" +
                "    \"userId\": \"f9aa4f9f-ce5c-4445-990a-977fff157ff9\",\n" +
                "    \"yht_access_token\": \"YT5_YmlwLXByZS14eHh4LmRpd29yay5jb20=_MCwCFAGDfdc5ZjwCwpqOwcxea1P+2NbcAhQm6mB/8MAEMcHPFJpXlh+A+BmhJQ==_FjAwMDBMSUJBS1FSNUEwTzdJQTAwMDCQ+apPn85cREWZCpd//xV/+QAK6Z+p5YW1MjMwMQDIAwCYDyDyQhjBAcig1K6isMYLAAEwAEEAQQcAAAABMMiu5NepiwEAAAEwAAMwMDEDMV8zAMTjEUtlxPM+SmUaaXVhcC1hcGNvbS1tZXNzYWdlcGxhdGZvcm0AQQCQCA0FSkDVQ5GqjFHnkkHKygdkY2NvcmUwATA=\"\n" +
                "}");

        String yhtAccessToken = "bttSWQ2eWdiMG42U25SSlI3a1Y1aWpaTmVVY29Fb211VzVKQk8vMDdSV09Yd1doY1lpdzNrZ1dGcDB5ZjJUcjNMWF9fZXVjLnlvbnlvdWNsb3VkLmNvbQ..__a71fea3b7d8e490fbf9b53efbb28a85e_1701776594342dccore0iuap-apcom-workbenchfaa22960YT";
        String tenantId = "z3vf8q3c";
        String userId = "c810f94a-9612-45ca-8e6b-f93ae839b355";
        String domainUrl = "https://c2.yonyoucloud.com";

        jsonObject.put("yht_access_token",yhtAccessToken);
        jsonObject.put("tenantId",tenantId);
        jsonObject.put("userId",userId);

        for (String billId : legBillList) {
            jsonObject.put("bpmCallbackRequestId", UUID.randomUUID().toString());
            jsonObject.put("bpmCallbackRequestTs", System.currentTimeMillis());
            jsonObject.put("businessKey", "hrtm_leg_work_bill_card_" + billId);
            RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());
            Request request = new Request.Builder()
                    .url(domainUrl + "/yonbip-hr-tm/bpm/complete")
                    .method("POST", body)
                    .addHeader("yht_access_token", yhtAccessToken)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }

        for (String billId : batchFillList) {
            jsonObject.put("bpmCallbackRequestId", UUID.randomUUID().toString());
            jsonObject.put("bpmCallbackRequestTs", System.currentTimeMillis());
            jsonObject.put("businessKey", "hrtm_batch_fill_card_" + billId);
            RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());
            Request request = new Request.Builder()
                    .url(domainUrl + "/yonbip-hr-tm/bpm/complete")
                    .method("POST", body)
                    .addHeader("yht_access_token", yhtAccessToken)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }






    }
}
