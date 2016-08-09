<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<c:if test="${user.role == 'admin'}">
	<form method="post">
		<label for="SeeProfile">My profile:</label>
		<input type="hidden" name="action" value="showProfile" >
	    <button id="ApproveUsers" type="submit">Profile</button>
	</form>
	<c:if test="${listProfile != null }">
		<div>
			<p>Username: ${user.userName}</p>
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
		<table>
		<c:forEach items="${pendingUsers}" var="pendingUser">
			<tr>
				<td><p>${pendingUser}</p></td>
				<td>
					<form method="post">
						<input type="hidden" name="userForApproval" value="${pendingUser}">
						<input type="hidden" name="action" value="approveUser" >
						<button type="submit">Approve</button>
					</form>
				</td>
				<td>
					<form method="post">
						<input type="hidden" name="userForApproval" value="${pendingUser}">
						<input type="hidden" name="action" value="dropUser" >
						<button type="submit">Drop</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>

</c:if>