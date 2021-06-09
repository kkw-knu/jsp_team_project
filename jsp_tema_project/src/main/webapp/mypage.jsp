<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="Description" content="관광지를 한눈에 찾아보고 숙박시스템까지 연계가 가능한 웹페이지.">
	<meta name="Keyword" content="웹개발, 프론트엔드, 백엔드, 여행, 관광, html, css, java, javascript, tomcat, jsp">
    <title>관광곳곳</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
    <script src="https://kit.fontawesome.com/012d5a0fd2.js" crossorigin="anonymous"></script>
    <link href="css/bootstrap1.min.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap1.min.js"></script>
    <script type="text/javascript" src="js/pooper.js"></script>
	<link href="css/init.css" rel="stylesheet" type="text/css">
    <link href="css/Header.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div>
        <div class="global_menu_wrap">
            <div class="fr">
                <ul>
					<li><a><%=session.getAttribute("user_id") %> 님 반갑습니다</a></li>
					<li><a>ㅣ</a></li>
                    <li><a href="logout.do">로그아웃</a></li>
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
                                <i class="far fa-user-circle fa-2x"></i><div>내 정보</div>
                            </a>
                        </li>
                        <li>
                            <a href="myreview.do" class="sub_menu">
                                <i class="far fa-edit fa-2x"></i><div>리뷰관리</div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 105px;"></div><!--여백용-->
    <div class="list_menu">
        <div class="total_menu"><a href="#"><i class="fas fa-bars"></i> 전체메뉴</div></a><!--onclick로 전체메뉴 켜고끄기-->
        <div class="total_sub_menu"><a href="main.do">메인</a></div>
        <div class="total_sub_menu"><a href="#">여행지</a></div>
        <div class="total_sub_menu"><a href="#">숙박</a></div>
        <div class="total_sub_menu"><a href="#">QnA</a></div>
        <div class="total_sub_menu"><a href="#">공지사항</a></div>
    </div>
   	<div class="container">
    	<div style="max-width:600px; margin:0 auto; margin-top:30px;">
   			<br><h1 style="text-align:center; font-size:60px;">내 정보</h1><br>
    		<table class="table">
				<tr><th>아이디</th><td>${member.user_id}</td>
				<tr><th>이름</th><td>${member.user_name}</td>
				<tr><th>전화</th><td>${member.user_tel}</td>
				<tr><th>주소</th><td>${member.user_address}</td>
				<tr><th>이메일</th><td>${member.user_email}</td>
				<tr><th>가입일</th><td>${member.user_date}</td>
			</table>
			<button class="btn btn-primary mt-3" data-toggle="modal" data-target="#myModal">정보수정</button>
			<button class="btn btn-primary mt-3" data-toggle="modal" data-target="#myModal1">비밀번호변경</button>
			<a onclick="#" class="btn btn-primary mt-3">회원탈퇴</a>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">회원 정보 수정</h4>
					</div>
					<div class="modal-body">
					<table class="table">
						<tr><th>이름</th><td>
						<div class="form-group">
    						<input type="text" name="user_name" id="user_name" class="form-control mt-3" placeholder="이름" required="required" value="${member.user_name}">	
    					</div></td>
						<tr><th>전화</th><td>
							<div class="form-group">
			    			<input type="text" name="user_tel" class="form-control mt-3" placeholder="핸드폰 번호" 
			    			title="010-1234-5678 과 같은 형식으로 입력하세요" pattern="\d{3}-\d{3,4}-\d{4}" required="required" value="${member.user_tel}">
			    			<span  aria-describedby="helpBlock" style="font-color:#111; font-size:10px;" class="mt-3">※ 010-1234-5678 과 같은 형태로 입력해 주세요.</span>    	
			    		</div></td>
						<tr><th>주소</th><td>
						<div class="form-group">
			    			<input type="text" name="user_address" class="form-control mt-3" placeholder="주소" required="required" value="${member.user_address}">	
			    		</div></td>
						<tr><th>이메일</th><td>
						<div class="form-group">
			    			<input type="text" name="user_email" class="form-control mt-3" placeholder="이메일" required="required" value="${member.user_email}">	
			    		</div></td>
					</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-primary" onclick="location.update.do">변경 사항 저장</button>
					</div>
				</div>
				<!-- 모달 콘텐츠 -->
			</div>
			<!-- 모달 다이얼로그 -->
		</div>
		<!-- 모달 젂체 윈도우 -->
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">비밀번호변경</h4>
					</div>
					<div class="modal-body">
    					
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-primary">변경 사항 저장</button>
					</div>
				</div>
				<!-- 모달 콘텐츠 -->
			</div>
			<!-- 모달 다이얼로그 -->
		</div>
    	</div>
	</div>
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