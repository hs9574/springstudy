package kr.or.ddit.user.model;

import java.util.List;

public class UsersVo {
//	private List<String> userid;
//	
//	
//	public List<String> getUserid() {
//		return userid;
//	}
//
//	public void setUserid(List<String> userid) {
//		this.userid = userid;
//	}
//	
//	

	private List<UserVo> userVoList;

	public List<UserVo> getUserVoList() {
		return userVoList;
	}

	public void setUserVoList(List<UserVo> userVoList) {
		this.userVoList = userVoList;
	}

	@Override
	public String toString() {
		return "UsersVo [userVoList=" + userVoList + "]";
	}
	
	
}
