<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<c:if test="${user.role == 'admin'}">

	<div class="row col-lg-12">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left col-lg-6">
					<form method="post">
						<label class="labels" for="SeeProfile">My profile:</label> <input type="hidden"
							name="action" value="showProfile">
						<button class="buttonStyle" id="ApproveUsers" type="submit">Profile</button>
					</form>
				</div>
				<div class="text-left col-lg-6">
					<form method="post">
						<label class="labels" for="ApproveUsers">Approve users:</label> <input
							type="hidden" name="action" value="approve">
						<button class="buttonStyle" id="ApproveUsers" type="submit">Approve Users</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${listProfile != null }">
		<div class="row col-lg-12">
			<div class="col-lg-offset-1 col-lg-10 rowBackground text-left">
				<p class="simpleText">Username: ${user.userName}</p>
				<p class="simpleText">Birthdate: ${user.birthDate}</p>
				<p class="simpleText">Role: ${user.role}</p>
				<p class="simpleText">Status: ${user.status}</p>
				<form method="post">
					<input type="hidden" name="action" value="changePassword">
					<button class="buttonStyle" type="submit">Change Password</button>
				</form>
			</div>
		</div>


<!-- not done yet -->
		<c:if test="${passChanged == 1 }">
			<p>Password changed successfully</p>
		</c:if>
		<c:if test="${passChanged == 0 }">
			<p>Password not changed!</p>
		</c:if>
		<c:if test="${changePassword == 1 }">
			<form method="post">
				<label for="OldPassword">Old password:</label> <input
					id="OldPassword" type="password" name="userPasswordOld"
					placeholder="password"><br> <label for="NewPassword">New
					password:</label> <input id="NewPassword" type="password"
					name="userPasswordNew"
					pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
					oninvalid="alert('Password is not valid')" placeholder="password">
				<p>* Password must contain minimum 8 characters, at least 1
					Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special
					Character [!@#$%^&*_=+-]</p>
				<label for="NewPasswordConfirm">Confirm new password:</label> <input
					id="NewPasswordConfirm" type="password" name="userPasswordNewCheck"
					placeholder="password"><br> <input type="hidden"
					name="action" value="changeMyPassword">
				<button type="submit">Change</button>
			</form>
		</c:if>
	</c:if>



	<c:if test="${listUsersForApproval != null }">
	<div class="row col-lg-12">
		<div class="col-lg-offset-1 col-lg-10 rowBackground ">
		<table  class="col-lg-offset-2 col-lg-8">
			<tr>
				<th class="approveHeadStyle text-center">User</th>
				<th class="approveHeadStyle text-center">Approve</th>
				<th class="approveHeadStyle text-center">Remove</th>
			</tr>
			<c:forEach items="${pendingUsers}" var="pendingUser">
				<tr>
					<td><p class="approveCellStyle">${pendingUser}</p></td>
					<td>
						<form method="post">
							<input type="hidden" name="userForApproval"
								value="${pendingUser}"> <input type="hidden"
								name="action" value="approveUser">
							<button class="buttonStyle" type="submit">Approve</button>
						</form>
					</td>
					<td>
						<form method="post">
							<input type="hidden" name="userForApproval"
								value="${pendingUser}"> <input type="hidden"
								name="action" value="dropUser">
							<button class="buttonStyle" type="submit">Remove</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</c:if>

</c:if>