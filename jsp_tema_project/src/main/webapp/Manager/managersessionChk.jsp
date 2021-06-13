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
		alert('관리자 전용 페이지입니다.');
		location.href="main.do";
	</script>
</c:if>
<c:if test="${not empty user_id }">
	<c:if test="${user_id!='master' }">
		<script type="text/javascript">
			alert('관리자 전용 페이지입니다.');
			location.href="main.do";
		</script>
	</c:if>
</c:if>
</body>
</html>