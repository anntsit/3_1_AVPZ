-- �����������
connect 'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine';

-- ���������������� ��������� ������, ���� ��������� ����������� �������
-- drop table employee;
 
-- �������� �������
create table employee(id integer, first_name varchar(20), last_name varchar(20), designation varchar(20), phone varchar(20));

--������� �������� ������
insert into employee values (1, 'Ivan', 'Ivanov', 'Manager', '11-22-33');
insert into employee values (2, 'Nikolay', 'Ivanov', 'Programmer', '33-44-55');
insert into employee values (3, 'Sergey', 'Petrov', 'System administrator', '12-34-56');
insert into employee values (4, 'Alexey', 'Petrov', 'Manager', '56-78-90');
insert into employee values (5, 'Vitaliy', 'Kuznetsov', 'Technician', '55-66-77');

-- ������� ��� �� ������� ��� ��������
select * from employee;

-- ���������� � �����
disconnect;
exit;
