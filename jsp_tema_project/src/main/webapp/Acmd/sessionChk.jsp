<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="user_id" value="${sessionScope.user_id }"></c:set>
<c:if test="${empty user_id }">
	<script type="text/javascript">
		alert('로그인이 필요한 페이지입니다.');
		location.href="loginform.do";
	</script>
</c:if>
</body>
</html>