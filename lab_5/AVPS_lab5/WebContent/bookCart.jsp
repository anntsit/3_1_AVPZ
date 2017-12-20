<%@page errorPage="errorPage.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Iterator" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<style>
			input {font-size: medium;}
			img {width: 5%;}
		</style>
	</head>
	<body>
	 <font size=5px>	
	 <a href='bookStore.jsp'>
	 	<img src='bag.png'><b>Вернуться к покупкам</b>
	 </a><br><br>
	 
	 <%@ taglib uri="/WEB-INF/BookOrderTagLib.tld" prefix="x" %>
	 
	 <%
			HashMap<String, Integer[]> booksBasket = (HashMap<String, Integer[]>) session.getAttribute("booksBasket");
		
			if (booksBasket.size() == 0)	
				out.print("<b>Ваша корзина пуста</b>");
		%>
		<table border="1" width="50%">
			<tr style="font-weight: bold;">
				<td>Название</td>
				<td>Количество</td>
				<td>Сумма</td>
				<td></td>		
			</tr>
			<x:BookOrderTagHandler 
				name="Thinking in Java"
				price="400" />
			<x:BookOrderTagHandler 
				name="Bitter Java"
				price="300" />
			<x:BookOrderTagHandler 
				name="Object-Oriented Design Patterns"
				price="500" />
		</table>
	 </font>
	</body>
</html>