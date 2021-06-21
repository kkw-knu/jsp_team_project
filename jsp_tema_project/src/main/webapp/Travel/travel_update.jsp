<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("여행지정보가 수정되었습니다.");
		location.href="content.to?travel_num=${travel_num}&pageNum=${pageNum}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("여행지 수정에 실패하였습니다.");
		history.back();
	</script>
</c:if>
</body>
</html>