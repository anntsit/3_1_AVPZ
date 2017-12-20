<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="employeeview.Employee, java.util.*, java.io.*" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Оновлення даних співробітника</title>
	<style type="text/css">
		<jsp:include page="index.css" />
	</style>	
</head>
	
<body>
	<div class="table_name">
        <h1>Пошук співробітників</h1>
    </div>
    
	<div class="main_table">

	<%Employee employee = (Employee)request.getAttribute("selectedEmployee"); %>

	<div id="table">
		<div class="row header green">
			<div class="cell">
				Оновлення даних співробітника: <%out.print(employee.getFirstName() + " " + employee.getLastName());%>
			</div>
		</div>
	</div>
	
	<form action="UpdateEmployee" method="post">
		<% 
			out.print("<input type = \"hidden\" value = \""+ employee.getId() +"\" name = \"id\">");
		%>
	
		<table>
			<tr>
				<td><div class="cell">Ім'я:</div></td>
				<td><div class="search"><%out.print("<input type=\"text\" required=\"required\" name=\"FirstName\" value=\"" + employee.getFirstName() + "\"/>"); %></div></td>
			</tr>
			<tr>
				<td><div class="cell">Прізвище:</div></td>
				<td><div class="search"><%out.print("<input type=\"text\" required=\"required\" name=\"LastName\" value=\"" + employee.getLastName() + "\"/>"); %></div></td>
			</tr>
			<tr>
				<td><div class="cell">Посада:</div></td>
				<td><div class="search"><%out.print("<input type=\"text\" required=\"required\" name=\"Designation\" value=\"" + employee.getDesignation() + "\"/>"); %></div></td>
			</tr>
			<tr>
				<td><div class="cell">Телефон:</div></td>
				<td><div class="search"><%out.print("<input type=\"text\" required=\"required\" name=\"Phone\" value=\"" + employee.getPhone() + "\"/>"); %></div></td>
			</tr>
		</table>	
		
		<input type="submit" class="green_button" value="Оновити дані">
		
	</form>
	
	
</body>
</html>