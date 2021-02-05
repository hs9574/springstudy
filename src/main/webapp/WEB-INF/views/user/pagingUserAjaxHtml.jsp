<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:forEach items="${userList}" var="user">
	<tr class="user" data-userid="${user.userid}">
	<td>${user.userid}</td>
	<td>${user.usernm}</td>
	<td>${user.alias}</td>
	<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
	</tr>
</c:forEach>


####################

<li class="prev">
	<%-- <a href="${cp}/user/pagingUserAjaxHtml?page=1&pageSize=${pageVo.pagesize}">«</a> --%>
	<a href="javascript:pagingUserAjax(1, ${pageVo.pagesize });">«</a>
</li>
<c:forEach begin="1" end="${pagination}" var="i" varStatus="loop">
	<c:choose>
		<c:when test="${pageVo.page == i }">
			<li class="page-item active"><span>${i}</span></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a href="javascript:pagingUserAjax(${i}, ${pageVo.pagesize });">${i}</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
<li class="next"><a href="javascript:pagingUserAjax(${pagination}, ${pageVo.pagesize });">»</a></li>