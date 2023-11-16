package com.liutao62;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.Date;

/**
 * @author liutao
 * @date Created in 2020/8/27 16:23
 * @description sql 替换
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String sql = "select ID,TENANTID,STAFF_ID,STAFF_CODE,STAFF_NAME,ORG_ID,ORG_NAME,DEPT_ID,DEPT_NAME,PLACE_ID,PLACE_NAME, ADDRESS_DETAIL,sign_type,SIGNTIME,SIGNDATE,ATTENDANCE_TYPE,place_type,ISOUTSIDE,CREATOR,CREATIONTIME, MODIFIER,MODIFIEDTIME,TS,DR,in_and_out_type,attend_range,setting_out_time,real_out_time,ytenant_id, suspected_abnormality_status,import_instructions,type_id,extend_field,filepath from ts_attend_record_total where tenantid = ? and signtime>=? and signtime<=? and dept_id in(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) order by dept_id DESC , staff_id DESC , signdate DESC , signtime DESC";
        String param = "x2taznup(String), 2023-06-26(String), 2023-06-26 23:59:59(String), 1630286083872260101(String), 1752794751769247760(String), 1752619233635729418(String), 1720723036961243145(String), 1720724033381072905(String), 1630286942856806406(String), 1752775999948849155(String), 1720724136460288009(String), 1742304646319308800(String), 1752619242225664016(String), 1752619242225664015(String), 1752619242225664012(String), 1752619242225664011(String), 1752619242225664014(String), 1630286951446741003(String), 1752619242225664013(String), 1720723921711923203(String), 1753371703316054021(String), 1752619242225664010(String), 1752635640412897291(String), 1752794751769247756(String), 1630296073966190595(String), 1752794751769247758(String), 1752794751769247757(String), 1752796066029240328(String), 1720724462877802500(String), 1752794751769247759(String), 1749114918272499713(String), 1720724222359633927(String), 1753399096610652164(String), 1630286951446740999(String), 1752619242225664009(String), 1752619242225664008(String), 1752776300596559878(String), 1630286951446740995(String), 1720702386745901065(String), 1630286951446741007(String)";

        // if dbtype == null mysql else oracle
        String s = replaceSql(sql, param, null);
        System.out.println(s);
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
