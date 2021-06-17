/*리뷰 게시판*/
CREATE TABLE REVIEW_TABLE (
review_num NUMBER PRIMARY KEY NOT NULL, 
user_id VARCHAR2(10) NOT NULL, 
review_title VARCHAR2(20), 
review_thema varchar2(30),
review_content VARCHAR2(200), 
review_cnt NUMBER, 
review_star NUMBER, 
review_regdate DATE NOT NULL
);

alter table REVIEW_TABLE modify review_place varchar2(50);

drop table REVIEW_TABLE;
/*리뷰게시판 시퀀스*/
create sequence review_num
start with 0
minvalue 0
increment by 1;

/*시퀀스 증가.*/

review_num.nextval;

select * from REVIEW_TABLE;
/*리뷰게시판 쿼리문*/
INSERT INTO REVIEW_TABLE(review_num, user_id, review_title,review_thema, review_content, review_cnt, review_star, review_regdate)
VALUES (review_num.nextval,'zzzz2222','추천','해수욕장','여기 완전 좋아요!',0,4,sysdate);


/*rownum을 이용한 쿼리문 작성*/
SELECT * FROM (SELECT ROWNUM, review_num, user_id, review_title,review_thema, review_content, review_cnt, review_star,  review_regdate FROM REVIEW_TABLE ORDER BY review_num DESC) WHERE ROWNUM <=15;