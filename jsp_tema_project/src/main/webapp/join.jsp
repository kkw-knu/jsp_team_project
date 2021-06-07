<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jsp_project.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("회원가입 성공");
		location.href="loginform.do";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("회원가입 실패");
		history.back();
	</script>
</c:if>
<c:if test="${result < 0 }">
	<script type="text/javascript">
		alert("아이디가 있습니다.");
		history.back();
	</script>
</c:if>


</body>
</html>