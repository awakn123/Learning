package SqlToMysql;

public class MysqlOutput {

	public static void main(String[] args) {
		System.out.println(getAllChildsRecursive("treenodes", "id", "pid", "2"));
		System.out.println(getAllParentsRecursive("treenodes", "id", "pid", "9"));
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
				.append("\t(select @id:=group_concat(").append(idColName).append(" separator ',') from ").append(tableName).append(" where find_in_set(").append(pidColName).append(",@id)) sub\n")
				.append("\tfrom ").append(tableName).append(",(select @id:=").append(pid).append(",@lv:=0) vars\n")
				.append("\twhere @id is not null")
				.append(") tl,").append(tableName).append(" t\n")
				.append("where find_in_set(t.id,tl.idlist);");
		return sb.toString();
	}
}
