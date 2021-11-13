package com.liutao62;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liutao
 * @date Created in 2020/8/27 16:23
 * @description sql 替换
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String sql = "(SELECT address_detail,attend_range,attendance_type,creationtime,creator,dept_id,dept_name,id,import_instructions,in_and_out_type,isoutside,modifiedtime,modifier,org_id,org_name,place_id,place_name,place_type,sign_type,signdate,signtime,staff_code,staff_id,staff_name,tenantid,ts,ytenant_id FROM ts_attend_record_total_2021 WHERE tenantid=? AND signtime>=? and signtime<=? and dept_id in(?,?,?,?,?,?,?,?,?,?,?) ORDER BY dept_id DESC,staff_id DESC,signdate DESC,signtime DESC) UNION (SELECT address_detail,attend_range,attendance_type,creationtime,creator,dept_id,dept_name,id,import_instructions,in_and_out_type,isoutside,modifiedtime,modifier,org_id,org_name,place_id,place_name,place_type,sign_type,signdate,signtime,staff_code,staff_id,staff_name,tenantid,ts,ytenant_id FROM ts_attend_record_total WHERE tenantid=? AND signtime>=? and signtime<=? and dept_id in(?,?,?,?,?,?,?,?,?,?,?) ORDER BY dept_id DESC,staff_id DESC,signdate DESC,signtime DESC)";
        String param = "ve8zdg1u(String), 2021-06-17(String), 2021-07-17 23:59:59(String), 2271566525911296(String), 2254218859991296(String), 2293753674109184(String), 2233227016687872(String), 2254218171519232(String), 2271566907134208(String), 2293784615113216(String), 2342179009843456(String), 2254217547124992(String), 2271559783584000(String), 2253397195510016(String), ve8zdg1u(String), 2021-06-17(String), 2021-07-17 23:59:59(String), 2271566525911296(String), 2254218859991296(String), 2293753674109184(String), 2233227016687872(String), 2254218171519232(String), 2271566907134208(String), 2293784615113216(String), 2342179009843456(String), 2254217547124992(String), 2271559783584000(String), 2253397195510016(String)";

        // if dbtype == null mysql else oracle
        String s = replaceSql(sql, param, null);
        System.out.println(s);

        Class<Admin> userClass = Admin.class;
        Annotation[] annotations = userClass.getAnnotations();
//        builerMethod();


        System.out.println("----------------sort before");

        List<AttendRecord> attendRecords = Stream.of(
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(10774000)),
                new AttendRecord().setSigndate(new Date(1626883200000l)).setSigntime(new Date(14379000)),
                new AttendRecord().setSigndate(new Date(1626883200000l)).setSigntime(new Date(7179000)),
                new AttendRecord().setSigndate(new Date(1626883200000l)).setSigntime(new Date(57579000)),
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(57039000)),
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(21039000)),
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(3039000))
        ).collect(Collectors.toList());
        attendRecords.forEach(System.out::println);
        System.out.println("----------------sort after");

        attendRecords = Stream.of(
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(10774000)),
                new AttendRecord().setSigndate(new Date(1626883200000l)).setSigntime(new Date(14379000)),
                new AttendRecord().setSigndate(new Date(1626883200000l)).setSigntime(new Date(7179000)),
                new AttendRecord().setSigndate(new Date(1626883200000l)).setSigntime(new Date(57579000)),
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(57039000)),
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(21039000)),
                new AttendRecord().setSigndate(new Date(1626969600000l)).setSigntime(new Date(3039000))
        ).peek(recored -> recored.setSigndate(null)).collect(Collectors.toList());

        attendRecords.forEach(System.out::println);




    }



    /**
     * @param sql
     * @param param
     * @return
     * @description sql 替换
     */
    public static String replaceSql(String sql, String param, String dbType) {
        String[] split = param.split(",");
//        List<Object> collect = Arrays.stream(split).map(s -> "null".equals(s.trim()) ? null : s.substring(0, s.indexOf("("))).collect(Collectors.toList());
//
//        StringBuilder sb = new StringBuilder();
//        char[] chars = sql.toCharArray();
//        Iterator<Object> iterator = collect.iterator();
//        for (char c : chars) {
//            if (c == '?') {
//                Object next = iterator.next();
//                sb.append(next == null ? null : "'" + next + "'");
//            } else {
//                sb.append(c);
//            }
//        }
////        sql.replaceAll("\\?","2");
//
//        System.out.println("----------------");
//        System.out.println(sql);

        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (str.contains("(")) {
                String type = str.substring(str.indexOf("(") + 1, str.length() - 1);
                str = str.substring(0, str.indexOf("("));
                switch (type) {
                    case "Integer":
                    case "BigDecimal":
                        sql = sql.replaceFirst("\\?", str);
                        break;
                    case "Timestamp":
                        if (dbType == null) {
                            sql = sql.replaceFirst("\\?", "'" + str.substring(0, str.indexOf(".")) + "'");
                        } else {
                            sql = sql.replaceFirst("\\?", "to_date('" + str.substring(0, str.indexOf(".")) + "','yyyy-mm-dd hh24:mi:ss')");
                        }
                        break;
                    default:
                        sql = sql.replaceFirst("\\?", "'" + str + "'");
                        break;
                }

            } else {
                sql = sql.replaceFirst("\\?", "null");
            }
        }
        sql = sql.replaceAll("' ", "'");

        return sql;
    }
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
class AttendRecord {
    Date signdate;
    Date signtime;

    @Override
    public String toString() {
        return new Date(signdate.getTime() + signtime.getTime()).toLocaleString();
    }
}

@Data
@Accessors(chain = true)
@AllArgsConstructor
@MyAnnotation
class Admin {
    String id;
    String innercode;
}
