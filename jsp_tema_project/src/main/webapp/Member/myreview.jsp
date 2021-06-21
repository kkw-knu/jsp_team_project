<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="Keyword"
	content="웹개발, 프론트엔드, 백엔드, 여행, 관광, html, css, java, javascript, tomcat, jsp">
<meta name="Description" content="관광지를 한눈에 찾아보고 숙박시스템까지 연계가 가능한 웹페이지.">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관광곳곳</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Noto+Sans+KR:wght@700&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<script src="https://kit.fontawesome.com/012d5a0fd2.js"
	crossorigin="anonymous"></script>
<link href="css/bootstrap1.min.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<link href="css/init.css" rel="stylesheet" type="text/css">
<link href="css/Header.css" rel="stylesheet" type="text/css">
<script src="js/bootstrap1.min.js"></script>
<script type="text/javascript" src="js/Header.js"></script>
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
							<li><a><%=session.getAttribute("user_id")%> 님 반갑습니다</a></li>
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
        <div class="total_menu"><a href="#"><i class="fas fa-bars"></i> 전체메뉴</a></div><!--onclick로 전체메뉴 켜고끄기-->
        <div class="total_sub_menu"><a href="main.do">메인</a></div>
        <div class="total_sub_menu" id="travle"><a href="#">여행지</a></div>
        <div class="total_sub_menu" id="sleep"><a href="#">숙박</a></div>
        <div class="total_sub_menu"><a href="qna.qo">QnA</a></div>
        <div class="total_sub_menu"><a href="notice.no">공지사항</a></div>
    </div>
    <div class="drop_bar" id="travle_menu">
        <div class="drop_menu"><a href="cul1.aa">문화</a></div>
        <div class="drop_menu"><a href="camp1.aa">캠핑</a></div>
        <div class="drop_menu"><a href="beach.aa">바다-해수욕장</a></div>
        <div class="drop_menu"><a href="view.aa">바다-해안가</a></div>
        <div class="drop_menu"><a href="top1.aa">산-정상</a></div>
        <div class="drop_menu"><a href="cirroad.aa">산-둘레길</a></div>
    </div>
    <div class="drop_bar" id="sleep_menu">
        <div class="drop_menu"><a href="#">호텔</a></div>
        <div class="drop_menu"><a href="#">풀빌라</a></div>
        <div class="drop_menu"><a href="#">글램핑</a></div>
        <div class="drop_menu"><a href="#">게스트하우스</a></div>
        <div class="drop_menu"><a href="#">펜션</a></div>
    </div>
	<div>
		<br>
		<h1 style="text-align: center; font-size: 40px;">리뷰 관리</h1>
		<br>
		<!-- 관리자일때 -->
		<c:if test="${user_id=='master' }">
		<div class="jumbotron">
			<h3>&nbsp;&nbsp;&nbsp;&nbsp;Review</h3>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;* 여행지 리뷰관리.</p>
		</div>
		<table class="table"><caption>리뷰 제목을 누르면 자세히 볼 수 있습니다.</caption>
			<thead>
				<tr>
					<th width="20%;">작성자</th>
					<th width="20%;">여행지명</th>
					<th width="20%;">제목</th>
					<th width="15%;">별점</th>
					<th width="15%;">작성일</th>
					<th width="5%"></th>
					<th width="5%"></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty list }">
				<tr><th colspan="7" style="text-align:center;">등록된 리뷰가 없습니다. 어서 리뷰를 등록해주세요!</th></tr>
			</c:if>
			<c:if test="${not empty list }">
				<c:forEach var="review" items="${list }">
				<tr>
					<td>${review.review_id }</td>
					<td>${review.review_travel }</td>
					<td title="${review.review_title }">
					<a style="cursor:pointer;"data-toggle="modal" data-target="#myModal1${review.review_num }">${review.review_title }</a></td>
					<c:choose>
						<c:when test="${review.review_star == 1}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 2}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 3}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 4}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 5}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
					</c:choose>
					<td>${review.review_reg_date }</td>
					<td><button class="btn btn-info btn-xs mt-3" data-toggle="modal" data-target="#myModal_update${review.review_num }">수정</button></td>
					<td><button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#myModal_delete${review.review_num }">삭제</button></td>
					</tr>
					<!-- 글수정 모달 -->
					<div class="modal fade" id="myModal_update${review.review_num }" role="dialog">
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
												<form class="form-horizontal" action="myreviewupdate.re" method="post" >
												<input type="hidden" name="review_num" value="${review.review_num }">
												<input type="hidden" name="pageNum" value="${pageNum }">
												<input type="hidden" name="review_id" value="${review.review_id }">
												<input type="hidden" name="review_travel" value="${review.review_travel }">
													<div class="form-group">
														<label class="control-label col-sm-2">작성자(ID):</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="review_id" value="${review.review_id }" disabled>
														</div>
													</div><br><br>
													<div class="form-group">
														<label class="control-label col-sm-2" for="pwd">여행지 :</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="review_travel" value="${review.review_travel }" disabled>
														</div>
													</div><br><br>
													<div class="form-group">
														<label class="control-label col-sm-2" for="review_title">제목 :</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="review_title" name="review_title" placeholder="제목을 입력하세요" value="${review.review_title }">
														</div>
													</div><br><br>
													<div class="form-group">
														<label class="control-label col-sm-2" for="review_content">내용 :</label>
														<div class="col-sm-10">
															<textarea class="form-control" rows="5"
																placeholder="내용을 입력하세요" name="review_content"
																id="review_content">${review.review_content }</textarea>
														</div>
													</div><br><br>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<div class="radio">
																<label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="1"
																	checked="checked"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="2"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="3"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="4"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="5"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i>
																</label>
															</div>
														</div>
													</div><br><br>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" class="btn btn-success">수 정</button>
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
					<!-- 리뷰 삭제 모달 -->
					<div class="modal fade" id="myModal_delete${review.review_num }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="myModalLabel" style="text-align:center;">리뷰가 삭제됩니다. 진행하시겠습니까?</h4>
								</div>
							<form action="myreviewdelete.re" method="post">
								<input type="hidden" name="review_num" value="${review.review_num }">
								<input type="hidden" name="pageNum" value="${pageNum }">
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
									<input type="submit" class="btn btn-primary" value="삭제하기">
								</div>
								</form>
							</div>
						</div>
					</div>
					<!-- 상세보기 모달 -->
					<div class="modal fade" id="myModal1${review.review_num }" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Review Detail</h4>
								</div>
								<div class="modal-body">
									<h4>${review.review_title }</h4>
										<c:choose>
											<c:when test="${review.review_star == 1}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 2}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 3}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 4}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 5}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
										</c:choose>
									${review.review_reg_date }<br><br>
									<textarea rows="10" class="form-control" readonly="readonly" id="content">${review.review_content }</textarea>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<div align="center">
		<c:if test="${startPage > PAGE_PER_BLOCK}">
			<button class="btn btn-default btn-xs" onclick="location.href='myreview.do?pageNum=${startPage - 1}'">이전</button>
		</c:if>
		<c:forEach var="i" begin="${ startPage}" end="${ endPage}">
			<button class="btn btn-default btn-xs" onclick="location.href='myreview.do?pageNum=${i}'">${i }</button>
		</c:forEach>
		<c:if test="${endPage < totalPage} }">
			<button class="btn btn-default btn-xs" onclick="location.href='myreview.do?pageNum=${ endPage + 1}'">다음</button>
		</c:if>
		</div>
		</c:if>
		
		<!-- 관리자아닐때 -->
		<c:if test="${user_id!='master' }">
		<div class="jumbotron">
			<h3>&nbsp;&nbsp;&nbsp;&nbsp;Review</h3>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;* 내 여행지 리뷰를 확인하세요.</p>
		</div>
		<table class="table"><caption>리뷰 제목을 누르면 자세히 볼 수 있습니다.</caption>
			<thead>
				<tr>
					<th width="20%;">#</th>
					<th width="20%;">여행지명</th>
					<th width="20%;">제목</th>
					<th width="15%;">별점</th>
					<th width="15%;">작성일</th>
					<th width="5%"></th>
					<th width="5%"></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${empty list }">
				<tr><th colspan="7" style="text-align:center;">등록된 리뷰가 없습니다. 어서 리뷰를 등록해주세요!</th></tr>
			</c:if>
			<c:if test="${not empty list }">
				<c:forEach var="review" items="${list }">
				<tr>
					<td>${number}<c:set var="number" value="${number-1 }"></c:set></td>
					<td>${review.review_travel }</td>
					<td title="${review.review_title }">
					<a style="cursor:pointer;"data-toggle="modal" data-target="#myModal1">${review.review_title }</a></td>
					<c:choose>
						<c:when test="${review.review_star == 1}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 2}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 3}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 4}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
						<c:when test="${review.review_star == 5}">
							<td><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i></td>
						</c:when>
					</c:choose>
					<td>${review.review_reg_date }</td>
					<td><button class="btn btn-info btn-xs mt-3" data-toggle="modal" data-target="#myModal_update">수정</button></td>
					<td><button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#myModal_delete">삭제</button></td>
					<!-- 글수정 모달 -->
					<div class="modal fade" id="myModal_update" role="dialog">
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
												<form class="form-horizontal" action="myreviewupdate.re" method="post" >
												<input type="hidden" name="review_num" value="${review.review_num }">
												<input type="hidden" name="pageNum" value="${pageNum }">
												<input type="hidden" name="review_id" value="${review.review_id }">
												<input type="hidden" name="review_travel" value="${review.review_travel }">
													<div class="form-group">
														<label class="control-label col-sm-2">작성자(ID):</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="review_id" value="${review.review_id }" disabled>
														</div>
													</div><br><br>
													<div class="form-group">
														<label class="control-label col-sm-2" for="pwd">여행지 :</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" name="review_travel" value="${review.review_travel }" disabled>
														</div>
													</div><br><br>
													<div class="form-group">
														<label class="control-label col-sm-2" for="review_title">제목 :</label>
														<div class="col-sm-10">
															<input type="text" class="form-control" id="review_title" name="review_title" placeholder="제목을 입력하세요" value="${review.review_title }">
														</div>
													</div><br><br>
													<div class="form-group">
														<label class="control-label col-sm-2" for="review_content">내용 :</label>
														<div class="col-sm-10">
															<textarea class="form-control" rows="5"
																placeholder="내용을 입력하세요" name="review_content"
																id="review_content">${review.review_content }</textarea>
														</div>
													</div><br><br>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<div class="radio">
																<label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="1"
																	checked="checked"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="2"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="3"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="4"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
																</label> <label class="radio-inline"> <input type="radio"
																	name="review_star" id="review_star" value="5"><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i>
																</label>
															</div>
														</div>
													</div><br><br>
													<div class="form-group">
														<div class="col-sm-offset-2 col-sm-10">
															<button type="submit" class="btn btn-success">수 정</button>
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
					<!-- 리뷰 삭제 모달 -->
					<div class="modal fade" id="myModal_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									<h4 class="modal-title" id="myModalLabel" style="text-align:center;">리뷰가 삭제됩니다. 진행하시겠습니까?</h4>
								</div>
							<form action="myreviewdelete.re" method="post">
								<input type="hidden" name="review_num" value="${review.review_num }">
								<input type="hidden" name="pageNum" value="${pageNum }">
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
									<input type="submit" class="btn btn-primary" value="삭제하기">
								</div>
								</form>
							</div>
						</div>
					</div>
					<!-- 상세보기 모달 -->
					<div class="modal fade" id="myModal1" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Review Detail</h4>
								</div>
								<div class="modal-body">
									<h4>${review.review_title }</h4>
										<c:choose>
											<c:when test="${review.review_star == 1}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 2}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 3}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 4}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="far fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
											<c:when test="${review.review_star == 5}">
												<i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i><i class="fas fa-star fa-1x" style="color:#FACC2E;"></i>
											</c:when>
										</c:choose>
									${review.review_reg_date }<br><br>
									<textarea rows="10" class="form-control" readonly="readonly" id="content">${review.review_content }</textarea>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			
								</div>
							</div>
						</div>
					</div>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<div align="center">
		<c:if test="${startPage > PAGE_PER_BLOCK}">
			<button class="btn btn-default btn-xs" onclick="location.href='myreview.do?pageNum=${startPage - 1}'">이전</button>
		</c:if>
		<c:forEach var="i" begin="${ startPage}" end="${ endPage}">
			<button class="btn btn-default btn-xs" onclick="location.href='myreview.do?pageNum=${i}'">${i }</button>
		</c:forEach>
		<c:if test="${endPage < totalPage} }">
			<button class="btn btn-default btn-xs" onclick="location.href='myreview.do?pageNum=${ endPage + 1}'">다음</button>
		</c:if>
		</div>
		</c:if>
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