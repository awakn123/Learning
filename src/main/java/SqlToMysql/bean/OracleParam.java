package SqlToMysql.bean;

import SqlToMysql.util.DataTypeConvert;
import SqlToMysql.util.ListUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 存储过程、函数的传参
 */
public class OracleParam {
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
		Pattern simplePattern = Pattern.compile("^\\w+ \\w+(\\(\\d+\\))?$", Pattern.CASE_INSENSITIVE);
		Pattern setPattern = Pattern.compile("^\\w+ \\w+(\\(\\d+\\))?( )?:=( )?.+$", Pattern.CASE_INSENSITIVE);
		Pattern defaultPattern = Pattern.compile("^\\w+ \\w+(\\(\\d+\\))? default .+$", Pattern.CASE_INSENSITIVE);
		Pattern transPattern = Pattern.compile("^\\w+ (in out|in|out) \\w+$", Pattern.CASE_INSENSITIVE);
		for (int i = 0; i < paramArr.length; i++) {
			String str = paramArr[i].trim();
			String[] arr = str.split(" ");
			String paramName = arr[0];
			String paramType = null;
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
			} else {
				str = ListUtils.toString(Arrays.asList(paramArr).subList(i, paramArr.length), split);
				params.add(new OracleParam(str));
//				throw new RuntimeException(str);
				break;
			}

			String length = null;
			if (paramType.matches("\\w+\\(\\d+\\)")){
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
		StringBuffer psb = new StringBuffer();
		String mysqlType = DataTypeConvert.oracleToMysql(this.getType());
		psb.append(this.getName()).append(" ");
		psb.append(mysqlType);
		if (inout != InOut.IN && inout != null)
			psb.append(inout);
		if (StringUtils.isNotBlank(length)) {
			psb.append("(").append(length).append(")");
		} else if ("VARCHAR".equals(mysqlType))
			psb.append("(255)");
		return psb.toString();
	}
	public String toDeclareString() {
		if (this.sql != null) {
			return this.sql;
		}
		StringBuffer psb = new StringBuffer("declare ");
		String mysqlType = DataTypeConvert.oracleToMysql(this.getType());
		psb.append(this.getName()).append(" ").append(mysqlType != null ? mysqlType : this.getType());
		if (StringUtils.isNotBlank(length)) {
			psb.append("(").append(length).append(")");
		} else if ("VARCHAR".equals(mysqlType))
			psb.append("(255)");
		if (StringUtils.isNotBlank(this.defaultValue))
			psb.append(" default ").append(this.defaultValue);
		psb.append(";");
		return psb.toString();
	}
}
