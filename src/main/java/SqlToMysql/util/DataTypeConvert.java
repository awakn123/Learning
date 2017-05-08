package SqlToMysql.util;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DataTypeConvert {
	private static Map<String, String> oracleToMysqlTypeMap = Maps.newHashMap();
	private static final Logger log = LogManager.getLogger();
	public static final String ORACLE_TRANSFER_CURSOR = "transferCursor";
	public static final String ORACLE_CURSOR = "cursor";
	public static final String ORACLE_ROWTYPE = "rowtype";
	public static final String ORACLE_TYPE = "type";

	static {
		oracleToMysqlTypeMap.put("NUMBER", "INT");
		oracleToMysqlTypeMap.put("INTEGER", "INT");
		oracleToMysqlTypeMap.put("INT", "INT");
		oracleToMysqlTypeMap.put("SMALLINT", "SMALLINT");
		oracleToMysqlTypeMap.put("VARCHAR2", "VARCHAR");
		oracleToMysqlTypeMap.put("VARCHAR", "VARCHAR");
		oracleToMysqlTypeMap.put("CHAR", "CHAR");
		oracleToMysqlTypeMap.put("FLOAT", "DECIMAL");
		oracleToMysqlTypeMap.put("PIPELINED", "PIPELINED");
		oracleToMysqlTypeMap.put("AGGREGATE", "AGGREGATE");
		oracleToMysqlTypeMap.put("CLOB", "TEXT");
		oracleToMysqlTypeMap.put("DECIMAL", "DECIMAL");
		oracleToMysqlTypeMap.put("NUMERIC", "INT");
		oracleToMysqlTypeMap.put("LONG", "BIGINT");
	}

	public static String oracleToMysql(String oracleType) {
		String mysqlType = oracleToMysqlTypeMap.get(oracleType.toUpperCase());
		if (mysqlType == null)
			log.error("No such oracleType " + oracleType);
		if ("NUMERIC".equals(oracleType.toUpperCase()))
			log.error("NUMERIC");
		return mysqlType;
	}

	/**
	 * 检查是否是正常类型
	 * @param oracleType
	 * @return
	 */
	public static boolean checkType(String oracleType) {
		return !ORACLE_TRANSFER_CURSOR.equals(oracleType) && !ORACLE_CURSOR.equals(oracleType) && !ORACLE_ROWTYPE.equals(oracleType) && !ORACLE_TYPE.equals(oracleType);
	}
}
