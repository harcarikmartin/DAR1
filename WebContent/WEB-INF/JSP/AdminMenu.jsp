<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html> -->

//Admin user is able to see admin interface where he can approve users or disable

<c:if test="${user.role == 'admin'}">
	<form>
		<label for="SeeProfile">My profile:</label>
		<input type="hidden" name="action" value="profile" >
	    <button id="ApproveUsers" type="submit">Profile</button>
	</form>
	<c:if test="${listProfile != null }">
	<div>
		<p>Username: ${user.userName} <a href="">change</a></p>
		<p>Birthdate: ${user.birthDate}</p>
		<p>Role: ${user.role}</p>
		<p>Status: ${user.status}</p>
		<p></p>
	</div>
	</c:if>
	
	<form>
		<label for="ApproveUsers">Approve users:</label>
		<input type="hidden" name="action" value="approve" >
	    <button id="ApproveUsers" type="submit">Approve Users</button>
	</form>
	<c:if test="${listUsersForApproval != null }">
	</c:if>
</c:if>