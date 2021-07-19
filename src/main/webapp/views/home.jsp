<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FESC</title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
    </head>
    <body>
        <h1>Bem vindo ao CarePlan Management 1.0 - FESC</h1>
        <form action="${pageContext.request.contextPath}/careplanapp" method="post">
            <p><font color="red">${errorMessage}</font></p>
                Name: <input type="text" name="name"/>
                Password: <input type="password" name="password"/>
            <input type="submit" value="submit"/>
        </form>                
    </body>
</html>
