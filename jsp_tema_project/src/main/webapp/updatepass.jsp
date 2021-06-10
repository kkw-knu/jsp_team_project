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
<c:if test="${result > 0  }">
	<script type="text/javascript">
		alert("비밀번호가 변경되었습니다.");
		location.href="mypage.do";
	</script>
</c:if>
<c:if test="${result == 0  }">
	<script type="text/javascript">
		alert("현재 비밀번호가 틀렸습니다. 다시 시도해주세요");
		history.go(-1);
	</script>
</c:if>
</body>
</html>