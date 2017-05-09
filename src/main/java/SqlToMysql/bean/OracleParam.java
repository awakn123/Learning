package SqlToMysql.bean;

import SqlToMysql.SqlConfig;
import SqlToMysql.util.DataTypeConvert;
import SqlToMysql.util.ListUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 存储过程、函数的传参
 */
public class OracleParam {
	private static final Logger log = LogManager.getLogger();
	private String name;//参数名
	private String type;//数据类型
	private InOut inout;//传入传出
	private String sql;//无法分类的
	private String length;//参数长度
	private String defaultValue;//默认值

	private OracleParam(String sql){
		this.sql = sql;
	};
	private OracleParam(String name, String type, InOut inout, String length, String defaultValue) {
		this.name = name;
		this.type = type;
		this.inout = inout;
		this.length = length;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public InOut getInout() {
		return inout;
	}

	public String getLength() {
		return length;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public static final String TRANSFER_PARAM = "TRANSFER_PARAM";
	public static final String DECLARE_PARAM = "DECLARE_PARAM";

	public static List<OracleParam> createOracleParam(String paramStr, String type) {
		if (StringUtils.isBlank(paramStr)) return null;
		if (paramStr.matches("^\\(.*\\)$")) {
			paramStr = paramStr.substring(1, paramStr.length() - 1);
		}
		String split;
		if (TRANSFER_PARAM.equals(type)) {
			split = ",";
		} else if (DECLARE_PARAM.equals(type)) {
			split = ";";
		} else return null;
		String[] paramArr = paramStr.split(split);
		List<OracleParam> params = Lists.newArrayList();
		Pattern simplePattern = Pattern.compile("^(declare )?\\w+ \\w+( )?(\\([\\d, ]+\\))?$", Pattern.CASE_INSENSITIVE);
		Pattern setPattern = Pattern.compile("^(declare )?\\w+ \\w+(\\([\\d,]+\\))?( )?:=( )?.+$", Pattern.CASE_INSENSITIVE);
		Pattern defaultPattern = Pattern.compile("^(declare )?\\w+ \\w+(\\([\\d,]+\\))? default .+$", Pattern.CASE_INSENSITIVE);
		Pattern transPattern = Pattern.compile("^(declare )?\\w+ (in out|in|out) \\w+$", Pattern.CASE_INSENSITIVE);
		Pattern cursorTransferPattern = Pattern.compile("^(declare )?\\w+ (in out|in|out) (\\w|.)+$", Pattern.CASE_INSENSITIVE);
		Pattern rowTypePattern = Pattern.compile("^(declare )?\\w+ \\w+%rowType$", Pattern.CASE_INSENSITIVE);
		Pattern typePattern = Pattern.compile("^(declare )?\\w+ [\\w\\.]+%type(( )?:=( )?.+)?$", Pattern.CASE_INSENSITIVE);
		Pattern cursorDeclarePattern = Pattern.compile("^cursor \\w+(\\(.+\\))? is", Pattern.CASE_INSENSITIVE);
		Pattern cursorDeclarePattern2 = Pattern.compile("^type \\w+ is ref cursor", Pattern.CASE_INSENSITIVE);
		for (int i = 0; i < paramArr.length; i++) {
			String str = paramArr[i].trim();
			String[] arr = str.split(" ");
			if (arr[0].equalsIgnoreCase("declare"))
				arr = Arrays.copyOfRange(arr, 1,arr.length);
			String paramName = arr[0];
			String paramType;
			InOut paramInOut = null;
			String defaultValue = null;
			if (simplePattern.matcher(str).find())
				paramType = arr[1];
			else if (setPattern.matcher(str).find()) {
				paramType = str.substring(str.indexOf(" ") + 1, str.indexOf(":=")).trim();
				defaultValue = str.substring(str.indexOf(":=")).trim();
			} else if (defaultPattern.matcher(str).find()) {
				paramType = arr[1];
				defaultValue = arr[3];
			} else if (transPattern.matcher(str).find()) {
				paramType = arr[arr.length - 1];
				paramInOut = arr.length == 4 ? InOut.INOUT : InOut.getByStr(arr[1]);
			} else if (cursorTransferPattern.matcher(str).find() && TRANSFER_PARAM.equals(type)) {
				if (SqlConfig.ShowTransferCursorError)
					log.error("Mysql don't support transfer cursorParam.paramName:" + str);
				OracleParam op = new OracleParam(str);
				op.type = DataTypeConvert.ORACLE_TRANSFER_CURSOR;
				params.add(op);
				continue;
			} else if (cursorDeclarePattern.matcher(str).find() || cursorDeclarePattern2.matcher(str).find()) {
				OracleParam op = new OracleParam(str);
				op.type = DataTypeConvert.ORACLE_CURSOR;
				params.add(op);
				continue;
			} else if (rowTypePattern.matcher(str).find()) {
				OracleParam op = new OracleParam(str);
				op.type = DataTypeConvert.ORACLE_ROWTYPE;
				params.add(op);
				continue;
			} else if (typePattern.matcher(str).find()) {
				OracleParam op = new OracleParam(str);
				op.type = DataTypeConvert.ORACLE_TYPE;
				params.add(op);
				continue;
			}

			else {
				str = ListUtils.toString(Arrays.asList(paramArr).subList(i, i+1), split);
				params.add(new OracleParam(str));
				log.error("cannot parse param:" + str);
//				throw new RuntimeException(str);
				continue;
			}

			String length = null;
			if (paramType.matches("\\w+\\([\\d,]+\\)")){
				int leftIdx = paramType.indexOf("(");
				int rightIdx = paramType.indexOf(")");
				length = paramType.substring(leftIdx + 1 ,rightIdx);
				paramType = paramType.substring(0, leftIdx);
			}
			params.add(new OracleParam(paramName, paramType, paramInOut, length, defaultValue));
		}
		return params;
	}

	public enum InOut {
		IN, OUT, INOUT;

		public static InOut getByStr(String str) {
			if (str == null) return IN;
			else if (str.toUpperCase().equals("IN OUT")) return INOUT;
			else if (str.toUpperCase().equals("OUT")) return OUT;
			else return IN;
		}
	}

	public String toString() {
		if (!DataTypeConvert.checkType(this.type)) {
			return specialToString();
		}
		StringBuilder psb = new StringBuilder();
		if (inout != InOut.IN && inout != null)
			psb.append(inout).append(" ");
		String mysqlType = DataTypeConvert.oracleToMysql(this.getType());
		psb.append(this.getName()).append(" ");
		psb.append(mysqlType);
		if (StringUtils.isNotBlank(length)) {
			psb.append("(").append(length).append(")");
		} else if ("VARCHAR".equals(mysqlType))
			psb.append("(255)");
		return psb.toString();
	}

	private String specialToString() {
		if (DataTypeConvert.ORACLE_CURSOR.equals(this.type))
			return this.sql;
		else if (DataTypeConvert.ORACLE_TRANSFER_CURSOR.equals(this.type) && SqlConfig.KeepTransferCursor)
			return this.sql;
		else if (DataTypeConvert.ORACLE_ROWTYPE.equals(this.type) && SqlConfig.KeepRowtype)
			return this.sql;
		else if (DataTypeConvert.ORACLE_TYPE.equals(this.type) && SqlConfig.KeepType)
			return this.sql;
		return "";
	}

	public String toDeclareString() {
		if (!DataTypeConvert.checkType(this.type)) {
			return specialToString();
		}
		StringBuffer psb = new StringBuffer("declare ");
		String mysqlType = DataTypeConvert.oracleToMysql(this.getType());
		if (length != null && length.contains(",") && "INT".equals(mysqlType)) {
			mysqlType = "DECIMAL";
		}
		psb.append(this.getName()).append(" ").append(mysqlType != null ? mysqlType : this.getType());
		if (StringUtils.isNotBlank(length)) {
			psb.append("(").append(length).append(")");
		} else if ("VARCHAR".equals(mysqlType))
			psb.append("(255)");
		else if ("CHAR".equals(mysqlType))
			psb.append("(10)");
		if (StringUtils.isNotBlank(this.defaultValue))
			psb.append(" default ").append(this.defaultValue);
		psb.append(";");
		return psb.toString();
	}

	public static String listToString(List<OracleParam> params) {
		if (params == null || params.isEmpty()) {
			return("()");
		} else {
			StringBuilder sb = new StringBuilder();
			List<String> paramStrs = Lists.newArrayList();
			params.stream().forEach((param) -> {
				String paramStr = param.toString();
				if (StringUtils.isNotBlank(paramStr))
					paramStrs.add(paramStr);
			});
			sb.append("(").append(ListUtils.toString(paramStrs)).append(")");
			return sb.toString();
		}
	}
}
