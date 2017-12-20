<%@ page isErrorPage="true" pageEncoding="UTF-8" session="false" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ошибка</title>
	</head>
	<body>       
		<h2>
		    <%= exception.getClass().getName() %>
		    <br>
		    <%= exception.getMessage() %>
		</h2>
	    <font size=10px> 
			<% exception.printStackTrace(); %>
		</font>
	</body>
</html>
