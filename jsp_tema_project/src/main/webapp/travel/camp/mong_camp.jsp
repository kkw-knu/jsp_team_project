<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="Description" content="관광지를 한눈에 찾아보고 숙박시스템까지 연계가 가능한 웹페이지.">
	<meta name="Keyword" content="웹개발, 프론트엔드, 백엔드, 여행, 관광, html, css, java, javascript, tomcat, jsp">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="../../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/012d5a0fd2.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/pooper.js"></script>
	<link href="../../css/init.css" rel="stylesheet" type="text/css">
    <link href="../../css/Header.css" rel="stylesheet" type="text/css">
<title>몽상포오토캠핑장</title>
</head>
<script>
    var index = 0;   //이미지에 접근하는 인덱스
    window.onload = function(){
        slideShow();
    }
    
    function slideShow() {
    var i;
    var x = document.getElementsByClassName("slide1");  //slide1에 대한 dom 참조
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";   //처음에 전부 display를 none으로 한다.
    }
    index++;
    if (index > x.length) {
        index = 1;  //인덱스가 초과되면 1로 변경
    }   
    x[index-1].style.display = "block";  //해당 인덱스는 block으로
    setTimeout(slideShow, 4000);   //함수를 4초마다 호출
 
}
</script>
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
                        <li>
                            <a href="mypage.do" class="sub_menu">
                                <i class="far fa-user-circle fa-2x"></i><div class="my">내 정보</div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="sub_menu">
                                <i class="far fa-edit fa-2x"></i><div class="my">리뷰관리</div>
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
	<div id="mong_title">
		<h1>몽상포 오토캠핑장(태안)</h1>
	</div>
	<div id="mong_img">
		<img class="slide1" src="../images/camp/mong1.JPG" height="300" width="300"/> 
		<img class="slide1" src="../images/camp/mong2.JPG" height="300" width="300"/>
		<img class="slide1" src="../images/camp/mong3.JPG" height="300" width="300"/>
	</div>
	<p> 몽산포해수욕장과 소나무 숲속에서 자연을 즐길수 있다.</p>

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
</body>
</html>