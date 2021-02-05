<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<script>
//문서 로딩이 완료 되었을 때
$(function(){
	$('#modifyBtn').on("click", function(){
		$('#frm').attr("method", "get")
		$('#frm').attr("action", "${cp}/user/userModify")
		$('#frm').submit();
	});
	$("#deleteBtn").on("click", function(){
		$('#frm').attr("method", "post")
		$('#frm').attr("action", "${cp}/user/deleteUser")
		$('#frm').submit();
	});
})
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container-fluid">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@ include file="/WEB-INF/views/common/left.jsp"%>
		</div>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<form class="form-horizontal" id="frm" role="form">
				<input type="hidden" name="userid" value="${user.userid }"/>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
					<div class="col-sm-10">
						<%-- <img src="${cp}/profile/${user.userid }.png"/> --%>
						<a href="/user/profileDownload?userid=${user.userid }">
							<img src="/user/profile?userid=${user.userid }"/>
						</a>
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
					<div class="col-sm-10">
						<label class="control-label">${user.userid }</label>
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
					<div class="col-sm-10">
						<label class="control-label">${user.usernm }</label>
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">별명</label>
					<div class="col-sm-10">
						<label class="control-label">${user.alias }</label>
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">비밀번호</label>
					<div class="col-sm-10">
						<label class="control-label">******</label>
					</div>
				</div>
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">등록일시</label>
					<div class="col-sm-10">
						<label class="control-label"><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></label>
					</div>
				</div>
				 
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">도로주소</label>
					<div class="col-sm-10">
						<label class="control-label">${user.addr1 }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">상세주소</label>
					<div class="col-sm-10">
						<label class="control-label">${user.addr2 }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">우편번호</label>
					<div class="col-sm-10">
						<label class="control-label">${user.zipcode }</label>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<!-- 사용자 수정 : method : get action = /userModify
						사용자 삭제 : method : post action = /deleteUser
						파라미터는 둘다 userid 하나만 있으면 가능 -->
						<button type="button" id="modifyBtn" class="btn btn-default">사용자 수정</button>
						<button type="button" id="deleteBtn" class="btn btn-default">사용자 삭제</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>