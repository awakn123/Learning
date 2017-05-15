package SqlToMysql;

public class MysqlOutput {

	public static void main(String[] args) {
		System.out.println(getAllParentsRecursive("treenodes", "id", "pid", "2"));
//		System.out.println(getAllParentsRecursive("DocSubCategory", "id", "subcategoryid", "fatherid_1"));
//		System.out.println(outputPinyinCeshi("MEETING_TYPE", "NAME"));
//		System.out.println(outputPinyinCeshi("DOCDETAIL", "DOCSUBJECT"));
//		System.out.println(outputPinyinCeshi("DOCTREEDOCFIELD", "TREEDOCFIELDNAME"));
//		System.out.println(outputPinyinCeshi("WORKFLOW_NODEBASE", "NODENAME"));
//		System.out.println(outputPinyinCeshi("HRMDEPARTMENTVIRTUAL", "DEPARTMENTNAME"));
//		System.out.println(outputPinyinCeshi("HRMROLES", "ROLESNAME"));
//		System.out.println(outputPinyinCeshi("HRMJOBTITLES", "JOBTITLENAME"));
//		System.out.println(outputPinyinCeshi("CRM_CUSTOMERCONTACTER", "FULLNAME"));
	}

	/**
	 * 递归读取所有子
	 * @return
	 */
	private static String getAllChildsRecursive(String tableName, String idColName, String pidColName, String pid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select tl.lv,t.* from (\n")
			.append("\tselect @id idlist, @lv:=@lv+1 lv,\n")
			.append("\t(select @id:=group_concat(").append(idColName).append(" separator ',') from ").append(tableName).append(" where find_in_set(").append(pidColName).append(",@id)) sub\n")
			.append("\tfrom ").append(tableName).append(",(select @id:=").append(pid).append(",@lv:=0) vars\n")
			.append("\twhere @id is not null")
			.append(") tl,").append(tableName).append(" t\n")
			.append("where find_in_set(t.id,tl.idlist);");
		return sb.toString();
	}
	/**
	 * 递归读取所有父
	 */

	private static String getAllParentsRecursive(String tableName, String idColName, String pidColName, String pid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select tl.lv,t.* from (\n")
				.append("\tselect @id idlist, @lv:=@lv+1 lv,\n")
				.append("\t(select @id:=group_concat(").append(pidColName).append(" separator ',') from ").append(tableName).append(" where find_in_set(").append(idColName).append(",@id)) sub\n")
				.append("\tfrom ").append(tableName).append(",(select @id:=").append(pid).append(",@lv:=0) vars\n")
				.append("\twhere @id is not null")
				.append(") tl,").append(tableName).append(" t\n")
				.append("where find_in_set(t.id,tl.idlist);");
		return sb.toString();
	}

	private static String outputPinyinCeshi(String tableName, String colName) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ").append(tableName).append("(").append(colName).append(") VALUES('名字');\n");
		sb.append("SELECT ID,ecology_pinyin_search FROM ").append(tableName).append(" WHERE ").append(colName).append(" = '名字';\n");
		sb.append("UPDATE ").append(tableName).append(" SET ").append(colName).append(" = '新名字' WHERE ID = ?;\n");
		sb.append("SELECT ID,ecology_pinyin_search FROM ").append(tableName).append(" WHERE ID = ?;\n");
		return sb.toString();
	}
}
