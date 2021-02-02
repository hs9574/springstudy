package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	//사용자 아이디로 사용자 조회
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();
	
	List<UserVo> selectPagingUser(PageVo pagevo);
	
	int selectAllUserCnt();
	
	int modifyUser(UserVo uservo);
	
	int registUser(UserVo uservo);
	
	int deleteUser(String userid);

}
