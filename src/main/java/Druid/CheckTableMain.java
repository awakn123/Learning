package Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static Druid.DruidMain.getMysqlDataSource;

public class CheckTableMain {
    private static Set<String> checkedErrorTable = Sets.newHashSet();
    public static void main(String[] args) {
        try (DruidDataSource dataSource = getMysqlDataSource()) {
            checkErrorTable(dataSource);
//            checkErrorTableWithFixTable(dataSource, tableNameArray, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void checkErrorTableWithFixTable(DruidDataSource dataSource, String[] tableNameArray, int start) throws Exception {
        List<String> tableNameList = Lists.newArrayList(tableNameArray);
        Map errorTable = Maps.newHashMap();
        DruidPooledConnection conn = dataSource.getConnection();
        for (int i = start;i<tableNameList.size();i++) {
            String tableName = tableNameList.get(i);
            if (!checkedErrorTable.contains(tableName)) {
                conn = checkTable(dataSource, conn, errorTable, i, tableName);
            }
        }
        if (errorTable.isEmpty()) {
            System.out.println("EveryTable is OK!");
        } else {
            System.out.println("errorTable:" + errorTable.keySet());
            System.out.println(new JSONObject(errorTable));
        }
    }

    private static boolean checkErrorTable(DruidDataSource dataSource) throws Exception {
        DruidPooledConnection conn = dataSource.getConnection();
        String sql = "SELECT * from information_schema.tables where TABLE_SCHEMA = database()";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeQuery();
        ResultSet rs = ps.getResultSet();
        if (rs == null) {
            System.out.println("Table is null");
            return true;
        }
        List<Map<String, String>> result = parseResultSet(rs);
        System.out.println("共"+result.size()+"张表");
        int exportTableNum = result.size()/10 + 1;
        String tableNames = "";
        int i = 0;
        int sqlNum = 0;
        Set<String> noContainTable = Sets.newHashSet();
        for (Map<String, String> m : result) {
            String tableName = m.get("TABLE_NAME");
            if (noContainTable.contains(tableName.toLowerCase())) {
                continue;
            }
            tableName.replaceAll("$", "\\$");
            tableNames += tableName + " ";
            i++;
            if (i>=exportTableNum) {
                sqlNum = outExportSql(tableNames, sqlNum);
                i = 0;
                tableNames = "";
            }
        }

        outExportSql(tableNames, sqlNum);
        System.out.println();
        String exportSql = "nohup mysqldump -uroot -p  --skip-create-options -R weaver_dev --force ";
        for (String name:noContainTable) {
            exportSql += " --ignore-table ";
            exportSql += "weaver_dev."+name + " ";
        }
        exportSql += "> /usr/local/backup/data.sql 2> error.log &";
        sqlNum++;
        System.out.println(exportSql);

//        Map errorTable = Maps.newHashMap();
//        int i=0;
//        for (Map<String, String> m : result) {
//            String tableName = m.get("TABLE_NAME");
//            i++;
//            conn = checkTable(dataSource, conn, errorTable, i, tableName);
//        }
//        System.out.println("errorTable:" + errorTable.keySet());
//        System.out.println(new JSONObject(errorTable));
        return false;
    }

    private static int outExportSql(String tableNames, int sqlNum) {
        String exportSql = "nohup mysqldump -uroot -p  --skip-create-options -R weaver_dev --force --tables ";
        exportSql += tableNames;
        exportSql += "> /usr/local/backup/data"+sqlNum+".sql 2> error.log &";
        sqlNum++;
        System.out.println(exportSql);
        System.out.println(sqlNum);
        return sqlNum;
    }

    private static DruidPooledConnection checkTable(DruidDataSource dataSource, DruidPooledConnection conn, Map errorTable, int i, String tableName) throws Exception {
        String sql;
        PreparedStatement ps;
        if (i%100 == 0) {
            System.out.println("i:"+i+",TableName:"+tableName);
            System.out.println("errorTable:" + errorTable.keySet());
            conn.close();
            conn = dataSource.getConnection();
        }
        try {
            sql = "check table " + tableName;
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            List<Map<String, String>> checkTableResult = parseResultSet(ps.getResultSet());
            Map<String, String> r = checkTableResult.get(0);
            if (!"OK".equals(r.get("Msg_text"))) {
                errorTable.put(tableName, r);
            }
        } catch (Exception e) {
            throw new Exception("TableName:"+tableName+ ", index:"+i, e);
        }
        return conn;
    }

    private static List<Map<String, String>> parseResultSet(ResultSet rs) throws SQLException {
        List<Map<String, String>> result = Lists.newArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnNum = md.getColumnCount();
        String[] columnName = new String[columnNum];
        for (int i=0;i<columnNum;i++) {
            columnName[i] = md.getColumnLabel(i+1);
        }
        while(rs.next()) {
            Map<String, String> m = Maps.newHashMap();
            for (int i=0;i<columnNum;i++) {
                m.put(columnName[i], rs.getString(i + 1));
            }
            result.add(m);
        }
        return result;
    }

    private static String[] tableNameArray = new String[]{};
}
