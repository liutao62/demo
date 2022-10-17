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
        String sql = "select tbr.id tbr_id,tbr.name tbr_name,tbr.ismain,tbr.parent,tbr.orderNum, grp.id tbr_parent,tbr.childrenField, tbr.align,tbr.subid tbr_subid,tbr.system tbr_system,tbr.cStyle,tbr.terminalType, item.id item_id,item.toolbar,item.name item_name,item.command, case when (item.authid is not null and item.authid!='') then item.authid else cmd.authid end as authid, item.type,item.style,item.text, cmd.action, null as text_resid, item.imgsrc,item.parent item_parent,item.order, item.subid item_subid,item.system item_system, item.parameter,item.cDataRule, ifnull(?,(select iDefTplId from bill_base where cBillNo = ? and tenant_id=?)) currentTplid,item.icon, item.cExtProps from bill_toolbar tbr left join billtplgroup_base grp on grp.ibillid = (select id from bill_base where cBillNo = ? and tenant_id=?) and tbr.parent = grp.cCode and grp.tenant_id=? left join (select * from bill_toolbaritem where billnumber = ? and tenant_id=? and (auth_level>=? or auth_level is null) ) item on tbr.billnumber = item.billnumber and tbr.name = item.toolbar left join (select * from bill_command where billnumber = ? and tenant_id = ?) cmd on item.billnumber = cmd.billnumber and item.command = cmd.name and cmd.tenant_id=? where tbr.billnumber = ? and tbr.terminalType=? and tbr.tenant_id=? and (tbr.parent is null or tbr.parent = '' or ( grp.ibillid = (select id from bill_base where cBillNo = ? and tenant_id=?) and grp.iTplId = ifnull(?,(select iDefTplId from bill_base where cBillNo = ? and tenant_id=?)))) and tbr.tplmode = (select iTplMode from billtemplate_base where id = ifnull(?,(select iDefTplId from bill_base where cBillNo = ? and tenant_id=?)) and tenant_id=? ) order by tbr.id,item.order ";
        String param = "1168(Long), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), 0000L21IWA6Y25Y4G30000(String), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), 1(Short), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), 0000L21IWA6Y25Y4G30000(String), hrtm_business_trip_apply_card(String), 1(String), 0000L21IWA6Y25Y4G30000(String), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), 1168(Long), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), 1168(Long), hrtm_business_trip_apply_card(String), 0000L21IWA6Y25Y4G30000(String), 0000L21IWA6Y25Y4G30000(String) ";

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
