package SqlToMysql.bean;

import java.util.List;

public class OracleFunction {
	private String name;//函数名称
	private List<OracleParam> params;//传参
	private OracleReturn returnType;//返回类型
	private List<OracleParam> delcareList;//命名定义部分
	private String content;
	private SqlBlock block;

	public String getName() {
		return name;
	}

	public List<OracleParam> getParams() {
		return params;
	}

	public OracleReturn getReturnType() {
		return returnType;
	}

	public String getContent() {
		return content;
	}

	public SqlBlock getBlock() {
		return block;
	}

	public List<OracleParam> getDelcareList() {
		return delcareList;
	}

	public OracleFunction(String name, List<OracleParam> params, OracleReturn returnType, List<OracleParam> declare, String content, SqlBlock block) {
		this.name = name;
		this.params = params;
		this.returnType = returnType;
		this.delcareList = declare;
		this.content = content;
		this.block = block;
	}
}
