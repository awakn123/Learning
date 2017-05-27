package SqlToMysql;

import SqlToMysql.bean.SqlFile;
import SqlToMysql.util.SqlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OracleToMysql {

	private static final Logger log = LogManager.getLogger();

	/**
	 * SQL处理流程：
	 * 读取SQL文件,按行数对应代码组成List
	 * 分割代码块、记录代码块对应行数，注意不要把注释也算在内了。
	 * 将代码块与SQL类型对应，如创建存储过程、创建索引，暂时来说，一个代码块只对应一种类型
	 * 分析各类型，并根据类型修改代码块的内容
	 * 修改代码块对应位置的字符串
	 * 将数组写入新文件
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException, SQLException {
		SqlConfig.KeepTransferCursor = false;
		SqlConfig.KeepType = true;
		SqlConfig.KeepRowtype = true;
		SqlConfig.ShowTransferCursorError = false;
		SqlConfig.ShowRowtypeError = true;
		SqlConfig.ShowTypeError = true;
		SqlConfig.showParseError = true;
		// 1~22
		String rootPath = "./src/test/sqlWork/e8_oracle/split/1table,record,key.sql";
		String writePath = "./src/test/sqlWork/e8_oracle/split";

		List<SqlFile> files = SqlFile.readFile(rootPath);
		SqlFile file = files.get(0);

		int j =0;
		int s = file.getContentList().size();
		for (int i=0;i<s;i = i+50000) {
			j++;
			SqlUtils.writeFileStr(writePath, j+"table.sql", file.getContentList().subList(i, i+50000 > s ? s : i+50000));
		}

		System.out.println("end");
	}

}
