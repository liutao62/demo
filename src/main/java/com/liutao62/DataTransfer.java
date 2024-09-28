package com.liutao62;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.sql.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    private static final List<String> COLUMN_NAMES = Lists.newArrayList("ID", "TENANTID", "BU_ID", "STAFF_ID", "STAFFJOB_ID", "staff_code", "STAFF_NAME", "SPACE_ID", "ORG_ID", "ORG_VID", "ORG_NAME", "DEPT_ID", "DEPT_VID", "DEPT_NAME", "PLACE_ID", "PLACE_NAME", "ADDRESS_DETAIL", "LNG", "LAT", "apply_type", "sign_type", "SIGN_STATUS", "SIGN_TIME", "SIGNDATE", "ATTENDANCE_TYPE", "MEMBER_ID", "ISOUTSIDE", "OUTSIDE_TYPE", "OUTSIDE_TYPENAME", "OUTSIDEPLACE", "approve_status", "approver", "approver_name", "REMARK", "FILEPATH", "INSTANCEID", "IS_ROOT", "DEVICE_ID", "DEVICE_NAME", "OUTSIDE_PROJECT_ID", "OUTSIDE_PROJECT_NAME", "SHIFT_ID", "SHIFT_NAME", "CREATOR", "CREATIONTIME", "MODIFIER", "MODIFIEDTIME", "TS", "DR", "MANUAL_FILL", "APP_NAME", "ORIG_SIGN_TIME", "IMEI", "scheduled_shift_status", "ytenant_id", "in_and_out_type", "attend_range", "setting_out_time", "real_out_time", "suspected_abnormality_status", "import_instructions", "extend_field", "isWfControlled", "verifystate", "returncount", "audit_time", "auditor", "auditorId", "audit_date", "status", "attend_record_define", "TIME_ZONE", "SYSTEM_TIME"
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

    public static void main(String[] args) {

        long[] longs = {1691942400000l, 1692028800000l, 1679673600000l, 1679760000000l, 1682265600000l, 1682352000000l, 1679932800000l, 1680019200000l, 1687795200000l, 1687881600000l, 1680019200000l, 1680105600000l, 1687881600000l, 1687968000000l, 1682438400000l, 1682524800000l, 1680710400000l, 1680796800000l, 1685462400000l, 1685548800000l, 1687968000000l, 1688054400000l, 1685548800000l, 1685635200000l, 1680796800000l, 1680883200000l, 1685635200000l, 1685721600000l, 1680883200000l, 1680969600000l, 1682524800000l, 1682611200000l, 1724169600000l, 1724256000000l, 1685894400000l, 1685980800000l, 1681488000000l, 1681574400000l, 1685980800000l, 1686067200000l, 1681660800000l, 1681747200000l, 1681142400000l, 1681228800000l, 1688486400000l, 1688572800000l, 1704902400000l, 1704988800000l, 1681315200000l, 1681401600000l, 1681401600000l, 1681488000000l, 1691337600000l, 1691424000000l, 1679414400000l, 1679500800000l, 1681920000000l, 1682006400000l, 1683475200000l, 1683561600000l, 1686240000000l, 1686326400000l, 1688659200000l, 1688745600000l, 1726243200000l, 1726329600000l, 1691596800000l, 1691683200000l, 1691683200000l, 1691769600000l, 1726588800000l, 1726675200000l, 1683648000000l, 1683734400000l, 1705420800000l, 1705507200000l, 1723478400000l, 1723564800000l, 1723564800000l, 1723651200000l, 1705507200000l, 1705593600000l, 1720454400000l, 1720540800000l, 1723651200000l, 1723737600000l, 1682956800000l, 1683043200000l, 1726848000000l, 1726934400000l, 1705593600000l, 1705680000000l, 1727020800000l, 1727107200000l, 1686844800000l, 1686931200000l, 1686931200000l, 1687017600000l, 1725638400000l, 1725724800000l, 1725724800000l, 1725811200000l, 1693411200000l, 1693497600000l, 1725811200000l, 1725897600000l, 1705939200000l, 1706025600000l, 1721923200000l, 1722009600000l, 1706025600000l, 1706112000000l, 1722873600000l, 1722960000000l, 1692633600000l, 1692720000000l, 1722268800000l, 1722355200000l, 1727280000000l, 1727366400000l, 1725984000000l, 1726070400000l, 1727366400000l, 1727452800000l, 1726070400000l, 1726156800000l, 1722355200000l, 1722441600000l, 1721232000000l, 1721318400000l, 1721318400000l, 1721404800000l, 1703692800000l, 1703779200000l, 1700150400000l, 1700236800000l, 1704124800000l, 1704211200000l, 1725465600000l, 1725552000000l, 1700496000000l, 1700582400000l, 1725552000000l, 1725638400000l, 1699459200000l, 1699545600000l, 1694793600000l, 1694880000000l, 1694966400000l, 1695052800000l, 1699545600000l, 1699632000000l, 1693843200000l, 1693929600000l, 1693929600000l, 1694016000000l, 1694016000000l, 1694102400000l, 1699804800000l, 1699891200000l, 1694102400000l, 1694188800000l, 1697644800000l, 1697731200000l, 1719158400000l, 1719244800000l, 1719417600000l, 1719504000000l, 1699200000000l, 1699286400000l, 1699286400000l, 1699372800000l, 1697990400000l, 1698076800000l, 1708358400000l, 1708444800000l, 1706716800000l, 1706803200000l, 1719763200000l, 1719849600000l, 1719244800000l, 1719331200000l, 1706198400000l, 1706284800000l, 1719849600000l, 1719936000000l, 1719331200000l, 1719417600000l, 1706457600000l, 1706544000000l, 1708876800000l, 1708963200000l, 1703433600000l, 1703520000000l, 1718640000000l, 1718726400000l, 1718726400000l, 1718812800000l, 1706889600000l, 1706976000000l, 1718035200000l, 1718121600000l, 1706544000000l, 1706630400000l, 1706976000000l, 1707062400000l, 1718121600000l, 1718208000000l, 1707062400000l, 1707148800000l, 1707148800000l, 1707235200000l, 1707235200000l, 1707321600000l};
        List<java.util.Date> collect = Arrays.stream(longs).mapToObj(java.util.Date::new).distinct().sorted().collect(Collectors.toList());

        List<java.util.Date> collect1 = collect.stream().filter(date -> collect.contains(new java.util.Date(date.getTime() + 86400000))).collect(Collectors.toList());

        collect1.stream().parallel().forEach(date -> {
            transfer(date, new java.util.Date(date.getTime() + 86400000));
        });

        System.out.println(dateTime);
    }

    private static void transfer(java.util.Date beginDate, java.util.Date end) {
        try (Connection prodConnection = DriverManager.getConnection("jdbc:mysql://jumper.diwork.com:33061/yonbip_hr_tm?characterEncoding=utf-8", "2d758aea-2248-4937-be05-943058138732", "f1toVZyLj0KItctR");
             Statement statement = prodConnection.createStatement();
             Connection testConnection = DriverManager.getConnection("jdbc:mysql://dbproxy.diwork.com:12368/yonbip_hr_tm?characterEncoding=utf-8", "bip_hr_serv", "hbvzOoHcocB2y9SFtV8iWDjY5DKLXIjn");
             Statement testStatement = testConnection.createStatement();) {
            String tenantid = "qyic8c7o";

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
            dataTransfer(tenantid, beginDate, end, statement, testStatement);

        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    private static void dataTransfer(String tenantid, java.util.Date beginDate, java.util.Date end, Statement statement, Statement testStatement) {
        String querySql = "select " + COLUMN_NAMES.stream().collect(Collectors.joining(","))
                + " from yonbip_hr_tm.ts_attend_record where ytenant_id = '" + tenantid + "' and SIGNDATE >= '" + DATETIME_FORMATTER.format(beginDate) + "' and SIGNDATE < '" + DATETIME_FORMATTER.format(end) + "';";

        String insertColumnPart = "replace into yonbip_hr_tm.ts_attend_record_archive (" + COLUMN_NAMES.stream().collect(Collectors.joining(",")) + ") values ";

        StringBuilder sqlbuilder = new StringBuilder(insertColumnPart);
        StopWatch stopWatch = new StopWatch("dataTransfer");
        try {
            stopWatch.start("executeQuery");
            ResultSet resultSet1 = statement.executeQuery(querySql);
            stopWatch.stop();
            stopWatch.start("batchInsert");
            int count = 0;
            while (resultSet1.next()) {
                sqlbuilder.append("(");
                for (String columnName : COLUMN_NAMES) {
                    Object object = resultSet1.getObject(columnName);
                    if (object instanceof Date) {
                        object = "'" + ((Date) object).toLocaleString() + "'";
                    } else if (object instanceof String) {
                        object = "'" + ((String) object).replace("'","") + "'";
                    } else if (object instanceof LocalDateTime) {
                        object = "'" + ((LocalDateTime) object).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'";
                    }
                    sqlbuilder.append(object).append(",");
                }
                sqlbuilder.deleteCharAt(sqlbuilder.length() - 1).append("),");

                if (count++ == 1000) {
                    sqlbuilder.deleteCharAt(sqlbuilder.length() - 1).append(";");
                    boolean execute = testStatement.execute(sqlbuilder.toString());
                    log.info("执行结果:{}", execute);
                    sqlbuilder = new StringBuilder(insertColumnPart);
                    count = 0;
                }
            }
            if (count != 0){
                sqlbuilder.deleteCharAt(sqlbuilder.length() - 1).append(";");
                testStatement.execute(sqlbuilder.toString());
            }
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        } catch (Exception e) {
            e.printStackTrace();
            dateTime.add(beginDate.getTime());
        }

    }
}
