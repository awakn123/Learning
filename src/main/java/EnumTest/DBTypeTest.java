package EnumTest;

public class DBTypeTest {
	public static void main(String[] args) {
		System.out.println(DBType.mysql == DBType.mysql);
		System.out.println(DBType.mysql.equals("mysql"));
		System.out.println("mysql".equals(DBType.mysql));
		System.out.println("-----------------------------------");
		System.out.println(DBTypeConstant.DB_TYPE_MYSQL == DBTypeConstant.DB_TYPE_MYSQL);
		String mysql = DBTypeConstant.DB_TYPE_MYSQL;
		System.out.println(DBTypeConstant.DB_TYPE_MYSQL == mysql);
		System.out.println(DBTypeConstant.DB_TYPE_MYSQL == "mysql");
	}
}
