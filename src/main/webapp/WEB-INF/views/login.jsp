<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap **</title>

    <%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
	<%-- common_lib.jsp의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여 넣기 --%>
	
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>

    <!-- Custom styles for this template -->
    <link href="<%= request.getContextPath() %>/css/signin.css" rel="stylesheet" type="text/css">
    
    <script>
    	function getCookieValue(cookieStr, cookieName){
    		arr = cookieStr.split("; ")
    		for(i in arr){
    		    b = arr[i].split("=");
    		    
    		    if(b[0] == cookieName){
    		        return b[1]
    		    }
    		}
    		return ""
    	}
    	
    	//cookiename : 추가하고자 하는 쿠키이름
    	//cookievalue : 쿠키의 값
    	//expires : 유효기간(일수)
    	function addCookie(cookiename, cookievalue, expires){
    		var dt = new Date();	//지금 현재 날짜 ==> expires 만큼 미래날짜로 변경
    		dt.setDate(dt.getDate() + parseInt(expires));
    		console.log(dt);
    		document.cookie = cookiename + "=" + cookievalue + "; " + "path=/; expires=" + dt.toGMTString();
    	}
    	
    	function deletecookie(cookiename){
    		addCookie(cookiename, "", -1);
    	}

		//확인하여 존재할 경우 값설정, 체크
		$(function(){
			<c:if test="${msg != null}">
				alert("${msg}" + "ra");
			</c:if>
			
    		if(Cookies.get("rememberme") != null && Cookies.get("userid") != null){
    			$("#rememberme").prop("checked", true);
    			$("#userid").val(Cookies.get("userid"));
    		}
    		
    		//signin 아이디를 select
        	$("#signin").on("click",function(){
        		//rememberme 체크박스가 체크 되어있는지 확인
        		
        		// 체크되어 있을 경우
        		if($("#rememberme").is(":checked") == true){
            		// userid input에 있는 값을 userid쿠키로 저장
            		Cookies.set("userid", $("#userid").val());
            		// rememberme 쿠키로 Y값을 저장
            		Cookies.set("rememberme", "Y");
        		}else{
        			Cookies.remove("userid");
        			Cookies.remove("rememberme");
        		}
        		// form태그를 이용하여 signin 요청
        		$("#frm").submit();
        	})
    	})
    </script>

  </head>

  <body>

    <div class="container">
      <form class="form-signin" id="frm" action="<%= request.getContextPath() %>/login/process" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userid" class="sr-only">userid</label>
        <input type="text" name="userid" id="userid" class="form-control" placeholder="사용자 아이디" required autofocus>
        <label for="pass" class="sr-only">Password</label>
        <input type="password" name="pass" id="pass" class="form-control" placeholder="Password" value="brownPass" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me" id="rememberme"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>
