<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="managersessionChk.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<link href="css/init.css" rel="stylesheet" type="text/css">
    <link href="css/Header.css" rel="stylesheet" type="text/css">
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
    <div><!-- 메인내용 -->
    <form action="writeAction.ac" method="post" enctype="multipart/form-data">
    <input type="hidden" name="acmd_num" value="${acmd_num}">
	<input type="hidden" name="pageNum" value="${pageNum }">
	<table class="table"><caption>숙박업소 등록</caption>
		<tr><th>숙박 이름</th><td colspan="2"><input type="text" name="acmd_name" required="required" autofocus="autofocus"></td></tr>
		<tr><th>숙박 간단소개</th><td colspan="2"><input type="text" name="acmd_mini" required="required"></td></tr>
		<tr><th>주변 여행지</th><td colspan="2"><input type="text" name="acmd_travel" required="required" placeholder="정확한 여행지 이름입력"></td></tr>
		<tr><th>숙박 내용</th><td colspan="2"><textarea rows="5" cols="40" name="acmd_content" required="required"></textarea></td></tr>
		<tr><th>숙박 이미지</th><td colspan="2"><input type="file" name="acmd_img" required="required"></td></tr>
		<tr><th>숙박 지역</th><td colspan="2"><select name="acmd_local">
			<option value="서울" selected>서울</option>
			<option value="경기">경기</option>
			<option value="인천">인천</option>
			<option value="대구">대구</option>
			<option value="부산">부산</option>
			<option value="경상도">경상도</option>
			<option value="충청도">충청도</option>
			<option value="강원도">강원도</option>
			<option value="광주">광주</option>
			<option value="울산">울산</option>
			<option value="대전">대전</option>
			<option value="전라도">전라도</option>
			<option value="제주">제주</option>
			<option value="세종">세종</option>
		</select></td></tr>
		<tr><th>테마</th><td colspan="2"><select name="acmd_q">
			<option value="호텔" selected>호텔</option>
			<option value="풀빌라">풀빌라</option>
			<option value="글램핑">글램핑</option>
			<option value="게스트하우스">게스트하우스</option>
			<option value="펜션">펜션</option>
		</select></td></tr>
		<tr><th colspan="3"><input class="btn btn-primary btn-sm" type="submit" value="등록하기"></th></tr>
	</table>
    </form>
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