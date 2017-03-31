import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class DataConvert {
	public static void main(String[] args) {
	    Map<Integer, String> sqlServerColumnTypeMap = Maps.newHashMap();
		sqlServerColumnTypeMap.put(34,"image");
		sqlServerColumnTypeMap.put(35,"text");
		sqlServerColumnTypeMap.put(36,"uniqueidentifier");
		sqlServerColumnTypeMap.put(48,"tinyint");
		sqlServerColumnTypeMap.put(52,"smallint");
		sqlServerColumnTypeMap.put(56,"int");
		sqlServerColumnTypeMap.put(58,"smalldatetime");
		sqlServerColumnTypeMap.put(59,"real");
		sqlServerColumnTypeMap.put(60,"money");
		sqlServerColumnTypeMap.put(61,"datetime");
		sqlServerColumnTypeMap.put(62,"float");
		sqlServerColumnTypeMap.put(98,"sql_variant");
		sqlServerColumnTypeMap.put(99,"ntext");
		sqlServerColumnTypeMap.put(104,"bit");
		sqlServerColumnTypeMap.put(106,"decimal");
		sqlServerColumnTypeMap.put(108,"numeric");
		sqlServerColumnTypeMap.put(122,"smallmoney");
		sqlServerColumnTypeMap.put(127,"bigint");
		sqlServerColumnTypeMap.put(165,"varbinary");
		sqlServerColumnTypeMap.put(167,"varchar");
		sqlServerColumnTypeMap.put(173,"binary");
		sqlServerColumnTypeMap.put(175,"char");
		sqlServerColumnTypeMap.put(189,"timestamp");
		sqlServerColumnTypeMap.put(231,"sysname");
		sqlServerColumnTypeMap.put(231,"nvarchar");
		sqlServerColumnTypeMap.put(239,"nchar");

		List<Integer> list = Lists.newArrayList(175,52,35,106,98,167,127,173,239,61,104,165,36,62,56,99,231,59,108,48,60,34,40);

		for (Integer i : list) {
			System.out.printf("%s(%s),",sqlServerColumnTypeMap.get(i),i);
		}
	}
}
