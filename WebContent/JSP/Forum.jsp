<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<form method="post">
    <div class="left">
        <label for="userName">Name:</label>
        <input id="userName" class="userName" type="text" name="userName" placeholder="name ...">
        <label for="userPassword">Password:</label>
        <input id="userPassword" class="userPassword" type="text" name="userPassword" placeholder="password ...">
        <button name="action" value="login" type="submit">Login</button>
    </div>
    <div class="right">
        <button name="action" value="registrationShow" type="submit">Registration</button>
    </div>
    </form>
    <div class="clear"></div>
</div>

<div class="content">

    <div>
    <form method="post">
        <div class="leftRegistration">
            <label for="userNameReg">Name:</label>
        </div>
        <div class="rightRegistration">
            <input id="userNameReg" class="userName" type="text" name="userName" placeholder="name ...">
        </div>
        <div class="clear"></div>
        <div class="leftRegistration">
            <label for="userPassReg">Password:</label>
        </div>
        <div class="rightRegistration">
            <input id="userPassReg" class="userPassword" type="text" name="userPassword" placeholder="password ...">
        </div>
        <div class="clear"></div>
        <div class="leftRegistration">
            <label for="userPassRegCheck">Password for check:</label>
        </div>
        <div class="rightRegistration">
            <input id="userPassRegCheck" class="userPasswordCheck" type="text" name="userPasswordCheck" placeholder="password again ...">
        </div>
        <div class="clear"></div>
        <div class="leftRegistration">
            <label for="Birthdate">Birthdate:</label>
        </div>
        <div class="rightRegistration">
            <input id="Birthdate" class="Birthdate" type="date" name="Birthdate">
        </div>
        <div class="clear"></div>
        <div class="leftRegistration"></div>
        <div class="rightRegistration">
            <button name="action" value="registration" type="submit">Registration</button>
        </div>
        <div class="clear"></div>
        
        </form>
        <div class="clear"></div>
    </div>
</div>

<div>
    <table>
        <tr>
            <th class="topics">Topic 1</th>
        </tr>
        <tr>
            <th class="topics">Topic 1</th>
        </tr>
    </table>
</div>
</body>
</html>