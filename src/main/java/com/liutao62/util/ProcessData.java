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

        ArrayList<String> batchFillList = Lists.newArrayList("S1536210176116785391",
                "S1536210279196000261",
                "S1546244774499450883",
                "S1567201732402348038",
                "S1674096527736832009",
                "S1669027976685027330",
                "S1791338964418297861",
                "S1787440706085715973",
                "S1805803950436777987",
                "S1809895224613273607",
                "S1808615831165206536",
                "S1808615393079066625",
                "S1810617131286921225",
                "S1810617096946581508",
                "S1810617251546005505",
                "S1810617200006397956",
                "S1828718563019456518",
                "S1828713692518678528",
                "BATCHFILL422293643266",
                "S1848563794415452165",
                "S1850682158185185282",
                "S1844135657363472388",
                "S1850712626698387465",
                "S1850706579377618948",
                "BATCHFILL722127802374",
                "S1841157836293275656",
                "S1846942203751432194",
                "BATCHFILL594084470790",
                "S1844755575763107840",
                "S1850991550449319943",
                "S1838259704018501638",
                "S1836591083461541891",
                "S1850560627790577666",
                "S1850681900495536133",
                "S1842674054238044165",
                "BATCHFILL885321355272",
                "BATCHFILL843093442567",
                "S1850567010111979523",
                "S1851492163262611464",
                "S1851443363844194313",
                "S1850706716823388166",
                "S1841063304056274947",
                "S1850764389637423110",
                "S1850764570026049540",
                "S1841991532395102217",
                "S1850764913630248964",
                "S1851468137200353289",
                "S1850764827724087299",
                "S1850766064681484289",
                "S1850766288012967940",
                "S1850765935825649665",
                "S1850766571487625216",
                "S1850766133385756674",
                "S1850766708911374337",
                "S1850766391092183042",
                "S1850766854948651014",
                "S1844136043910529029",
                "S1850766571487625218",
                "S1844695626609590275",
                "S1850506330822410246",
                "S1848564455855620105",
                "S1850992117385003015",
                "S1846103173300224001",
                "S1851439103229820934",
                "BATCHFILL768362729477",
                "BATCHFILL049924485120",
                "S1846217316357898243",
                "S1851502196291010567",
                "S1850506201965002754",
                "S1850769509238439943",
                "BATCHFILL551071477766",
                "S1851486631329529857",
                "S1848547782777372672",
                "BATCHFILL794034049025",
                "BATCHFILL767715147776",
                "S1850566829723353097",
                "S1850205382463979523",
                "BATCHFILL836983721985",
                "S1850582283024072704",
                "S1850582231491280904",
                "S1850675793058856967",
                "S1850706871435395078",
                "S1850638985173925893",
                "S1851514771963641865");

        ArrayList<String> legBillList = Lists.newArrayList("1851416820939489285",
                "1851339073448312840",
                "1851594331944648709");

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

        jsonObject.put("yht_access_token","6d8a6a7d-9476-4ec9-8464-uspacemobile__c1e4c1a5b21d0d4bc49c0d2c47cbbd4e_1699340788502dccore0yonbip-ec-base05f4fdc3YT");
        jsonObject.put("tenantId","yxuxnbwt");
        jsonObject.put("userId","2672a0ba-9807-4555-ba27-c521977fa7a7");

        for (String billId : legBillList) {
            jsonObject.put("bpmCallbackRequestId", UUID.randomUUID().toString());
            jsonObject.put("bpmCallbackRequestTs", System.currentTimeMillis());
            jsonObject.put("businessKey", "hrtm_leg_work_bill_card_" + billId);
            RequestBody body = RequestBody.create(mediaType, jsonObject.toJSONString());
            Request request = new Request.Builder()
                    .url("https://c1.yonyoucloud.com/yonbip-hr-tm/bpm/complete")
                    .method("POST", body)
                    .addHeader("yht_access_token", "6d8a6a7d-9476-4ec9-8464-uspacemobile__c1e4c1a5b21d0d4bc49c0d2c47cbbd4e_1699340788502dccore0yonbip-ec-base05f4fdc3YT")
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
                    .url("https://c1.yonyoucloud.com/yonbip-hr-tm/bpm/complete")
                    .method("POST", body)
                    .addHeader("yht_access_token", "6d8a6a7d-9476-4ec9-8464-uspacemobile__c1e4c1a5b21d0d4bc49c0d2c47cbbd4e_1699340788502dccore0yonbip-ec-base05f4fdc3YT")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }






    }
}
