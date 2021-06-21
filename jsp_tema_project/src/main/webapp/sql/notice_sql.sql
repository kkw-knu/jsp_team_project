create table notice(
	notice_num number primary key, --key
	notice_title varchar2(50) not null, -- qna 제목
	notice_content varchar2(2048) not null, -- qna 본문
	notice_writer varchar2(20) not null, --qna 작성자
	notice_readcount number default 0, --qna 조회수
	notice_reg_date date not null, --작성일
	notice_del char(1) default 'n' --qna 삭제여부
);
SELECT *
FROM (SELECT * FROM notice ORDER BY notice_reg_date desc)
WHERE rownum <= 3;