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

        ArrayList<String> batchFillList = Lists.newArrayList("BATCHFILL165213528072","BATCHFILL904062693379","S1861904761463767046","S1863835409392861190","S1864100709527977992","S1863597073067671556","BATCHFILL319505235974","BATCHFILL427768098823");
        //        ArrayList<String> batchFillList = new ArrayList<>();


//        ArrayList<String> legBillList = Lists.newArrayList("1865090991769255951","1868338304886767621","1868305620189315080","1868304967350616068","1868304185666568198","1868303464123596812","1868302759737950209","1868301368181653510","1868301007391293444","1868300904315224076","1868300629447278600","1868300577897709577","1868300466228559882","1868300019548291078","1868299907882811399","1868299272234467333","1868298301565042696","1868297459747782658","1868296403185827842","1868295673045057544","1868294745328975878","1868294247112245252","1868294092497092612","1868292855542841348","1868291086016839689","1868290759612432394","1868287976460517386","1868286773869674506","1868284205492338694","1868282985711665156","1868279103058083840","1868261347666427907","1867981891320676355","1867972871900889096","1867970793128853506","1867962581148237834","1867904530373410817","1867903568300736518","1867877532205842433","1867865111170383880","1867860661577449476","1867859888491200515","1867849408770998278","1867312022451912713","1867278564666638345","1867274896764567562","1866815919382069255","1866815730403508227","1866072984525471746","1866072486309265414","1864132346481475594","1863844214314893314","1863533112648007683","1858642613769863173","1857911155069026305","1857168976136634370","1857165376954040324","1856420578083733512");
        ArrayList<String> legBillList = new ArrayList<>();
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

        String yhtAccessToken = "0525f8fb-c60f-4e17-9550-uspacemobile__b2f19af1e822c0ab8f206a01764d3ca4_1696645236878dccore0yonbip-ec-based1852c41YT";
        String tenantId = "rmhauhuo";
        String userId = "41c32108-bcdf-475e-8b8d-8909969ca165";
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
