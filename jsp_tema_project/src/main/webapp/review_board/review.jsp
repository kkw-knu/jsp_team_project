

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="DB.*"%>
<%@ page import="review.*"%>
 

<% 
	//파라미터 한글처리
	request.setCharacterEncoding("utf-8");   
	 int tot = 0;
	 // Null값일때 형변환 하면 에러가나니.. 체크
	 if(request.getAttribute("tot") != null){				 
		 tot = (Integer)request.getAttribute("tot");	 
	 }
%>
<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="Description" content="관광지를 한눈에 찾아보고 숙박시스템까지 연계가 가능한 웹페이지.">
<meta name="Keyword"
	content="웹개발, 프론트엔드, 백엔드, 여행, 관광, html, css, java, javascript, tomcat, jsp">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Noto+Sans+KR:wght@700&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/012d5a0fd2.js"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/bootstrap1.min.js"></script>
<script type="text/javascript" src="../js/pooper.js"></script>
<link href="../css/init.css" rel="stylesheet" type="text/css">
<link href="../css/Header.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Review</title>


<%-- JavaScript review_read 이벤트 처리 --%>
<script type="text/javascript">

	//글 번호를 가지고 있을 전역변수
	var G_review_num;

	function review_read(num) {
		
		<%-- review_read 서블릿 호출 --%>
		
			G_review_num = num;
		
			//파라미터
			var param = {
					
					review_num : num
			}
			
			
			//요청
			$.ajax({
				
				url : "review_read.credu",
				dataType : "text",
				data : param,
				type : "post",
				success : function(data) {
															
					$("#content").text(data);
										
				}
						
			});
			
			
	}
	
	
	function review_delete() {
		
		<%-- review_delete 서블릿 호출 --%>
		
			//파라미터
			var param = {
					
					review_num : G_review_num
			}
			
			
			//요청
			$.ajax({
				
				url : "review_delete.credu",
				dataType : "text",
				data : param,
				type : "post",
				success : function(data) {
									
					alert("삭제 되었습니다.");
					G_review_num = 0; //전역변수 초기화
					location.href = "review.credu";
						
				}
						
			});
			
	}

	function aplace(e){
		 
		var place_camp = ["죽도오토캠핑장","왜목오토캠핑장","용담섬바위야영장","학포야영장","비양도 연평리야영지","몽상포오토캠핑장"];
		var place_cul = ["전주 한옥마을","안동 하회마을","남이섬","순천만습지","수성못"];
		var place_sea = ["광안리해수욕장","함덕해수욕장","꽃지해수욕장","감포항","남애항","태종대","해동용궁사"];
		var place_moun = ["지리산둘레길","치악산둘레길","망봉산둘레길","소양강둘레길","장생의 숲길","오방길","마곡사 솔바람길","올레길","덕유산","한라산","태백산","설악산"]
		var target = document.getElementById("review_place");
		
		 
		if(e.value == "camp") var d = place_camp;
		
		else if(e.value == "cul") var d = place_cul;
		
		else if(e.value == "sea") var d =  place_sea;
		
		else if(e.value == "moun") var d =  place_moun;
		
		
		target.options.length = 0;
		
		for (x in d) {
			var opt = document.createElement("option");
			opt.value = d[x];
			opt.innerHTML = d[x];
			target.appendChild(opt);
		            }
		 
		}


		
</script>


</head>
<body>
	<div>
		<div class="global_menu_wrap">
			<div class="fr">
				<ul>
					<c:set var="user_id" value="${sessionScope.user_id }"></c:set>
					<c:if test="${empty user_id }">
						<li><a href="loginform.do">로그인</a></li>
						<li><a>ㅣ</a></li>
						<li><a href="joinform.do">회원가입</a></li>
						<li><a>ㅣ</a></li>
						<li><a href="#none">고객센터</a></li>
					</c:if>
					<c:if test="${not empty user_id }">
						<li><a><%=session.getAttribute("user_id") %> 님 반갑습니다</a></li>
						<li><a>ㅣ</a></li>
						<li><a href="logout.do">로그아웃</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="local_menu_wrap">
			<div class="fl">
				<div class="logo">
					<a href="main.do">관광곳곳</a>
				</div>
			</div>
			<div class="fr">
				<div class="private_menu">
					<ul>
						<li><a href="mypage.do" class="sub_menu"> <i
								class="far fa-user-circle fa-2x"></i>
							<div class="my">내 정보</div>
						</a></li>
						<li><a href="#" class="sub_menu"> <i
								class="far fa-edit fa-2x"></i>
							<div class="my">리뷰관리</div>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div style="height: 105px;"></div>
	<!--여백용-->
	<div class="list_menu">
		<div class="total_menu">
			<a href="#"><i class="fas fa-bars"></i> 전체메뉴
		</div>
		</a>
		<!--onclick로 전체메뉴 켜고끄기-->
		<div class="total_sub_menu">
			<a href="main.do">메인</a>
		</div>
		<div class="total_sub_menu">
			<a href="#">여행지</a>
		</div>
		<div class="total_sub_menu">
			<a href="main.jsp?page=review/r_list.jsp?cate=0" target="_self">리뷰</a>
		</div>
		<div class="total_sub_menu">
			<a href="#">QnA</a>
		</div>
		<div class="total_sub_menu">
			<a href="#">공지사항</a>
		</div>
	</div>
	<%-- review 리스트 서블릿 호출 --%>
	<c:url var="review_read" value="../../review_read.credu"></c:url>
	<%-- review 작성 서블릿 호출 --%>
	<c:url var="review_write" value="../review_write.credu"></c:url>
	<%-- review 삭제 서블릿 호출 --%>
	<c:url var="review_delete" value="../../review_delete.credu"></c:url>






	<div class="container">
		<div class="jumbotron">
			<h2>Review</h2>
			<p>* 별점 및 리뷰 작성 후 작성하기 버튼을 클릭해 주세요.</p>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Title</th>
					<th>테마</th>
					<th>여행지</th>
					<th>별점</th>
					<th>작성일</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="review_Bean" items="${requestScope.list}">

					<tr class="info">
						<td>${review_Bean.review_num}</td>
						<td>${review_Bean.user_id}</td>
						<td><a data-toggle="modal" data-target="#myModal2"
							onclick="review_read(${review_Bean.review_num})">${review_Bean.review_title}</a></td>
						<td>${review_Bean.review_thema}</td>
						<td>${review_Bean.review_place }</td>
						<%-- 별점 --%>
						<c:choose>

							<%-- if(a == 1){ --%>
							<c:when test="${review_Bean.review_star == 1}">
								<td>★☆☆☆☆</td>
							</c:when>
							<%-- if(a == 2){ --%>
							<c:when test="${review_Bean.review_star == 2}">
								<td>★★☆☆☆</td>
							</c:when>
							<%-- if(a == 3){ --%>
							<c:when test="${review_Bean.review_star == 3}">
								<td>★★★☆☆</td>
							</c:when>
							<%-- if(a == 4){ --%>
							<c:when test="${review_Bean.review_star == 4}">
								<td>★★★★☆</td>
							</c:when>
							<%-- if(a == 5){ --%>
							<c:when test="${review_Bean.review_star == 5}">
								<td>★★★★★</td>
							</c:when>


						</c:choose>

						<td>${review_Bean.review_regdate}</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>

		<div class="row">
			<div class="col-sm-6">

				<div style="text-align: left">
					<ul class="pagination" id="page_num">
						<li>
							<% for(int i = 1 ; i<=tot ; i++){ %> <a
							href="review.credu?Page_num=<%=i%>"><%=i%></a> <% } %>

						</li>
					</ul>
				</div>


			</div>
			<div class="col-sm-2"></div>
			<div class="col-sm-4 text-success" style="text-align: right;">
				<button type="button" class="btn btn-success btn-lg"
					data-toggle="modal" data-target="#myModal">Write</button>
			</div>
		</div>


		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div class="modal-body">

						<div class="panel-group">
							<div class="panel panel-success" style="margin-top: 10px;">
								<div class="panel-heading">Review</div>
								<div class="panel-body">
									<%-- form --%>
									<form class="form-horizontal" role="form"
										action="${review_write}" method="post">
										<div class="form-group">
											<label class="control-label col-sm-2">작성자(ID):</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="user_id"
													name="user_id" placeholder="ID">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="pwd">제목:</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="review_title"
													name="review_title" placeholder="Title">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="pwd">테마:</label>
											<div class="col-sm-10">
												<select class="form-control" id="review_thema"
													name="review_thema" onchange="aplace(this)">
													<option>테마</option>
													<option value="camp">캠핑</option>
													<option value="cul">문화</option>
													<option value="sea">바다</option>
													<option value="moun">산</option>
												</select> <select class="form-control" id="review_place" name="review_place">
													<option>여행지</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="pwd">내용:</label>
											<div class="col-sm-10">
												<textarea class="form-control" rows="5"
													placeholder="review_content" name="review_content"
													id="review_content"></textarea>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<div class="radio">

													<label class="radio-inline"> <input type="radio"
														name="review_star" id="review_star" value="1"
														checked="checked">★☆☆☆☆
													</label> <label class="radio-inline"> <input type="radio"
														name="review_star" id="review_star" value="2">★★☆☆☆
													</label> <label class="radio-inline"> <input type="radio"
														name="review_star" id="review_star" value="3">★★★☆☆
													</label> <label class="radio-inline"> <input type="radio"
														name="review_star" id="review_star" value="4">★★★★☆
													</label> <label class="radio-inline"> <input type="radio"
														name="review_star" id="review_star" value="5">★★★★★
													</label>

												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-success">작 성</button>
												<button type="reset" class="btn btn-danger">초기화</button>
											</div>
										</div>
									</form>


								</div>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>


		<!-- Modal2 _ read review -->
		<div class="modal fade" id="myModal2" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Review Detail</h4>
					</div>
					<div class="modal-body">

						<textarea rows="10" class="form-control" readonly="readonly"
							id="content"></textarea>

					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-danger"
							onclick="review_delete()">Delete</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

					</div>
				</div>

			</div>
		</div>

	</div>
	<div class="footer">
		<div class="fl">
			<div>
				<div>
					<address class="footer_licensee">
						<span><strong>(주)관광곳곳</strong></span><span>ㅣ</span> <span>대표
							: 홍길동, 김철수</span><span>ㅣ</span> <span>주소 : 서울 마포구 신촌로 176 중앙빌딩</span>
					</address>
					<p class="footer_licensee">
						<span>사업자등록번호 : 111-11-11111</span><span>ㅣ</span> <span>팩스
							: 111-111-1111</span><span>ㅣ</span> <span>이메일 : abcd@naver.com</span><span>ㅣ</span>
						<span><strong>고객센터 : 1577-1111</strong></span>
					</p>
					<p class="guide_txt">
						※ 부득이한 사정에 의해 여행일정이 변경되는 경우 사전동의를 받습니다.<br> ※ 관광곳곳은 여행상품에 대하여
						통신판매중개자로서 통신판매 당사자가 아니며 해당상품의 거래정보 및 거래등에 대해 책임을 지지 않습니다.
					</p>
					<p class="copy">COPYRIGHTⓒ GWANGWANGGOTGOT SERVICE INC. ALL
						RIGHTS RESERVED</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

</body>
</html>