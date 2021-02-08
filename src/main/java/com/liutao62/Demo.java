package com.liutao62;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/8/27 16:23
 * @description sql 替换
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String sql = "select month.id,month.isapprove,month.staff_id,month.staff_code staff_code,month.org_id,month.dept_id, month.tsyear tsyear,month.tsmonth tsmonth,month.begindate,month.enddate,month.ts ,month.workdays,month.actualworkhours,month.latelong,month.latecount,month.earlylong,month.earlycount,month.absenthour,month.absentcount,month.resigncnt,month.outsidecnt,month.isallduty,month.leavecnt,month.isnormal,month.f_n_1,month.f_v_1,month.f_v_2,month.f_v_3,month.f_v_4,month.f_v_5 from ts_monthstat month where month.tenantid = ? and month.tsyear=? and month.tsmonth=? and month.dept_id in(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) and month.org_id in(?,?,?,?,?) order by month.dept_id ASC , month.staff_code ASC";
        String param = "e1dpehzh(String), 2021(String), 2(String), 2057738527969792(String), 2058075580502272(String), 1696708743631104(String), 2057684663243008(String), 2057635670545408(String), 2054980554363136(String), 2057749012353280(String), 1696709579444480(String), 2054947450687744(String), 2057493772850432(String), 2057642098561280(String), 1696710028595456(String), 1730743122088192(String), 1696744431489280(String), 2104191919182080(String), 1696703601561856(String), 1730743122088192(String), 1696742045946112(String), 1718281802715392(String), 2058072373711104(String)";

        // dbtype == null mysql else oracle
        String s = replaceSql(sql, param, null);
        System.out.println(s);

        String u = null;

//        builerMethod();

    }

    private static void builerMethod() throws IOException {
        File srcFile = new File("C:/Users/liutao/Desktop/new");
        File srcFile1 = new File("C:\\Users\\liutao\\Documents\\WeChat Files\\wxid_dws526u8ijvf22\\FileStorage\\File\\2021-01\\qq.txt");

        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        List<Admin> list = new ArrayList<>();
        List<String> orgs = new ArrayList<>();
        List<String> depts = new ArrayList<>();
        List<String> scopeOrgIds = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(srcFile);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 拆分每行数据的值
                String[] split = line.split("\t");
                list.add(new Admin(split[0], split[1]));
            }
            // 如果已经读取到末尾，写入 FOOTER 信息并关闭流
            bufferedReader.close();

            fileInputStream = new FileInputStream(srcFile1);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                // 拆分每行数据的值
                line.trim();
                String[] split = line.split(",");
                if (count == 0) {
                    orgs = Arrays.asList(split);
                } else if (count == 1) {
                    depts = Arrays.asList(split);
                } else {
                    scopeOrgIds = Arrays.asList(split);
                }
                count++;
            }
            // 如果已经读取到末尾，写入 FOOTER 信息并关闭流
            bufferedReader.close();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        Map<String, String> idInnercodeMap = list.stream().filter(org -> org.getInnercode() != null)
                .collect(Collectors.toMap(Admin::getId, Admin::getInnercode, (k1, k2) -> k1));

        for (String otherSchemeScopeOrg : scopeOrgIds) {
            String innerCode = idInnercodeMap.get(otherSchemeScopeOrg);

            orgs = orgs.stream().filter(orgId -> !idInnercodeMap.get(orgId).startsWith(innerCode))
                    .collect(Collectors.toList());
            // 人员所属组织和部门跨方案的时候可能查不出来。取消过滤。需要权限的话后面再取交集减少 deptId 数量
//			depts = depts.stream().filter(deptId -> !idInnercodeMap.get(deptId).startsWith(innerCode))
//					.collect(Collectors.toList());
        }

        System.out.println("---------------");
        System.out.println(orgs);
        System.out.println("---------------");

        System.out.println("god ning niupi");
    }

    public static String test(Boolean b) {
        b = true;
        return "";
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

    public static final Map<String, String> BILL_STATUS_MAP = new HashMap<>();

    static {
        BILL_STATUS_MAP.put("1", "P_YS_SCM_PU_0000027756"/* "未提交"*/);
        BILL_STATUS_MAP.put("2", "P_YS_SCM_PU_0000028700"/* "已提交"*/);
        BILL_STATUS_MAP.put("3", "P_YS_OA_XTLCZX_0000030417"/* "审批中"*/);
        BILL_STATUS_MAP.put("4", "P_YS_OA_XTLCZX_0000030544"/* "审批通过"*/);
        BILL_STATUS_MAP.put("5", "P_YS_HR_HRJQ_0000031266"/* "审批不通过"*/);
        BILL_STATUS_MAP.put("12", "P_YS_HR_HRJQ_0000031063"/* "已驳回"*/);
        BILL_STATUS_MAP.put("14", "P_YS_HR_HRJQ_0000031056"/* "已撤回"*/);
    }
}

@Data
@Accessors(chain = true)
@NoArgsConstructor
class User {
    String name;
    String sex;
}

@Data
@Accessors(chain = true)
@AllArgsConstructor
class Admin {
    String id;
    String innercode;
}
