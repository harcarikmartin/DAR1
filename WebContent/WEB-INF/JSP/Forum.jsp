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

<c:if test="${user == null}">
<div id="login" class="content">
<form method="post">
    <div class="left">
        <label for="userName"><h4>Name:</h4></label>
        <input id="userName" class="userName" type="text" name="userName" placeholder="name ...">
        <label for="userPassword">Password:</label>
        <input id="userPassword" class="userPassword" type="text" name="userPassword" placeholder="password ...">
        <button name="action" value="login" type="submit">Login</button>
    </div>
    <div class="right">
        <button name="action" value="registrationShow" type="submit" onclick="registerFcn()">Registration</button>
    </div>
    <div class="clear"></div>
    </form>
</div>
</c:if>

<c:if test="${user != null}">
<div class="content">
    <div class="left">
        <p>Prihlásený ako </p>
    </div>
    <div class="right">
        <button name="action" value="logout" type="submit">Logout</button>
    </div>
</div>
</c:if>

<div id="register" class="content">

    <div>
    <form method="post">
    	<input type="hidden" name="role" value="user">
    	<input type="hidden" name="status" value="pending">
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
        <div class="leftRegistration">
        </div>
        <div class="rightRegistration">
            <button name="action" value="registration" type="submit">Registration</button><br>
            <button type="refresh">Back</button>
        </div>
        <div class="clear"></div>
        
        </form>
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

<script type="text/javascript">
document.getElementById("login").style.display = "inline";
document.getElementById("register").style.display = "none";

function registerFcn(){
	document.getElementById("login").style.display = "none";
	document.getElementById("register").style.display = "inline";
}

</script>

</html>