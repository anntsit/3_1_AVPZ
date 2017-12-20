<%@page errorPage="errorPage.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Iterator" %>
<%@page import="javax.naming.AuthenticationException" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	if (session.getAttribute("user") == null)
		throw new AuthenticationException("Ошибка аутентификации: неверное имя пользователя/пароль");
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Книжный магазин</title>
		<style>
			input {font-size: medium;}
			img {width: 5%;}
		</style>
	</head>
	<body>
	    <font size=5px>
	    <a href='bookCart.jsp'>
	    	<img src='cartbg.png'><b>Перейти в корзину</b>
	    </a><br><br>
		<%
			HashMap<String, Integer[]> booksBasket = (HashMap<String, Integer[]>) session.getAttribute("booksBasket");
		
			String bookName = request.getParameter("bookName");
			String bookPriceStr = request.getParameter("bookPrice");
			Integer bookPrice = bookPriceStr == null ? null : new Integer(bookPriceStr);
			String bookCountStr = request.getParameter("bookCount");
			Integer bookCount = (bookCountStr == null || Integer.parseInt(bookCountStr) <= 0) ? null : new Integer(bookCountStr);
			
			if (bookName != null && bookPrice != null && bookCount != null) {
				if (!booksBasket.containsKey(bookName))
					booksBasket.put(bookName, new Integer[] {bookPrice, bookCount});
				else {
					Integer oldCount = booksBasket.get(bookName)[1];
					Integer newCount = bookCount + oldCount;
					booksBasket.put(bookName, new Integer[] {bookPrice, newCount});
				}
			}
		%>
					
		<%@ taglib uri="/WEB-INF/BookTagLib.tld" prefix="x" %>

		<table border=1 width=60%>
			<tr style="font-weight: bold;">
				<td>Код товара</td>
			    <td>Название</td>
			    <td>Автор</td>
			    <td>Цена</td>
			    <td></td>
			</tr>
			<x:BookTagHandler 
				pageURL="bookStore.jsp" 
			    bookId="001" 
			    name="Thinking in Java" 
			    author="Bruce Eckel" 
			    price="400"
			    buttonValue ="Добавить в корзину"/>
			<x:BookTagHandler 
			    pageURL="bookStore.jsp" 
			    bookId="002" 
			    name="Bitter Java" 
			    author="Bruce Tate" 
			    price="300"
			    buttonValue ="Добавить в корзину"/>                
			<x:BookTagHandler 
				pageURL="bookStore.jsp" 
			    bookId="003" 
			    name="Object-Oriented Design Patterns" 
			    author="Erich Gamma et al." 
			    price="500"
			    buttonValue ="Добавить в корзину"/>
		</table>
		</font>
	</body>
</html>
