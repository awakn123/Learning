package SqlToMysql.util;

import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class DataTypeConvert {
	private static Map<String, String> oracleToMysqlTypeMap = Maps.newHashMap();
	private static final Logger log = LogManager.getLogger();

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
	}

	public static String oracleToMysql(String oracleType) {
		String mysqlType = oracleToMysqlTypeMap.get(oracleType.toUpperCase());
		if (mysqlType == null)
			log.error("No such oracleType " + oracleType);
		return mysqlType;
	}
}
