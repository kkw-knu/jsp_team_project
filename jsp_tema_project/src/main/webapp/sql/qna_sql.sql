create table qna(
	qna_num number primary key, --key
	qna_title varchar2(50) not null, -- qna 제목
	qna_content varchar2(2048) not null, -- qna 본문
	qna_writer varchar2(20) not null, --qna 작성자
	qna_readcount number default 0, --qna 조회수
	qna_ref number not null, --답변글 그룹
	qna_re_step number not null, --ref내 순서
	qna_re_level number not null, --들여쓰기
	qna_reg_date date not null, --작성일
	qna_del char(1) default 'n' --qna 삭제여부
);