create table review(
review_num NUMBER PRIMARY KEY NOT NULL, --review 숫자
review_id VARCHAR2(20) NOT NULL, --유저 아이디 
review_travel VARCHAR2(50) NOT NULL, --리뷰 여행지
review_title VARCHAR2(50) NOT NULL, --리뷰 제목
review_content VARCHAR2(200) NOT NULL, --리뷰 내용
review_star NUMBER NOT NULL, --리뷰 별 갯수
review_reg_date DATE NOT NULL --리뷰 작성일
);

alter table REVIEW_TABLE modify review_place varchar2(50);

drop table REVIEW_TABLE;

select * from REVIEW_TABLE;