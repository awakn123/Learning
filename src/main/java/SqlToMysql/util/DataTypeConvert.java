package SqlToMysql.util;

import com.google.common.collect.Maps;

import java.util.Map;

public class DataTypeConvert {
	private static Map<String, String> oracleToMysqlTypeMap = Maps.newHashMap();

	static {
		oracleToMysqlTypeMap.put("NUMBER", "INT");
		oracleToMysqlTypeMap.put("INTEGER", "INT");
		oracleToMysqlTypeMap.put("INT", "INT");
		oracleToMysqlTypeMap.put("VARCHAR2", "VARCHAR");
		oracleToMysqlTypeMap.put("FLOAT", "DECIMAL");
	}

	public static String oracleToMysql(String oracleType) {
		String mysqlType = oracleToMysqlTypeMap.get(oracleType.toUpperCase());
		if (mysqlType == null)
			throw new RuntimeException("No such oracleType " + oracleType);
		return mysqlType;
	}
}
