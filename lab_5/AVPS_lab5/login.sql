connect 'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine';

--create table login(user_name varchar(20), password varchar(20));

--insert into login values ('user', 'password');

select * from login;

disconnect;
exit;
