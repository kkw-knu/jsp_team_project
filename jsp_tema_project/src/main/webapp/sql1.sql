create user scott identified by 1234;
alter user scott account unlock;
grant connect to scott;
grant resource to scott;
/*scott사용자계정 생성 후 권한 부여*/