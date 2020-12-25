package com.liutao62;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/8/27 16:23
 * @description sql 替换
 */
public class Demo {
    public static void main(String[] args) {
        String sql = "Preparing: INSERT INTO ts_monthstat ( id,tenantid,staff_id,staff_code,tsyear,tsmonth,org_id,dept_id,begindate,enddate,limittime,workhours,workdays,actualworkhours,actualworkdays,unnormallen,latelong,latecount,earlylong,earlycount,absenthour,absentcount,resigncnt,outsidecnt,isallduty,leavecnt,isnormal,store,storeunit,f_n_1,f_v_1,f_v_2,f_n_2 ) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) , ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
        String param = "1f64df2343ae479cafd8b9d54963cec7(String), e1dpehzh(String), 2056533153992960(String), A000534(String), 2020(String), 12(String), 2055116760126464(String), null, 2020-12-01(Date), 2021-01-01(Date), 0(Integer), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0(Integer), null, test35001(String), test1224001(String), 299.00(BigDecimal), d68e89e79bfd4935ab8e12ab23e61527(String), e1dpehzh(String), 2056557736956160(String), A000535(String), 2020(String), 12(String), 2055116760126464(String), 2056530298671360(String), 2020-12-05(Date), 2021-01-04(Date), -1(Integer), 206.8500(BigDecimal), 21.0000(BigDecimal), 9.8500(BigDecimal), 1.0000(BigDecimal), 134.55(Double), 0.0000(BigDecimal), 0(Integer), 0.0000(BigDecimal), 0(Integer), 134.5500(BigDecimal), 13(Integer), 0(Integer), 0(Integer), false(Boolean), 0(Integer), 0(String), 1.0000(BigDecimal), 1(Integer), 2.0(Double), test35(String), test1224001(String), 299.00(BigDecimal)";


    }


    /**
     * @param sql
     * @param param
     * @return
     * @description sql 替换
     */
    public static String replaceSql(String sql, String param) {
        String[] split = param.split(",");
        List<String> collect = Arrays.stream(split).map(s -> "null".equals(s.trim()) ? null : s.substring(0, s.indexOf("("))).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        char[] chars = sql.toCharArray();
        Iterator<String> iterator = collect.iterator();
        for (char c : chars) {
            if (c == '?') {
                sb.append("'" + iterator.next() + "'");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static final Map<String,String> BILL_STATUS_MAP = new HashMap<>();
    static  {
        BILL_STATUS_MAP.put("1", "P_YS_SCM_PU_0000027756" /* "未提交" */);
        BILL_STATUS_MAP.put("2","P_YS_SCM_PU_0000028700" /* "已提交" */);
        BILL_STATUS_MAP.put("3","P_YS_OA_XTLCZX_0000030417" /* "审批中" */);
        BILL_STATUS_MAP.put("4","P_YS_OA_XTLCZX_0000030544" /* "审批通过" */);
        BILL_STATUS_MAP.put("5","P_YS_HR_HRJQ_0000031266" /* "审批不通过" */);
        BILL_STATUS_MAP.put("12","P_YS_HR_HRJQ_0000031063" /* "已驳回" */);
        BILL_STATUS_MAP.put("14","P_YS_HR_HRJQ_0000031056" /* "已撤回" */);
    }
}
