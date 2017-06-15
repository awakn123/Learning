package MyBatis.mapper;

import MyBatis.bean.Author;
import MyBatis.bean.Blog;

import java.util.List;

public interface BlogMapper {
	void insertAuthor(Author author);
	List<Blog> selectBlog();
}
