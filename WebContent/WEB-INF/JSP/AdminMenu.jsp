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



<c:if test="${user.role == 'admin'}">
	<form method="post">
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
			<form method="post">
				<input type="hidden" name="action" value="changePassword" >
	    		<button type="submit">Change Password</button>
			</form>
		</div>
		<c:if test="${passChanged == 1 }">
			<p>Password changed successfully</p>
		</c:if>
		<c:if test="${passChanged == 0 }">
			<p>Password not changed!</p>
		</c:if>
		<c:if test="${changePassword == 1 }">
			<form method="post">
				<label for="OldPassword">Old password:</label>
				<input id="OldPassword" type="password" name="userPasswordOld" placeholder="password"><br>
				<label for="NewPassword">New password:</label>
				<input id="NewPassword"type="password" name="userPasswordNew" 
           		pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" 
            	oninvalid="alert('Password is not valid')" placeholder="password">
            	<p>* Password must contain minimum 8 characters, at least 1 Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special Character [!@#$%^&*_=+-]</p>
				<label for="NewPasswordConfirm">Confirm new password:</label>
				<input id="NewPasswordConfirm" type="password" name="userPasswordNewCheck" placeholder="password"><br>
				
				<input type="hidden" name="action" value="changeMyPassword" >
	    		<button type="submit">Change</button>
			</form>
		</c:if>
	</c:if>
	
	<form method="post">
		<label for="ApproveUsers">Approve users:</label>
		<input type="hidden" name="action" value="approve" >
	    <button id="ApproveUsers" type="submit">Approve Users</button>
	</form>
	<c:if test="${listUsersForApproval != null }">
		<c:forEach items="${pendingUsers}" var="pendingUser">
			<p>${pendingUser }<a href="?action=approveUser&amp;user=${pendingUser.userName}">Approve</a></p>
		</c:forEach>
	</c:if>
</c:if>