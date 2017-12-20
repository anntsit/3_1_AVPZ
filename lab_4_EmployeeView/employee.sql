-- подключение
connect 'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine';

-- раскомментируйте следующую строку, если требуется пересоздать таблицу
-- drop table employee;
 
-- создание таблицы
create table employee(id integer, first_name varchar(20), last_name varchar(20), designation varchar(20), phone varchar(20));

--вставка тестовых данных
insert into employee values (1, 'Ivan', 'Ivanov', 'Manager', '11-22-33');
insert into employee values (2, 'Nikolay', 'Ivanov', 'Programmer', '33-44-55');
insert into employee values (3, 'Sergey', 'Petrov', 'System administrator', '12-34-56');
insert into employee values (4, 'Alexey', 'Petrov', 'Manager', '56-78-90');
insert into employee values (5, 'Vitaliy', 'Kuznetsov', 'Technician', '55-66-77');

-- выбрать все из таблицы для проверки
select * from employee;

-- отключение и выход
disconnect;
exit;
