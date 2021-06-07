-- alt + s : 한줄실행
-- 범위 설정하고 alt + x : 여러줄 실행

create user scott identified by 1234;
alter user scott account unlock;
grant connect to scott;
grant resource to scott;
/*scott사용자계정 생성 후 권한 부여*/

create table admin(
	admin_id varchar2(20) primary key,
	admin_password varchar2(20) not null
);
-- admin table 생성

insert into admin values ('admin', '1234');
-- admin 정보 삽입

create table member(
	user_id varchar2(20) primary key,
	user_password varchar2(20) not null,
	user_name varchar2(20) not null,
	user_tel varchar2(20) not null,
	user_address varchar2(20) not null,
	user_email varchar2(40) not null,
	user_date date not null,
	user_del varchar2(20) not null
);

drop table member;