package com.liutao62;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.sql.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date 2024/9/27
 */
@Slf4j
public class DataTransfer {

    private static List<Long> dateTime = Collections.synchronizedList(new ArrayList<>());

    private static final SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMATTER_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final List<String> COLUMN_NAMES = Lists.newArrayList("id","user_id","ytenant_id"
    );

    public static java.util.Date[] createDateArray(java.util.Date beginDate, java.util.Date endDate) {
        // 保证开始日期不晚于结束日期
        if (beginDate.after(endDate)) {
            java.util.Date tempDate = beginDate;
            beginDate = endDate;
            endDate = tempDate;
        }
        List<java.util.Date> retList = new ArrayList<>();
        java.util.Date date = beginDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        cal.set(Calendar.MINUTE, 0);
        //将秒至0
        cal.set(Calendar.SECOND, 0);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 0);
        do {
            retList.add(cal.getTime());
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
        } while (!date.after(endDate));
        return retList.toArray(new java.util.Date[0]);
    }

    public static void main(String[] args) throws InterruptedException {

        List<java.util.Date> collect = Arrays.stream(createDateArray(new java.util.Date(1704038400000l), new java.util.Date(1730366070000l))).collect(Collectors.toList());
        ExecutorService executorService = Executors.newWorkStealingPool();

        collect.stream().forEach(date -> {
            executorService.execute(() -> transfer(date, new java.util.Date(date.getTime() + 86400000)));
        });

        executorService.awaitTermination(3, TimeUnit.HOURS);
        System.out.println(dateTime);
    }

    private static void transfer(java.util.Date beginDate, java.util.Date end) {
        try (Connection prodConnection = DriverManager.getConnection("jdbc:mysql://jumper.diwork.com:33061/yonbip_hr_tm?characterEncoding=utf-8", "8074a156-c5af-413f-ae0e-4f31fa6817b7", "UM6QMrqazxF7qrAD");
             Statement statement = prodConnection.createStatement();
             Connection testConnection = DriverManager.getConnection("jdbc:mysql://dbproxy.diwork.com:12368/yonbip_hr_tm?characterEncoding=utf-8", "bip_hr_serv", "hbvzOoHcocB2y9SFtV8iWDjY5DKLXIjn");
             Statement testStatement = testConnection.createStatement();) {
            // 核心3 分库1
            //            ArrayList<String> tenant = Lists.newArrayList("vkx4mgh7","lmcwzu3e","tfuqrfic","dlqorvak","diyt1hqk","xgjh8jtw","hj0j4fxt","izx2uug0","s4rl0np9","h2tsp9zl","ebkitg4w","gxm3arh0"
//            );
            // 核心3 分库2
//            ArrayList<String> tenant = Lists.newArrayList("ftzokcrn","hvgvzpx0","hdywrzfg"
//            );
            // 核心4分库1
//            ArrayList<String> tenant = Lists.newArrayList("r47tbpsv","aae28shl","da3mojo8","fo02vw19","b96sc95");
            // 核心4总账
//            ArrayList<String> tenant = Lists.newArrayList("u8jdybet","srrh3hgp");
            // 核心4 分库4
//            ArrayList<String> tenant = Lists.newArrayList("gfcx92ph");
            // 核心1 用友集团
//            ArrayList<String> tenant = Lists.newArrayList("qyic8c7o");
            // 核心1 分库1
//            ArrayList<String> tenant = Lists.newArrayList("rdy91zio");
            // 核心2
            ArrayList<String> tenant = Lists.newArrayList("y6bzmfrx","trr51z1u","w34o47cq","wwrtk9r8","mbe008kc","ikkb9gi2","glroeeo3","kjevosbe","j9b55izl","dpxhi49c","ogz9urwc","t70rxy8a","nim5zstd","qmh8bs6c","ij44ambo","ckg75soz","u60uwe55","x0y0m8rn","eb8b58c3","p8s8sqd6","hqp3mg1j","rx69kmql","ton8l99g","cqmr5njx","xn9o50gt","vyos9rjy","dmpltzkc","lc6fpdmh","vz69bh15","fxrzzens","h27tpclq","b9wuchko","it1c7eoe","cwuhswon","bolmz333","itvqa0x6","sxljek2f","ryievz1l","sq4iimfp","n22w18oi","bnipy2cj","dsbuej9w","nbe2y23x","ppy6c63r","fe376zmc","qhwm54dz","fkdtrqg8","ipd1qn5s","obu25s4b","rkafzcia","ygzf9svt","da4g5qhf","lmmf8i8y","pxodp3oi","g7i9zxb7","rnqsuipc","s1mqekvo","aa7i8snb","nakpg97z","w8k9ito4","v1bxz4av"
            );
            tenant.forEach(tenantid -> dataTransfer(tenantid, beginDate, end, statement, testStatement));

            // 查询租户下最早的日报日期
//            String earliestCalendarSql = "select max(signdate) from yonbip_hr_tm.ts_attend_record_archive where ytenant_id = '" + tenantid + "'";
//            ResultSet resultSet = testStatement.executeQuery(earliestCalendarSql);
//            Date begin = new Date(1709022834000l);
//            if (resultSet.next()) {
//                begin = resultSet.getDate(1) == null ? begin : resultSet.getDate(1);
//            }

//            Calendar instance = Calendar.getInstance();
//            instance.setTime(begin);
//
//            java.util.Date beginDate = instance.getTime();
//            instance.add(Calendar.DATE, 1);
//            java.util.Date end = instance.getTime();
            System.out.println(String.format("开始时间:%s, 结束时间:%s", beginDate.toLocaleString(), end.toLocaleString()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private static void dataTransfer(String tenantid, java.util.Date beginDate, java.util.Date end, Statement statement, Statement testStatement) {
        String querySql = "select " + COLUMN_NAMES.stream().collect(Collectors.joining(","))
                + " from iuap_apdoc_basedoc.bd_staff where ytenant_id = '" + tenantid + "' and enable = 1 and dr = 0;";

        String insertColumnPart = "replace into yonbip_hr_tm.ts_daystat_summary (" + COLUMN_NAMES.stream().collect(Collectors.joining(",")) + ",early_length,late_length" + ") values ";

        StringBuilder sqlbuilder = new StringBuilder(insertColumnPart);
        StopWatch stopWatch = new StopWatch("dataTransfer");
        try {
            stopWatch.start("executeQuery");
            ResultSet resultSet1 = statement.executeQuery(querySql);
            stopWatch.stop();
            stopWatch.start("batchInsert");
            int count = 0;
            while (resultSet1.next()) {

                Date calendar = (Date) resultSet1.getObject("CALENDAR");
                LocalDateTime firstSign = (LocalDateTime) resultSet1.getObject("SIGNBEGINTIME");
                LocalDateTime lastSign = (LocalDateTime) resultSet1.getObject("SIGNENDTIME");
                Long earlyLength = null;
                Long lateLength = null;
                if (firstSign != null) {
                    java.util.Date date = Date.from(firstSign.toInstant(ZoneOffset.ofHours(8)));
                    earlyLength = (calendar.getTime() - date.getTime()) / 1000;
                }
                if (lastSign != null){
                    java.util.Date date = Date.from(lastSign.toInstant(ZoneOffset.ofHours(8)));
                    lateLength = (date.getTime() - calendar.getTime()) / 1000;
                }
                if (earlyLength == null && lateLength == null) {
                    continue;
                }


                sqlbuilder.append("(");
                for (String columnName : COLUMN_NAMES) {
                    Object object = resultSet1.getObject(columnName);
                    if (object instanceof Date) {
                        object = "'" + ((Date) object).toLocaleString() + "'";
                    } else if (object instanceof String) {
                        if (columnName.equals("ID")) {
                            object = object + tenantid;
                        }
                        object = "'" + ((String) object).replace("'","") + "'";
                    } else if (object instanceof LocalDateTime) {
                        object = "'" + ((LocalDateTime) object).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'";
                    }
                    sqlbuilder.append(object).append(",");
                }


                sqlbuilder.append(earlyLength).append(",");
                sqlbuilder.append(lateLength).append(",");

                sqlbuilder.deleteCharAt(sqlbuilder.length() - 1).append("),");

                if (count++ == 1000) {
                    sqlbuilder.deleteCharAt(sqlbuilder.length() - 1).append(";");
                    boolean execute = testStatement.execute(sqlbuilder.toString());
                    sqlbuilder = new StringBuilder(insertColumnPart);
                    count = 0;
                }
            }
            if (count != 0 && sqlbuilder.toString().endsWith(",")) {
                sqlbuilder.deleteCharAt(sqlbuilder.length() - 1).append(";");
                testStatement.execute(sqlbuilder.toString());
            }
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            dateTime.add(beginDate.getTime());
        }

    }
}
