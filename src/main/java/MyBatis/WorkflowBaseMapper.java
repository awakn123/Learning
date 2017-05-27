package MyBatis;

import MyBatis.bean.HrmAlbumSubcompanyVO;
import MyBatis.bean.WorkflowBase;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorkflowBaseMapper {

	@Select("SELECT * FROM Workflow_Base WHERE id = #{id}")
	WorkflowBase selectWorkflowBase(int id);

	List<HrmAlbumSubcompanyVO> selectHrmAlbumSubcompanyVO();

}
