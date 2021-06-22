create table travel(
	travel_num number primary key,
	travel_name varchar2(50) not null,
	travel_img varchar2(200) not null,
	travel_local varchar2(50) not null,
	travel_content varchar2(2048) not null,
	travel_mini varchar2(50) not null,
	travel_q1 varchar2(50) not null,
	travel_q2 varchar2(50) not null,
	travel_q3 varchar2(50) not null,
	travel_q4 varchar2(50) not null,
	travel_star number not null --별점 총합
);
/*필요한것 질문내용 이미지경로 제목 내용 지역 */
/*
 * 당일, 1박2일, 3박4일, 장기
 * 봄, 여름, 가을, 겨울
 * 캠핑, 산, 바다, 테마
 * 혼자, 커플, 가족
 * 
 */
drop table travel;