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
        String sql = "SELECT ss_staff_job.enddate ss_staff_jobb_enddate, ss_staff_job.deptid deptid, ss_staff_job.endflag ss_staff_joba_endflag, ss_staff_job.id ss_staff_job_id, ss_staff_job.jobrankid ss_staff_jobb_jobrankid, ss_staff_job.postid postid, ss_staff_job.begindate ss_staff_jobb_begindate, ss_staff_job.orgid orgid, ss_staff_job.showorder ss_staff_jobb_showorder, ss_staff_job.ismainjob ismainjob, ss_staff_orgrel.enddate ss_staff_orgrel_enddate, ss_staff_orgrel.begindate ss_staff_orgrel_begindate, bd_staff.email email, bd_staff.mobile mobile, bd_staff.birthdate birthdate, bd_staff.photo photo, bd_staff.name name, bd_staff.user_id user_id, bd_staff.sex sex, bd_staff.code code, bd_staff.enable bd_staffb_enable, bd_staff.tenantid bd_staffa_tenantid, bd_staff.id bd_staffb_id, bd_staff.id staffId, bd_psnl_catg.name psnclname, bd_position.name postname, org_adminj.name corpname, org_admin.name deptname, ss_staff_ctrt.id ss_staff_ctrt_id FROM corehr.ss_staff_job LEFT OUTER JOIN corehr.ss_staff_orgrel ss_staff_orgrel ON ss_staff_orgrel.id = ss_staff_job.orgrelid LEFT OUTER JOIN iuap_cloud_basedoc.bd_staff bd_staff ON ss_staff_orgrel.staff_id = bd_staff.id LEFT OUTER JOIN iuap_cloud_basedoc.bd_psnl_catg bd_psnl_catg ON ss_staff_job.psnclid = bd_psnl_catg.id LEFT OUTER JOIN iuap_cloud_basedoc.bd_duty bd_duty ON ss_staff_job.jobid = bd_duty.id LEFT OUTER JOIN iuap_cloud_basedoc.bd_position bd_position ON ss_staff_job.postid = bd_position.id LEFT OUTER JOIN iuap_cloud_basedoc.bd_staff bd_stafff ON ss_staff_job.rptrel = bd_stafff.id LEFT OUTER JOIN corehr.cs_chgtype cs_chgtype ON ss_staff_job.trnstype = cs_chgtype.id LEFT OUTER JOIN iuap_cloud_basedoc.org_admin org_admin ON ss_staff_job.deptid = org_admin.id LEFT OUTER JOIN iuap_cloud_basedoc.org_admin org_adminj ON ss_staff_job.orgid = org_adminj.id LEFT OUTER JOIN corehr.ss_staff_ctrt ss_staff_ctrt on ss_staff_job.staff_id = ss_staff_ctrt.staff_id and ss_staff_ctrt.lastflag = 1 where bd_staff.tenantid = ? and bd_staff.name like ? and ss_staff_job.deptid in(?,?,?,?,?,?,?,?,?) and ss_staff_job.endflag=? and (ss_staff_ctrt.conttype is null or ss_staff_ctrt.conttype=? or ss_staff_ctrt.conttype=?) and bd_staff.id not in(?,?,?,?,?) and ss_staff_job.lastflag=? and ss_staff_orgrel.indocflag=? and bd_staff.tenantid=? and bd_staff.dr=? and ss_staff_orgrel.dr=? and ss_staff_job.dr=? and bd_staff.enable=? order by ss_staff_job.showorder ASC limit 0,10";
        String param = "pnhodtfi(String), %woiu%(String), 1688523149365504(String), 1688524264853760(String), 1688523511664896(String), 1688528084472064(String), 1688528959525120(String), 1688528430878976(String), 1688524765827328(String), 1688525096030464(String), 1688525627494656(String), 0(Integer), 4(Integer), 5(Integer), 1689611297739008(String), 1689766202560768(String), 2114626798604544(String), 1689764642132224(String), 1689784217735424(String), 1(Integer), 1(Integer), pnhodtfi(String), 0(Integer), 0(Integer), 0(Integer), 1(Integer)";

        // if dbtype == null mysql else oracle
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
