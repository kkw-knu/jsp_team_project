<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<% String path = request.getContextPath();%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="Description" content="관광지를 한눈에 찾아보고 숙박시스템까지 연계가 가능한 웹페이지.">
	<meta name="Keyword" content="웹개발, 프론트엔드, 백엔드, 여행, 관광, html, css, java, javascript, tomcat, jsp">
    <title>관광곳곳</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="css/bootstrap1.min.css" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/012d5a0fd2.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap1.min.js"></script>
    <script type="text/javascript" src="js/pooper.js"></script>
    <script type="text/javascript" src="js/Header.js"></script>
	<link href="css/init.css" rel="stylesheet" type="text/css">
    <link href="css/Header.css" rel="stylesheet" type="text/css">
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
                    <li><a href="qna.qo">고객센터</a></li>
				</c:if>
                <c:if test="${not empty user_id }">
                	<c:if test="${user_id=='master' }">
                	<li><a>관리자 로그인 상태입니다</a></li>
					<li><a>ㅣ</a></li>
                    <li><a href="logout.do">로그아웃</a></li>
                    <li><a>ㅣ</a></li>
                    <li><a href="membermanage.mo">회원관리</a></li>
                	</c:if>
                	<c:if test="${user_id!='master' }">
                	<li><a><%=session.getAttribute("user_id") %> 님 반갑습니다</a></li>
					<li><a>ㅣ</a></li>
                    <li><a href="logout.do">로그아웃</a></li>
                	</c:if>
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
                        <li>
                            <a href="mypage.do" class="sub_menu">
                                <i class="far fa-user-circle fa-2x"></i>내 정보
                            </a>
                        </li>
                        <li>
                            <a href="myreview.do" class="sub_menu">
                                <i class="far fa-edit fa-2x"></i>리뷰관리
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 105px;"></div><!--여백용-->
    <div class="list_menu">
        <div class="total_menu"><a href="#"></a></div><!--onclick로 전체메뉴 켜고끄기-->
        <div class="total_sub_menu"><a href="main.do">메인</a></div>
        <div class="total_sub_menu" id="travle"><a href="travel.to">여행지</a></div>
        <div class="total_sub_menu" id="sleep"><a href="acmd.ac">숙박</a></div>
        <div class="total_sub_menu"><a href="qna.qo">QnA</a></div>
        <div class="total_sub_menu"><a href="notice.no">공지사항</a></div>
    </div>
    <div class="drop_bar" id="travle_menu">
        <div class="drop_menu"><a href="search.to?travel_q3=테마여행">문화</a></div>
        <div class="drop_menu"><a href="search.to?travel_q3=캠핑">캠핑</a></div>
        <div class="drop_menu"><a href="search.to?travel_q3=바다-해수욕장">해수욕장</a></div>
        <div class="drop_menu"><a href="search.to?travel_q3=바다-해변">해안가</a></div>
        <div class="drop_menu"><a href="search.to?travel_q3=산-정상">등산</a></div>
        <div class="drop_menu"><a href="search.to?travel_q3=산-둘레길">둘레길</a></div>
    </div>
    <div class="drop_bar" id="sleep_menu">
        <div class="drop_menu"><a href="search.ac?acmd_q=호텔">호텔</a></div>
        <div class="drop_menu"><a href="search.ac?acmd_q=풀빌라">풀빌라</a></div>
        <div class="drop_menu"><a href="search.ac?acmd_q=글램핑">글램핑</a></div>
        <div class="drop_menu"><a href="search.ac?acmd_q=게스트하우스">게스트하우스</a></div>
        <div class="drop_menu"><a href="search.ac?acmd_q=펜션">펜션</a></div>
    </div>
    <div><br><br>
    <!-- 최근 공지사항 -->
    <table class="table"><caption><i class="far fa-smile-beam"></i> 최근 공지사항 목록 <i class="far fa-smile-beam"></i></caption>
    <thead>
	<tr><th width="15%;">번호</th><th width="40%;">제목</th><th width="15%;">작성자</th><th width="15%;">작성일</th><th width="15%;">조회수</th></tr></thead><tbody>
		<c:if test="${empty noticelist }">
			<tr><th colspan="5" style="text-align:center;">최근 공지사항이 없습니다</th></tr>
		</c:if>
		<c:if test="${not empty noticelist }">
			<c:forEach var="notice" items="${noticelist }">
				<tr><td>${notice.notice_num}</td>
				<c:if test="${notice.notice_del == 'y' }">
					<th colspan="4">삭제된 공지사항 입니다</th>
				</c:if>
				<c:if test="${notice.notice_del != 'y' }">
					<td title="${notice.notice_content }">
						<a href="content.no?notice_num=${notice.notice_num}&pageNum=1">
						${notice.notice_title}</a></td>
					<td>${notice.notice_writer }</td>
					<td>${notice.notice_reg_date }</td>
					<td>${notice.notice_readcount}</td>
				</c:if>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table><br><br>
	<!-- 별점높은순 4개 여행지 추천 -->
	<div class="jumbotron">
		<h3>&nbsp;&nbsp;&nbsp;&nbsp;유저들이 추천하는 여행지</h3><br>
		<div class="travelbox">
		<c:if test="${empty travellist }">
			<h2 align="center">현재 등록된 여행지가 없습니다.</h2>
		</c:if>
		 <c:if test="${not empty travellist }">
	    	<c:forEach var="travel" items="${travellist }">
	    	<article class="imagebox" onclick="location.href='content.to?travel_num=${travel.travel_num}&pageNum=${currentPage}'">
	    		<img src="<%=path%>/filesave/${travel.travel_img}" alt="여행이미지">
	    		<h2 align="center">${travel.travel_name }</h2>
	    		<p align="center">${travel.travel_mini }</p>
	    		<p align="center">
	    		<c:if test="${travel.travel_star >= 0 && travel.travel_star<0.5}"><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 0.5 && travel.travel_star<1}"><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star }</c:if>
	    		<c:if test="${travel.travel_star >= 1 && travel.travel_star<1.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 1.5 && travel.travel_star<2}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 2 && travel.travel_star<2.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 2.5 && travel.travel_star<3}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 3 && travel.travel_star<3.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 3.5 && travel.travel_star<4}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 4 && travel.travel_star<4.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star >= 4.5 && travel.travel_star<5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</c:if>
	    		<c:if test="${travel.travel_star == 5}"><p><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i> (${travel.travel_star })</p></c:if>
	    	    </p>
	    	</article>
	    	</c:forEach>
	    </c:if>
		</div>
	</div><br><br>
	<!-- 별점높은순 4개 숙박추천 -->
	<div class="jumbotron">
		<h3>&nbsp;&nbsp;&nbsp;&nbsp;유저들이 추천하는 숙박업소</h3><br>
		<div class="travelbox">
		    <c:if test="${empty acmdlist }">
		    	<h2 align="center">등록된 숙박업소가 없습니다.</h2>
		    </c:if>
		    <c:if test="${not empty acmdlist }">
		    	<c:forEach var="acmd" items="${acmdlist }">
		    	<article class="imagebox" onclick="location.href='content.ac?acmd_num=${acmd.acmd_num}&pageNum=${currentPage}'">
		    		<img src="<%=path%>/filesave/${acmd.acmd_img}" alt="숙박업소 이미지">
		    		<h2 align="center">${acmd.acmd_name }</h2>
		    		<p align="center">${acmd.acmd_mini }</p>
		    		<p align="center">
		    		<c:if test="${acmd.acmd_star >= 0 && acmd.acmd_star<0.5}"><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 0.5 && acmd.acmd_star<1}"><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star }</c:if>
		    		<c:if test="${acmd.acmd_star >= 1 && acmd.acmd_star<1.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 1.5 && acmd.acmd_star<2}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 2 && acmd.acmd_star<2.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 2.5 && acmd.acmd_star<3}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 3 && acmd.acmd_star<3.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 3.5 && acmd.acmd_star<4}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 4 && acmd.acmd_star<4.5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star >= 4.5 && acmd.acmd_star<5}"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star-half-alt fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</c:if>
		    		<c:if test="${acmd.acmd_star == 5}"><p><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i> (${acmd.acmd_star })</p></c:if>
		    	    </p>
		    	</article>
		    	</c:forEach>
		    </c:if>
	    </div>
	</div>
	<!-- 유형검사후 맞는거 -->
	<div>
		<h4>본인한테 알맞는 여행지를 찾고 싶으시다면? ↓↓↓↓click</h4>
		<button type="button" class="btn btn-success btn-ms" data-toggle="modal" data-target="#myModal">검사하기</button>
		<!-- 검사 모달 -->
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
								<div class="panel-heading">자신한테 맞는 여행지를 찾아보세요</div>
								<div class="panel-body">
									<%-- form --%>
									<form class="form-horizontal" role="form" action="search.do" method="post">
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<div class="radio">
													<h5>질문1</h5>
													<label class="radio-inline"><input type="radio" name="travel_q1" id="travel_q1" value="당일치기" checked="checked">당일치기</label>
													<label class="radio-inline"><input type="radio" name="travel_q1" id="travel_q1" value="1박2일">1박2일</label>
													<label class="radio-inline"><input type="radio" name="travel_q1" id="travel_q1" value="3박4일">3박4일</label>
													<label class="radio-inline"><input type="radio" name="travel_q1" id="travel_q1" value="장기여행">장기여행</label>
												</div>
												<div class="radio">
													<h5>질문2</h5>
													<label class="radio-inline"><input type="radio" name="travel_q2" id="travel_q2" value="봄" checked="checked">봄</label>
													<label class="radio-inline"><input type="radio" name="travel_q2" id="travel_q2" value="여름">여름</label>
													<label class="radio-inline"><input type="radio" name="travel_q2" id="travel_q2" value="가을">가을</label>
													<label class="radio-inline"><input type="radio" name="travel_q2" id="travel_q2" value="겨울">겨울</label>
												</div>
												<div class="radio">
													<h5>질문3</h5>
													<label class="radio-inline"><input type="radio" name="travel_q3" id="travel_q3" value="산-정상" checked="checked">산-정상</label>
													<label class="radio-inline"><input type="radio" name="travel_q3" id="travel_q3" value="산-둘레길">산-둘레길</label>
													<label class="radio-inline"><input type="radio" name="travel_q3" id="travel_q3" value="바다-해수욕장">바다-해수욕장</label>
													<label class="radio-inline"><input type="radio" name="travel_q3" id="travel_q3" value="바다-해변">바다-해변</label>
													<label class="radio-inline"><input type="radio" name="travel_q3" id="travel_q3" value="캠핑">캠핑</label>
													<label class="radio-inline"><input type="radio" name="travel_q3" id="travel_q3" value="테마여행">테마여행</label>
												</div>
												<div class="radio">
													<h5>질문4</h5>
													<label class="radio-inline"><input type="radio" name="travel_q4" id="travel_q4" value="솔로" checked="checked">솔로</label>
													<label class="radio-inline"><input type="radio" name="travel_q4" id="travel_q4" value="가족">가족</label>
													<label class="radio-inline"><input type="radio" name="travel_q4" id="travel_q4" value="커플">커플</label>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-success">제출하기</button>
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
	</div>
    </div><!--메인 div-->
    <div class="footer">
        <div class="fl">
            <div>
                <div>
                    <address class="footer_licensee">
                        <span><strong>(주)관광곳곳</strong></span><span>ㅣ</span>
                        <span>대표 : 홍길동, 김철수</span><span>ㅣ</span>
                        <span>주소 : 서울 마포구 신촌로 176 중앙빌딩</span>
                    </address>
                    <p class="footer_licensee">
                        <span>사업자등록번호 : 111-11-11111</span><span>ㅣ</span>
                        <span>팩스 : 111-111-1111</span><span>ㅣ</span>
                        <span>이메일 : abcd@naver.com</span><span>ㅣ</span>
                        <span><strong>고객센터 : 1577-1111</strong></span>
                    </p>
                    <p class="guide_txt">
                        ※ 부득이한 사정에 의해 여행일정이 변경되는 경우 사전동의를 받습니다.<br>
                        ※ 관광곳곳은 여행상품에 대하여 통신판매중개자로서 통신판매 당사자가 아니며 해당상품의 거래정보 및 거래등에 대해 책임을 지지 않습니다.
                    </p>
                    <p class="copy">COPYRIGHTⓒ GWANGWANGGOTGOT SERVICE INC. ALL RIGHTS RESERVED</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>