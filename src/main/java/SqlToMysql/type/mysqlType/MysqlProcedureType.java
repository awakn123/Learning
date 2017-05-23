package SqlToMysql.type.mysqlType;

import SqlToMysql.type.SqlType;

import java.util.regex.Pattern;

public class MysqlProcedureType extends SqlType {
	private final static MysqlProcedureType instance = new MysqlProcedureType();
	private MysqlProcedureType(){
		super(Pattern.compile("^DROP PROCEDURE IF EXISTS \\w+;", Pattern.CASE_INSENSITIVE),
				Pattern.compile("DELIMITER;", Pattern.CASE_INSENSITIVE),
				Pattern.compile("\\w+(?=;)"),
				"Mysql存储过程", "mysqlProcedure");
	}
	public static MysqlProcedureType getInstance(){
		return instance;
	}
}
