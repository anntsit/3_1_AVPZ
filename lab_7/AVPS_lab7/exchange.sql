-- �����������
connect 'jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine';

-- �������� �������
--create table exchange(currency varchar(3), USD float, EUR float, UAH float);

--������� �������� ������
--insert into exchange values ('USD', 1, 1.18, 27.2);
--insert into exchange values ('EUR', 0.84, 1, 31.3);
--insert into exchange values ('UAH', 0.04, 0.03, 1);

-- ������� ��� �� ������� ��� ��������
select * from exchange;

-- ���������� � �����
disconnect;
exit;
