<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<c:if test="${user.role == 'admin'}">

	<div class="row col-lg-12">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left col-lg-6">
					<form method="post">
						<label class="labels" for="SeeProfile">My profile:</label> <input
							type="hidden" name="action" value="showProfile">
						<button class="buttonStyle" id="ApproveUsers" type="submit">Profile</button>
					</form>
				</div>
				<div class="text-left col-lg-6">
					<form method="post">
						<label class="labels" for="ApproveUsers">Approve users:</label> <input
							type="hidden" name="action" value="approve">
						<button class="buttonStyle" id="ApproveUsers" type="submit">Approve
							Users</button>
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

		<c:if test="${changePassword == 1 }">
			<div class="row col-lg-12">
				<div class="col-lg-offset-1 col-lg-10 rowBackground ">
					<form method="post">
						<table class="col-lg-offset-2 col-lg-8">
							<tr>
								<td><label class="simpleText" for="OldPassword">Old
										password:</label></td>
								<td class="registerCellStyle"><input id="OldPassword"
									class="passwordForm" type="password" name="userPasswordOld"
									placeholder="password"></td>
							</tr>
							<tr>
								<td><label class="simpleText" for="NewPassword">New
										password:</label></td>
								<td class="registerCellStyle"><input id="NewPassword"
									class="passwordForm" type="password" name="userPasswordNew"
									pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
									oninvalid="alert('Password is not valid')"
									placeholder="password"></td>
							</tr>
							<tr>
								<td></td>
								<td class="registerCellStyle"><p class="topicInfoStyle">*
										Password must contain minimum 8 characters, at least 1
										Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special
										Character [!@#$%^&*_=+-]</p></td>
							</tr>
							<tr>
								<td><label class="simpleText" for="NewPasswordConfirm">Confirm
										new password:</label></td>
								<td class="registerCellStyle"><input
									id="NewPasswordConfirm" class="passwordForm" type="password"
									name="userPasswordNewCheck" placeholder="password"></td>
							</tr>
							<tr>
								<td></td>
								<td><c:if test="${passChanged == 1 }">
										<p class="simpleText">Password changed successfully</p>
									</c:if> <c:if test="${passChanged == 0 }">
										<p class="simpleText">Password not changed!</p>
									</c:if></td>
							</tr>
							<tr>
								<td></td>
								<td class="registerCellStyle"><input type="hidden"
									name="action" value="changeMyPassword">
									<button class="buttonStyle" type="submit">Change</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</c:if>
	</c:if>

	<c:if test="${listUsersForApproval != null }">
		<div class="row col-lg-12">
			<div class="col-lg-offset-1 col-lg-10 rowBackground ">
				<table class="col-lg-offset-2 col-lg-8">
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