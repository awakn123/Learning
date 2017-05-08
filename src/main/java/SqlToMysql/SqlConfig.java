package SqlToMysql;

public class SqlConfig {
	// -------------------------------------参数配置----------------------------------
	// 传入的参数中有Cursor时是否报错
	public static boolean ShowTransferCursorError = false;
	// 是否在生成的Sql中保留传入的Cursor
	public static boolean KeepTransferCursor = false;
	// 传参有使用%rowType时是否报错
	public static boolean ShowRowtypeError = true;
	// 生成的Sql中是否保留rowType（会保留原OracleSql);
	public static boolean KeepRowtype = true;
	// 传参有使用%type时是否报错
	public static boolean ShowTypeError = true;
	// 生成的sql中是否保留type（会保留原OracleSql);
	public static boolean KeepType = true;
	// 是否显示Parse失败
	public static boolean showParseError = true;
}
