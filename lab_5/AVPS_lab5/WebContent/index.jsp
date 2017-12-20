<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Книжный магазин</title>
        <style>
			input {font-size: medium;}
		</style>
    </head>
    <body>
	    <font size=5px>
	    <b>Авторизируйтесь, пожалуйста, в системе</b>
	    <form action="LogInServlet" method="post" style="margin-top: 10px;">	
			<p>Имя пользователя: <input type="text" name="userName"></p>
	    	<p>Пароль: <input type="password" name="password"></p>
	    	<p><input type="submit" value="ОК"></p>
	    </form>
	    <br>
		</font>
    </body>
</html>
