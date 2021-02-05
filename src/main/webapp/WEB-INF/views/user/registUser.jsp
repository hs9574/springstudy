<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="${cp}/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	$('#addrBtn').on('click', function(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            $('#addr1').val(data.roadAddress);		//도로주소
	            $('#zipcode').val(data.zonecode);		//우편번호
	            
	            //사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
	            $('#addr2').focus();
	        }
	    }).open();
	})
})
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container-fluid">
		<div class="col-sm-3 col-md-2 sidebar">
			spring message : <spring:message code="GREETTING" arguments="brown"/>
			<%@ include file="/WEB-INF/views/common/left.jsp"%>
		</div>
		
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<form class="form-horizontal" role="form" action="${cp}/user/registUser" method="post" enctype="multipart/form-data">
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERID"/></label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="userid" name="userid" placeholder="아이디 입력" value="${param.userid}">
						<span style="color: red;"><form:errors path="userVo.userid"/></span>
						<input type="file" class="form-control" name="profile"/>
					</div>
					<div class="col-sm-2">
						<button type="button" id="checkBtn" class="btn btn-default">중복검사</button>
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERNM"/></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="usernm" name="usernm" placeholder="이름 입력" value="${param.usernm}">
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label"><spring:message code="ALIAS"/></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="alias" name="alias" placeholder="별명 입력" value="${param.alias}">
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label"><spring:message code="PASS"/></label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호" value="${param.pass}">
					</div>
				</div>
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label"><spring:message code="REG_DT"/></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="reg_dt" name="reg_dt" placeholder="날짜" value="${param.reg_dt}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label"><spring:message code="ADDR1"/></label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="addr1" name="addr1" placeholder="도로주소" value="${param.addr1}" readonly>
					</div>
					<div class="col-sm-2">
						<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label"><spring:message code="ADDR2"/></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소" value="${param.addr2}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label"><spring:message code="ZIPCODE"/></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" value="${param.zipcode}" readonly>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">등록 하기</button>
					</div>
				</div>
			</form>
			<select name="lang" class="form-control" style=width:100px;>
				<option value="">언어설정</option>
				<option value="ko">한국어</option>
				<option value="en">영어</option>
			</select>
			
			<script>
				$(function(){
					$('select[name=lang]').on('change', function(){
						document.location="/user/registUser?lang="+$(this).val();
					})
					$('select[name=lang]').val("${param.lang}");
				})
			</script>
		</div>
	</div>
</body>
</html>