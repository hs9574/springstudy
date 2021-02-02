package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

public interface UserService {
	
	UserVo selectUser(String userid);
	
	List<UserVo> selectAllUser();
	
	Map<String , Object> selectPagingUser(PageVo pagevo);

	int modifyUser(UserVo uservo);
	
	int registUser(UserVo uservo);
	
	int deleteUser(String userid);
}
