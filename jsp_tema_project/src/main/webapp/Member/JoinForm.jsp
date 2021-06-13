<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
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
	<link href="css/init.css" rel="stylesheet" type="text/css">
    <link href="css/Header.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
    	function chk(){
    		if(!frm.user_id.value){//id란에 아무것도 입력 안됐을 때
    			alert('아이디를 입력하세요!');
    			frm.user_id.focus();
    			return false;
    		}else{
    			$.post("confirm.do", "user_id="+frm.user_id.value, function(data){
    				$('#err').html(data);
    			});
    		}
    	}
    	function chk2(){
    		if(frm.user_password.value != frm.confirmpass.value){
    			alert('암호와 암호확인이 다릅니다.');
    			frm.user_password.focus();
    			frm.user_password.value = "";
    			return false;
    		}
    	}
    </script>
</head>
<body>
    <div>
        <div class="global_menu_wrap">
            <div class="fr">
                <ul>
                    <li><a href="loginform.do">로그인</a></li>
                    <li><a href="joinform.do">회원가입</a></li>
                    <li><a href="#none">고객센터</a></li>
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
                            <a href="myreview.do" class="sub_menu">
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
        <div class="total_sub_menu"><a href="qna.qo">QnA</a></div>
        <div class="total_sub_menu"><a href="notice.no">공지사항</a></div>
    </div>
    <div class="container mt-3" style="max-width:550px;">
    	<form action="join.do" method="post" name="frm" onsubmit="return chk2()">
    		<br><h1 style="text-align:center; font-size:60px;">회원가입</h1><br>
    		<div class="form-group" style="display:flex; flex-flow:row;">
    			<input type="text" name="user_id" class="form-control" placeholder="아이디" required="required">
    			<input type="button" value="중복체크 " onclick="chk()" class="btn btn-default" style="border:1px solid #ddd; font-size:12px;">
    		</div>
    		<div id="err"></div>
    		<div class="form-group">
    			<input type="password" name="user_password" class="form-control mt-3" placeholder="비밀번호" required="required">	
    		</div>
    		<div class="form-group">
    			<input type="password" name="confirmpass" class="form-control mt-3" placeholder="비밀번호재입력" required="required">	
    		</div>
    		<div class="form-group">
    			<input type="text" name="user_name" class="form-control mt-3" placeholder="이름" required="required">	
    		</div>
    		<div class="form-group">
    			<input type="text" name="user_tel" class="form-control mt-3" placeholder="핸드폰 번호" 
    			title="010-1234-5678 과 같은 형식으로 입력하세요" pattern="\d{3}-\d{3,4}-\d{4}" required="required">
    			<span  aria-describedby="helpBlock" style="font-color:#111; font-size:10px;" class="mt-3">※ 010-1234-5678 과 같은 형태로 입력해 주세요.</span>    	
    		</div>
    		<div class="form-group">
    			<input type="text" name="user_address" class="form-control mt-3" placeholder="주소" required="required">	
    		</div>
    		<div class="form-group">
    			<input type="text" name="user_email" class="form-control mt-3" placeholder="이메일" required="required">	
    		</div>
    		<input type="submit" class="btn btn-primary btn-block mt-3 " value="회원가입" >
    		<a onclick="location.href='loginform.do'" class="btn btn-primary btn-block mt-3">로그인화면</a>
    	</form>
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