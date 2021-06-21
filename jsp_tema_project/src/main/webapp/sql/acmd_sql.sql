create table acmd(
	acmd_num number primary key,
	acmd_name varchar2(50) not null,
	acmd_img varchar2(200) not null,
	acmd_local varchar2(50) not null,
	acmd_content varchar2(2048) not null,
	acmd_mini varchar2(50) not null,
	acmd_travel varchar2(50)
);

drop table acmd;