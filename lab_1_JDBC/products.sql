
connect 'jdbc:derby://localhost:1527/myDB;
create=true;user=me;password=mine';


 -- drop table shop;


 create table products (id integer, genre varchar(100), author varchar(100), title varchar(100));


disconnect;
exit;
