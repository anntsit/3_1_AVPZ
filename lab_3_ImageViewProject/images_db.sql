-- �����������
connect 'jdbc:derby://localhost:1527/imagesDB;create=true;user=me;password=mine';

-- �������� �������
create table file_info_table(
	file_name varchar(100), 
	file_author varchar(100), 
	file_creation_date varchar(100), 
	file_description varchar(1000));

-- ���������� � �����
disconnect;
exit;
