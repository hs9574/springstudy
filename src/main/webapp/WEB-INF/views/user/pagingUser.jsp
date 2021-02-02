<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${cp}/../favicon.ico">

<title>user</title>
<link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
//문서 로딩이 완료되고 나서 실행되는 영역
$(function(){
	$('.user').on("click",function(){
		// this : 클릭이벤트가 발생한 element
		// data-속성명 data-userid, 속성명은 대소문자 무시하고 소문자로 인식
		// data-userId ==> data-userid
		var userid = $(this).data("userid");
		$('#userid').val(userid);
		$('#frm').submit();
	});
	
	$('#insBtn').on("click",function(){
		location.href= '${cp}/user/registUser';
	})
})
</script>
</head>
<body>
	<form id="frm" action="${cp}/user/user">
		<input type="hidden" name="userid" id="userid" value=""/>
	</form>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container-fluid">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@ include file="/WEB-INF/views/common/left.jsp"%>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="row">
				<div class="col-sm-8 blog-main">
					<h2 class="sub-header">사용자</h2>
					<div class="table-responsive">
						<table class="table table-striped">
							<tr>
								<th>사용자 아이디</th>
								<th>사용자 이름</th>
								<th>사용자 별명</th>
								<th>등록일시</th>
							</tr>	
							<c:forEach items="${userList}" var="user">
								<tr class="user" data-userid="${user.userid}">
								<td>${user.userid}</td>
								<td>${user.usernm}</td>
								<td>${user.alias}</td>
								<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<a id="insBtn" class="btn btn-default pull-right">사용자 등록</a>

					<div class="text-center">
						<ul class="pagination">
							<%-- pagination 값이 4이므로 1부터 4까지 4번 반복된다.
								 전체 사용자수 : 16명
								 페이지 사이즈 : 5
								 전체 페이지 수 : 4페이지
							 --%>
							<li class="prev"><a href="${cp}/user/pagingUser?page=1&pageSize=${pageVo.pagesize}">«</a></li>
							<c:forEach begin="1" end="${pagination}" var="i" varStatus="loop">
								<c:choose>
									<c:when test="${pageVo.page == i }">
										<li class="page-item active"><span>${i}</span></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="${cp}/user/pagingUser?page=${i}&pageSize=${pageVo.pagesize}">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<li class="next"><a href="${cp}/user/pagingUser?page=${pagination}&pageSize=${pageVo.pagesize}">»</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>