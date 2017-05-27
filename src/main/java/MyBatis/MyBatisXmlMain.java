package MyBatis;

import MyBatis.bean.Author;
import MyBatis.bean.HrmAlbumSubcompanyVO;
import MyBatis.bean.WorkflowBase;
import MyBatis.mapper.BlogMapper;
import MyBatis.mapper.WorkflowBaseMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisXmlMain {
	public static void main(String[] args) throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

		// 全面使用MyBatis
		SqlSession session = sqlSessionFactory.openSession();
		try {
			WorkflowBaseMapper mapper = session.getMapper(WorkflowBaseMapper.class);
			// 注解式
			WorkflowBase workflowBase = mapper.selectWorkflowBase(1);
			// xml式
			List<HrmAlbumSubcompanyVO> voList = mapper.selectHrmAlbumSubcompanyVO();
//			System.out.println(workflowBase.getWorkflowName());
//			System.out.println(voList.size());
//			System.out.println(voList.get(0).getToTALsize());
			Author author = new Author();
			author.setUsername("1");
			author.setPassword("1");
			author.setEmail("2@126.com");
			System.out.println(author.getId());
			BlogMapper blogMapper = session.getMapper(BlogMapper.class);
			blogMapper.insertAuthor(author);
			System.out.println(author.getId());
		} finally{
			session.close();
		}
/*
		// 使用MyBatis的Sql解析器
		Map params = Maps.newHashMap();
//		RecordSet rs = new RecordSet();
//		params.put("id", 1);//这里放置参数。这个sql不需要参数，就先注掉了。
		String sql = getSql("MyBatis.mapper.WorkflowBaseMapper.selectHrmAlbumSubcompanyVO", params);
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
		System.out.println(sql);*/
	}

	private static SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "MyBatis/Mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	private static String getSql(String key, Map params) throws IOException {
		BoundSql bs = getSqlSessionFactory().getConfiguration().getMappedStatement(key).getBoundSql(params);
		return bs.getSql();
	}
}