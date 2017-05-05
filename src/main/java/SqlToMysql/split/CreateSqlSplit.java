package SqlToMysql.split;

import SqlToMysql.bean.SqlBlock;
import SqlToMysql.bean.SqlFile;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;

public class CreateSqlSplit implements SqlFileSplit {
	@Override
	public List<SqlBlock> split(SqlFile file) {
		List<SqlBlock> blocks = Lists.newArrayList();
		Integer start = null;
		for (int i = 0; i < file.getContentList().size(); i++) {
			String str = file.getContentList().get(i);
			CreatePattern type = CreatePattern.getType(str);
			if (type == null) continue;
			if (start != null) {
				blocks.add(new SqlBlock(file, start, i-1));
			}
			start = i;
		}
		if (start != null && start + 1 < file.getContentList().size()) {
			blocks.add(new SqlBlock(file, start, file.getContentList().size()-1));
		}
		return blocks;
	}

	public enum CreatePattern {
		Trigger("^CREATE OR REPLACE TRIGGER \"\\w+\""), Procedure("^CREATE OR REPLACE PROCEDURE \"\\w+\""), Function("^CREATE OR REPLACE FUNCTION \"\\w+\""),
		View("^CREATE OR REPLACE FORCE VIEW \"\\w+\"");
		private Pattern pattern;

		CreatePattern(String pattern) {
			this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		}

		public Pattern getPattern() {
			return pattern;
		}
		public static CreatePattern getType(String sql) {
			for (CreatePattern cp : values()) {
				if (cp.pattern.matcher(sql).find()) return cp;
			}
			return null;
		}
	}
}
