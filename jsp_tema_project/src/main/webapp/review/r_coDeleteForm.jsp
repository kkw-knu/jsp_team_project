<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/yBoard.css" type="text/css">
<script type="text/javascript">
	function passchk() {
		if(!codelform.coPass.value){
			alert("비밀번호를 입력하세요");
			codelform.coPass.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<%	
	int coNum = Integer.parseInt(request.getParameter("coNum"));
%>
<form method="post" name="codelform" action="r_coDeletePro.jsp" onsubmit="return passchk()">
	<table id="yBoard-popup">
		<tr>
			<td>비밀번호를 입력하세요.</td>
		</tr>
		<tr>	
			<td>
			<input type="hidden" name="coNum" value="<%=coNum%>">
			<input type="password" name="coPass">
			<input type="submit" value="확인">
			</td>
		</tr>
	</table>
</form>
</body>
</html>