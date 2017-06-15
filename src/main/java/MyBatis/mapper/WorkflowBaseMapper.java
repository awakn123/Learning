package MyBatis.mapper;

import MyBatis.bean.Blog;
import MyBatis.bean.HrmAlbumSubcompanyVO;
import MyBatis.bean.WorkflowBase;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface WorkflowBaseMapper {

	@Select("SELECT * FROM Workflow_Base WHERE id = #{id}")
	WorkflowBase selectWorkflowBase(int id);

	List<HrmAlbumSubcompanyVO> selectHrmAlbumSubcompanyVO();

	List<Blog> selectWithParams(Map<String, Object> map);

	String select1();
}
