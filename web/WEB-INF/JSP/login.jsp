<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 09-Jul-20
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">


    <script type="text/javascript" src="javascript/lib/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="javascript/login.js"></script>

    <title>Login example</title>
</head>
<body>
<h1>Login</h1>
<form action="login" method="post">
    <label for="userName">UserName:</label>
    <input type="text" name="userName" id="userName">
    <br><br>
    <label for="parola">Parola:</label>
    <input type="password" name="password" id="parola">
    <input type="checkbox" id="showPassword">
    <br>${message}
    <br><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
