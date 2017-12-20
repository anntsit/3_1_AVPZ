<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="employeeview.Employee, java.util.*, java.io.*" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Пошук співробітників</title>
	<style type="text/css">
		<jsp:include page="index.css" />
	</style>	
</head>
	
<body>
	<div class="table_name">
        <h1>Пошук співробітників</h1>
    </div>
    
	<div class="main_table">
	<form action="EmployeeServlet">
		<div class="search">
			<%out.print("<input type=\"text\" name=\"lastname\" value=\"" + 
					request.getAttribute("searchQuery") + "\"/>"); %>
			<input type="submit" class="green_button" value="Пошук">
		</div>
	</form>

	<div id="table">
		<div class="row header green">
			<div class="cell">Id</div>
			<div class="cell">Ім'я</div>
			<div class="cell">Прізвище</div>
			<div class="cell">Посада</div>
			<div class="cell">Телефон</div>
			<div class="cell"></div>
		</div>
		
		<%
		ArrayList employees = (ArrayList)request.getAttribute("employeesFound");
		if (employees != null) {
			if (employees.size()==0) {
				out.print("<div class=\"row\">" + "Співробітників не знайдено" + "</div>");
			}
			else {
				for (int i = 0; i < employees.size(); i++) {
					out.print("<div class=\"row\">");
					Employee emp = (Employee) employees.get(i);
					out.print("<div class=\"cell\">" + emp.getId() + "</div>");
					out.print("<div class=\"cell\">" + emp.getFirstName() + "</div>");
					out.print("<div class=\"cell\">" + emp.getLastName() + "</div>");
					out.print("<div class=\"cell\">" + emp.getDesignation() + "</div>");
					out.print("<div class=\"cell\">" + emp.getPhone() + "</div>");
					out.print("<div class=\"cell\"><form action=\"UpdateEmployee\">");
					out.print("<input type = \"hidden\" value = \""+ emp.getId() +"\" name = \"id\">");
					out.print("<input type=submit class=\"green_button\" value=\"Змінити\"> ");
					out.print("</form></div></div>");
				}
			}
		}%>
	</div>
</body>
</html>
