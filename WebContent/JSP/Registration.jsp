<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Registration form</title>
<style>
   <%@ include file="normalize.css"%>
   <%@ include file="Forum.css"%>
</style>
</head>
    <body>
    <div class="centerAlign"><a href="google.com"><h3>Forum</h3></a></div>
    <div class="content">
    <div class="left">
    <label for="userName">Name:</label>
    <input id="userName" class="userName" type="text" name="userName" placeholder="name ...">
    <label for="userPassword">Password:</label>
    <input id="userPassword" class="userPassword" type="text" name="userPassword" placeholder="password ...">
    <button name="action" value="login" type="submit">Login</button>
    </div>
    <div class="right">
    <button name="action" value="registration" type="submit">Registration</button>
    </div>
    </div>
</body>
</html>