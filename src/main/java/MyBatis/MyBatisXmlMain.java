package MyBatis;

import MyBatis.bean.Blog;
import MyBatis.bean.HrmAlbumSubcompanyVO;
import MyBatis.bean.WorkflowBase;
import MyBatis.mapper.BlogMapper;
import MyBatis.mapper.WorkflowBaseMapper;
import MyBatis.util.SqlWrapper;
import com.google.common.collect.Maps;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisXmlMain {

	private static final Logger log = LogManager.getLogger();

	public static void main(String[] args) throws IOException {

		String sql;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			BlogMapper mapper = session.getMapper(BlogMapper.class);
			List<Blog> blogs = mapper.selectBlog();
			System.out.println(blogs.size());
			System.out.println("byte[]:" + blogs.get(0).getVarBin());
			System.out.println("byteToString: " + new String(blogs.get(0).getVarBin()));
		}

//		String sql = getSql(WorkflowBaseMapper.class, "selectHrmAlbumSubcompanyVO");
//		System.out.println(sql);
		// 测试使用#{}读取文件
//		Map params = Maps.newHashMap();
//		params.put("id", 1);
//		params.put("orderColumn", "name");
//		sql = getSql(WorkflowBaseMapper.class, "selectBlog", 1, "name");
//		System.out.println(sql);

//		example();
//		exampleForSqlWrapper();
	}

	private static void exampleForSqlWrapper() {
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", 1);
		map.put("title", "title");
//		map.put("author", "author");
		try (SqlSession session = sqlSessionFactory.openSession()) {
			WorkflowBaseMapper mapper = session.getMapper(WorkflowBaseMapper.class);
			List<Blog> blogs = mapper.selectWithParams(map);
			System.out.println(blogs.size());
			String s = mapper.select1();
			System.out.println(s);
		}

		SqlWrapper sqlWrapper = getSqlWrapper(WorkflowBaseMapper.class, "selectWithParams", map);
//		rs.executeQuery(sqlWrapper.getSql(), sqlWrapper.getParams());
//		System.out.println(sqlWrapper);
	}

	private static void example() {
		String sql;// 全面使用MyBatis
		SqlSession session = sqlSessionFactory.openSession();
		try {
			WorkflowBaseMapper mapper = session.getMapper(WorkflowBaseMapper.class);
			// 注解式
			WorkflowBase workflowBase = mapper.selectWorkflowBase(1);
			// xml式
			List<HrmAlbumSubcompanyVO> voList = mapper.selectHrmAlbumSubcompanyVO();
			System.out.println(workflowBase.getWorkflowName());
			System.out.println(voList.size());
			System.out.println(voList.get(0).getToTALsize());
			System.out.println(voList.get(0).getArr()[0]);
			/*Author author = new Author();
			author.setUsername("1");
			author.setPassword("1");
			author.setEmail("2@126.com");
			System.out.println(author.getId());
			BlogMapper blogMapper = session.getMapper(BlogMapper.class);
			blogMapper.insertAuthor(author);
			System.out.println(author.getId());*/
		} finally{
			session.close();
		}
		// 使用MyBatis的Sql解析器
		Map params = Maps.newHashMap();
//		RecordSet rs = new RecordSet();
//		params.put("id", 1);//这里放置参数。这个sql不需要参数，就先注掉了。
		sql = getSql("MyBatis.mapper.WorkflowBaseMapper.selectHrmAlbumSubcompanyVO", params);
//		rs.executeQuery(sql);

		System.out.println(sql);

		// 只有Mysql使用MyBatis
		String dbType = "mysql";
		if ("oracle".equals(dbType)) {
			sql = "select  a.*,b.*,round(b.albumsize/(1000+0.0),2) as totalsize,round(b.albumSizeUsed/(1000+0.0),2) as usesize, round((b.albumSize-b.albumSizeUsed)/(1000+0.0),2) as remainsize, (case b.albumSize when 0 then 0 else round((b.albumSizeUsed/(b.albumSize+0.0)*100),2) end ) AS rate " +
						 " from HrmSubcompany a LEFT JOIN AlbumSubcompany b ON a.id=b.subcompanyId"+
					"order by a.supsubcomid,a.id";
		} else if ("mysql".equals(dbType)) {
			sql = getSql("MyBatis.mapper.WorkflowBaseMapper.selectHrmAlbumSubcompanyVO", params);
		} else {
			sql = "select a.*,b.*,(convert(decimal(18,2),b.albumsize/(1000+0.0))) as totalsize,(convert(decimal(18,2),(b.albumSizeUsed/(1000+0.0)))) as usesize, (convert(decimal(18,2),(b.albumSize-b.albumSizeUsed)/(1000+0.0))) as remainsize, (case b.albumSize when 0 then 0 else (convert(decimal(18,2),(b.albumSizeUsed/(b.albumSize+0.0)*100))) end ) AS rate " +
					" from HrmSubcompany a LEFT JOIN AlbumSubcompany b ON a.id=b.subcompanyId"+
					"order by a.supsubcomid,a.id";
		}
//		rs.executeQuery(sql);
		System.out.println(sql);
	}

	public static final SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();

	private static SqlSessionFactory createSqlSessionFactory(){
		try {String resource = "MyBatis/Mybatis.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			return new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			log.error("读取MyBatis配置失败");
			return null;
		}
	}

	public static SqlWrapper getSqlWrapper(Class c, String id, Object... params) {
		String key = c.getName() + "." + id;
		return getSqlWrapper(key, params);
	}

	public static SqlWrapper getSqlWrapper(String key, Object... params) {
		Object parseParams;
		if (params == null || params.length == 0) {
			parseParams = null;
		} else if (params.length == 1)
			parseParams = params[0];
		else
			parseParams = params;
		BoundSql bs = sqlSessionFactory.getConfiguration().getMappedStatement(key).getBoundSql(parseParams);
		return new SqlWrapper(bs, parseParams, sqlSessionFactory.getConfiguration());
	}
	/**
	 * 通过javabean或Map来读取sql
	 * @param key
	 * @param param
	 * @return
	 */
	public static String getSql(String key, Object param) {
		return getSqlWrapper(key, param).getSql();
	}
	/**
	 * 读取不需要参数的sql
	 * @param key
	 * @return
	 */
	public static String getSql(String key) {
		return getSql(key, null);
	}

	/**
	 * 根据mapper文件读取sql
	 * @param c
	 * @param id
	 * @param param
	 * @return
	 */
	public static String getSql(Class c, String id, Object param) {
		String key = c.getName() + "." + id;
		return getSql(key, param);
	}

	/**
	 * 支持传入多个参数
	 * @param c
	 * @param id
	 * @param param
	 * @return
	 */
	public static String getSql(Class c, String id, Object... param) {
		String key = c.getName() + "." + id;
		return getSql(key, param);
	}
	/**
	 *
	 * @param c
	 * @param id
	 * @return
	 */
	public static String getSql(Class c, String id) {
		return getSql(c, id, null);
	}
}
