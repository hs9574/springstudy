package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserServiceImpl() {}
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public Map<String , Object> selectPagingUser(PageVo pagevo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("pageVo", pagevo);
		map.put("userList", userDao.selectPagingUser(pagevo));
		map.put("pagination", (int)Math.ceil((double)userDao.selectAllUserCnt()/pagevo.getPagesize()));

		return map;
	}

	@Override
	public int modifyUser(UserVo uservo) {
		return userDao.modifyUser(uservo);
	}

	@Override
	public int registUser(UserVo uservo) {
		return userDao.registUser(uservo);
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}


}
