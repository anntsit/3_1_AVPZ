<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<%@page import="avps.labs.client.stubs.Currency"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Конвертер валют</title>
		<style>
			select, select option, input {font-size: medium;}
			img {width: 3vh; padding: 0 1vw 0 1vw;}
		</style>
	</head>
	<body>
	<font size=5px>
		<H3>Валютный калькулятор</H3>
		<form action="CurrencyServlet" method = "GET">
			Конветировать с валюты <select name="currencyFrom" onChange=
			"var sumInput = this.form.elements['resultSum']; if (sumInput != null) sumInput.value = '';">
			<option>
			<%
			Currency currencyInfo = (Currency)request.getAttribute("currencyInfo");
			List<String> currencies  = (List<String>)request.getAttribute("currencies");
			if (currencies != null) {
				String sel;
				for (int i = 0; i < currencies.size(); i++) {
					sel = "";
					// Если валюта уже указана, выбираем её в списке
					if (currencyInfo != null && currencies.get(i).equals(currencyInfo.getCurrencyName())) 
						sel = " selected = \"selected\"";
						out.print("<option" + sel + ">" + currencies.get(i) + "</option>");
				}
			}
			%>
			</select>
			&nbsp;&nbsp;в валюту  <select name="currencyTo" onChange=
			"var sumInput = this.form.elements['resultSum']; if (sumInput != null) sumInput.value = '';">
			<option>
			<%
			String currencyTo = (String)request.getAttribute("currencyTo");
			if (currencies != null) {
				String sel;
				for (int i = 0; i < currencies.size(); i++) {
					sel = "";
					// Если валюта уже указана, выбираем её в списке
					if (currencyInfo != null && currencies.get(i).equals(currencyTo)) 
						sel = " selected = \"selected\"";
						out.print("<option" + sel + ">" + currencies.get(i) + "</option>");
				}
			}
			%>
			</select>
			<p> Денежная сумма 
			<%
				Float initSum = (Float)request.getAttribute("initSum");
				out.print("<input type='text' name='sum' size='10px'");
				if (initSum != null)
					out.print(" value='" + initSum + "'");
				out.print(">");
			%>
			<%
				Float resultSum = (Float)request.getAttribute("resultSum");
				if (resultSum != null) {
					out.print("<img src='arrow.png'>");
					out.print("<input type='text' name='resultSum' size='10px' value='" + resultSum.toString() + "' readonly disabled='disabled'>");
				}
			%>
			</p>
			<p> <input type="submit" value="ОК"> </p>
		</form>
	</font>
	</body>
</html>
