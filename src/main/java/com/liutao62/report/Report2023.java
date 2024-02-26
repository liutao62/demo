package com.liutao62.report;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mysql.cj.jdbc.JdbcStatement;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liutao
 * @date 2023/11/16
 */
public class Report2023 {

    static Logger logger = LoggerFactory.getLogger(Report2023.class);
    static String tableName = "";

    private static int MEMORY_SIZE = 1500;

    static Set<String> tenantList = Sets.newHashSet("hc6h4yne", "qyic8c7o", "y6bzmfrx", "trr51z1u", "fkf8waws", "mbe008kc", "dpxhi49c", "w34o47cq", "j9b55izl", "vwsgqatm", "hc6h4yne", "glroeeo3", "no913wku", "onyxyjlw", "lj5uwqml", "ij44ambo", "xn9o50gt", "p8s8sqd6", "trr51z1u", "e1f8xrym", "sm699u3f", "qmh8bs6c", "lc6fpdmh", "telyf36r", "kjevosbe", "u60uwe55", "efgsc3wk", "whtkqqp4", "sncud5w2", "vz69bh15", "it1c7eoe", "wkyohttn", "wwrtk9r8", "ogz9urwc", "suyffdub", "ikkb9gi2", "vyos9rjy", "eb8b58c3", "s4l28nlq", "dkw67bnh", "sq4iimfp", "p8y549qi", "qhwm54dz", "fkdtrqg8", "t70rxy8a", "esu1m1v6", "ue4toadr", "o4p6voe0", "f7k6r3lg", "b616r7im", "kkwtb91f", "o2bky71z", "h27tpclq", "dmpltzkc", "sgnkhebc", "o1kg9ds9", "qbg71rsv", "bolok02g", "rq9yt6at", "cqmr5njx", "mpoki44a", "qdmpzski", "n22w18oi", "itvqa0x6", "kytq233a", "kftgrjbn", "ri6olpb2", "zrrdt554", "tzd1e6kw", "evgtgkx1", "hqp3mg1j", "welu4bwq", "o6pioi10", "tt65non9", "nim5zstd", "r0bppa3o", "ht1ysc1z", "yspy9miu", "wtxzxt8p", "q72omh0p", "prukv4t9", "ewontzay", "sxljek2f", "shwds6de", "pntgxj1s", "yaym70kz", "j6dmja45", "px19cm80", "vcb6smb7", "u8jdybet", "pxodp3oi", "x689z0rz", "s4rl0np9", "rdy91zio", "yxuxnbwt", "izx2uug0", "ayb9r1y4", "pleftcx7", "xgjh8jtw", "bk5ytx17", "w5agxii3", "g7i9zxb7", "s1mqekvo", "v1bxz4av");

    public static void main(String[] args) {
        loadDriver();
        String targetUrl = "jdbc:mysql://dbproxy.diwork.com:12368/esn_link?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai";
        String targetUsername = "lingyu";
        String targetPassword = "Gg5bEKZ8FDZkQeRFq_8889";
        try (java.sql.Connection targetConnection = DriverManager.getConnection(targetUrl, targetUsername, targetPassword);) {
            String sourceUrl = "jdbc:mysql://jumper.diwork.com:33061/yonbip_hr_tm?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&rewriteBatchedStatements=true&serverTimezone=Asia/Shanghai";
            String sourceUsername = "66a81b30-1c93-4527-8db3-f9a22e66fee5";
            String sourcePassword = "ZTY2AfAcAaNitk67";

            HashMap<String, String> schemaTableMap = new LinkedHashMap<>();
            schemaTableMap.put("yonbip_hr_tm", "ts_daystat_tm");
            schemaTableMap.put("hrattenddb", "ts_daystat");

            Statement statement1 = targetConnection.createStatement();
            ResultSet executedQuery = statement1.executeQuery("select tenantid from ts_daystat_tm group by tenantid");
            Set<String> hrtmTenantList = new HashSet<>();
            while (executedQuery.next()) {
                hrtmTenantList.add(executedQuery.getString("tenantid"));
            }
            logger.error("时间管理租户：" + hrtmTenantList);

//            fetchData(schemaTableMap, hrtmTenantList, targetConnection, sourceUrl, sourceUsername, sourcePassword, new CollectList(new ArrayList<>(MEMORY_SIZE), targetConnection));

            fetchStaffData(targetConnection, sourceUrl, sourceUsername, sourcePassword);

        } catch (Exception e) {
            logger.error("121", e);
        }

    }

    private static void fetchStaffData(Connection targetConnection, String sourceUrl, String sourceUsername, String sourcePassword) {
        CollectStaffList list = new CollectStaffList(new ArrayList<>(MEMORY_SIZE), targetConnection);

        ArrayList<String> strings = Lists.newArrayList("bk5ytx17", "cqmr5njx", "dpxhi49c", "eb8b58c3", "efgsc3wk", "esu1m1v6", "g7i9zxb7", "glroeeo3", "hc6h4yne", "hqp3mg1j", "it1c7eoe", "itvqa0x6", "j6dmja45", "j9b55izl", "mbe008kc", "n22w18oi", "no913wku", "ogz9urwc", "pxodp3oi", "rdy91zio", "s1mqekvo", "trr51z1u", "v1bxz4av", "vcb6smb7", "w5agxii3", "welu4bwq", "wwrtk9r8", "x689z0rz", "yspy9miu", "ayb9r1y4", "bk5ytx17", "izx2uug0", "q72omh0p", "qyic8c7o", "rdy91zio", "s4rl0np9", "u8jdybet", "x689z0rz", "xgjh8jtw", "yxuxnbwt");
        for (String tenant : tenantList) {
            logger.warn("开始处理 租户 " + tenant);
            String queryFormat = " /* bip_streaming_export */ select /*+ max_execution_time(14400000) */ id, user_id, tenantid \n" +
                    "from %s.bd_staff\n" +
                    "where TENANTID = '%s'\n" +
                    "  and dr = 0 and enable = 1;";

            try (Connection con = DriverManager.getConnection(sourceUrl, sourceUsername, sourcePassword);) {
                JdbcStatement st = (JdbcStatement) con.createStatement();
                st.enableStreamingResults();
                String formatSql = String.format(queryFormat, "iuap_apdoc_basedoc", tenant);
                ResultSet resultSet = st.executeQuery(formatSql);
                while (resultSet.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("id", resultSet.getString("id"));
                    row.put("user_id", resultSet.getString("user_id"));
                    row.put("tenantid", resultSet.getString("tenantid"));
                    list.add(row);
                }

                list.clear();
            } catch (Exception e) {
                logger.error("1111", e);
                try (Connection con = DriverManager.getConnection(sourceUrl, sourceUsername, sourcePassword);) {
                    JdbcStatement st = (JdbcStatement) con.createStatement();
                    st.enableStreamingResults();
                    String formatSql = String.format(queryFormat, "iuap_cloud_basedoc", tenant);
                    ResultSet resultSet = st.executeQuery(formatSql);
                    while (resultSet.next()) {
                        HashMap<String, String> row = new HashMap<>();
                        row.put("id", resultSet.getString("id"));
                        row.put("user_id", resultSet.getString("user_id"));
                        row.put("tenantid", resultSet.getString("tenantid"));
                        list.add(row);
                    }

                    list.clear();
                } catch (Exception exception) {
                    logger.error("1111", exception);
                }
            }

        }
    }

    private static void fetchData(HashMap<String, String> schemaTableMap, Set<String> hrtmTenantList, Connection targetConnection, String sourceUrl, String sourceUsername, String sourcePassword, List<Result> resultList) throws SQLException {
        for (Map.Entry<String, String> entry : schemaTableMap.entrySet()) {
            String schema = entry.getKey();
            tableName = entry.getValue();

            boolean hrattenddb = schema.equals("hrattenddb");

            for (String tenant : tenantList) {
                if (hrattenddb && hrtmTenantList.contains(tenant)) {
                    continue;
                }
                logger.warn("开始处理 租户 " + tenant);
                // 应对 3 种场景。1、连接太久 killed ，2、跳板机 token 过期 3、11 月底的数据增量更新
                Statement statement = targetConnection.createStatement();
                ResultSet executeQuery = statement.executeQuery("select max(calendar) as calendar from " + tableName + " where tenantid = '" + tenant + "';");
                String calendar = executeQuery.next() && !StringUtils.isEmpty(executeQuery.getString("calendar")) ? executeQuery.getString("calendar") : "2023-01-01";

                String queryFormat = " /* bip_streaming_export */ select /*+ max_execution_time(14400000) */ id, TENANTID, STAFF_ID, CALENDAR, SIGNBEGINTIME, SIGNENDTIME \n" +
                        "from %s.ts_daystat\n" +
                        "where TENANTID = '%s'\n" +
                        "  and CALENDAR >= '%s' order by calendar;";

                try (Connection con = DriverManager.getConnection(sourceUrl, sourceUsername, sourcePassword);) {
                    JdbcStatement st = (JdbcStatement) con.createStatement();
                    st.enableStreamingResults();
                    String formatSql = String.format(queryFormat, schema, tenant, calendar);
                    ResultSet resultSet = st.executeQuery(formatSql);
                    while (resultSet.next()) {
                        Result result = new Result(resultSet.getString("id"), resultSet.getString("TENANTID"), resultSet.getString("STAFF_ID"), resultSet.getString("SIGNBEGINTIME"), resultSet.getString("SIGNENDTIME"), resultSet.getString("CALENDAR"));
                        resultList.add(result);
                    }

                    resultList.clear();
                } catch (Exception e) {
                    logger.error("1111", e);
                }

            }
        }
    }

    private static class CollectList extends ArrayList<Result> {

        private List<Result> list;
        private Connection connection;

        public CollectList(List<Result> list, Connection connection) {
            this.list = list;
            this.connection = connection;
        }

        @Override
        public boolean add(Result t) {
            if (t.signBeginTime == null && t.signEndTime == null) {
                return false;
            }
            boolean add = list.add(t);
            if (list.size() >= MEMORY_SIZE) {
                this.clear();
            }
            return add;
        }

        @Override
        public int size() {
            return list.size();
        }

        private void selfClear() {
            if (list.size() == 0) {
                return;
            }
            try (Statement statement = connection.createStatement()) {
                StringBuilder stringBuilder = buildSql();
                statement.execute(stringBuilder.toString());
                logger.error("执行成功" + stringBuilder.substring(0, 200));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @SneakyThrows
        @NotNull
        private StringBuilder buildSql() {
            StringBuilder stringBuilder = new StringBuilder("replace INTO " + tableName + " (id, TENANTID, STAFF_ID, CALENDAR, SIGNBEGINTIME,begin_abs, SIGNENDTIME,end_abs) VALUES ");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat calendarFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Result row : list) {
                boolean breakFlag = row.signBeginTime == null && row.signEndTime == null;
                if (breakFlag) {
                    continue;
                }
                long calendarTime = calendarFormat.parse(row.calendar).getTime();
                stringBuilder.append(" ('")
                        .append(row.id).append(Math.abs((row.tenantId + row.staffId).hashCode())).append("',")
                        .append("'").append(row.tenantId).append("',")
                        .append("'").append(row.staffId).append("',")
                        .append("'").append(row.calendar).append("',");

                if (row.signBeginTime != null) {
                    long signTime = simpleDateFormat.parse(row.signBeginTime).getTime();
                    stringBuilder.append("'").append(row.signBeginTime).append("',");
                    stringBuilder.append(calendarTime - signTime).append(",");
                } else {
                    stringBuilder.append((Object) null).append(",");
                    stringBuilder.append((Object) null).append(",");
                }
                if (row.signEndTime != null) {
                    long signTime = simpleDateFormat.parse(row.signEndTime).getTime();
                    stringBuilder.append("'").append(row.signEndTime).append("',");
                    stringBuilder.append(signTime - calendarTime).append(",");
                } else {
                    stringBuilder.append((Object) null).append(",");
                    stringBuilder.append((Object) null).append(",");
                }
                int i = stringBuilder.lastIndexOf(",", stringBuilder.length() - 2);
                if (i > -1) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                stringBuilder.append("),");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(";");
            return stringBuilder;
        }

        @Override
        public void clear() {
            selfClear();
            list.clear();
        }
    }

    private static class CollectStaffList extends ArrayList<Map<String, String>> {

        private List<Map<String, String>> list;
        private Connection connection;

        public CollectStaffList(List<Map<String, String>> list, Connection connection) {
            this.list = list;
            this.connection = connection;
        }

        @Override
        public boolean add(Map<String, String> t) {
            if (t.get("user_id") == null || t.get("id") == null) {
                return false;
            }
            boolean add = list.add(t);
            if (list.size() >= MEMORY_SIZE) {
                this.clear();
            }
            return add;
        }

        @Override
        public int size() {
            return list.size();
        }

        private void selfClear() {
            if (list.size() == 0) {
                return;
            }
            try (Statement statement = connection.createStatement()) {
                StringBuilder stringBuilder = buildSql();
                statement.execute(stringBuilder.toString());
//                System.out.println(stringBuilder);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @SneakyThrows
        @NotNull
        private StringBuilder buildSql() {
            StringBuilder stringBuilder = new StringBuilder("replace INTO bd_staff (id, user_id, tenantid,staff_id) VALUES ");
            for (Map<String, String> row : list) {

                String id = row.get("id") + Math.abs((row.get("user_id") + row.get("tenantid")).hashCode());
                id = id.length() > 35 ? id.substring(0, 35) : id;
                stringBuilder.append(" ('")
                        .append(id).append("',")
                        .append("'").append(row.get("user_id")).append("',")
                        .append("'").append(row.get("tenantid")).append("',")
                        .append("'").append(row.get("id")).append("'");

                stringBuilder.append("),");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(";");
            return stringBuilder;
        }

        @Override
        public void clear() {
            selfClear();
            list.clear();
        }
    }


    private static class Result {
        public final String id;
        public final String tenantId;
        public final String staffId;
        public final String signBeginTime;
        public final String signEndTime;
        public final String calendar;

        public Result(String id, String tenantId, String staffId, String signBeginTime, String signEndTime, String calendar) {
            this.id = id;
            this.tenantId = tenantId;
            this.staffId = staffId;
            this.signBeginTime = signBeginTime;
            this.signEndTime = signEndTime;
            this.calendar = calendar;
        }
    }

    private static void loadDriver() {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        // Load driver class
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
