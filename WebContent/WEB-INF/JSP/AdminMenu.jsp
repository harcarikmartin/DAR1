<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<c:if test="${user.role == 'admin'}">

	<div class="row col-lg-12">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="text-left text-center-xs">
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="showProfile">
					<button class="marginHorizontalLeft buttonStyle" id="SeeProfile"
						type="submit">Profile</button>
				</form>
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="changePassword">
					<button class="marginHorizontalLeft buttonStyle" type="submit">Change Password</button>
				</form>
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="approve">
					<button class="marginHorizontalLeft buttonStyle" id="ApproveUsers"
						type="submit">Approve Users</button>
				</form>
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="addTopic">
					<button class="marginHorizontalLeft buttonStyle" id="addTopic"
						type="submit">Add topic</button>
				</form>
			</div>
		</div>
	</div>


		<c:if test="${listProfile != null }">
		<div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<table
					class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-2 col-xs-8">
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Username:</td>
						<td class="userProfileInfo paddingHorizontal">${user.userName}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Birthdate:</td>
						<td class="userProfileInfo paddingHorizontal">${user.birthDate}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Role:</td>
						<td class="userProfileInfo paddingHorizontal">${user.role}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Status:</td>
						<td class="userProfileInfo paddingHorizontal">${user.status}</td>
					</tr>
				</table>

			</div>
		</div>
	</c:if>

		<c:if test="${changePassword == 1 }">
			<div class="row col-lg-12">
				<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground ">
					<form method="post">
						<table class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-2 col-xs-8">
							<tr>
								<td class="passwordCellStyle"><label class="simpleText" for="OldPassword">Old
										password:</label></td>
								<td class="passwordCellStyle"><input id="OldPassword"
									class="passwordForm" type="password" name="userPasswordOld"
									placeholder="password"></td>
							</tr>
							<tr>
								<td class="passwordCellStyle"><label class="simpleText" for="NewPassword">New
										password:</label></td>
								<td class="passwordCellStyle"><input id="NewPassword"
									class="passwordForm" type="password" name="userPasswordNew"
									pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
									oninvalid="alert('Password is not valid')"
									placeholder="password"></td>
							</tr>
							<tr>
								<td class="passwordCellStyle"></td>
								<td class="passwordCellStyle"><p class="topicInfoStyle">*
										Password must contain minimum 8 characters, at least 1
										Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special
										Character [!@#$%^&*_=+-]</p></td>
							</tr>
							<tr>
								<td class="passwordCellStyle"><label class="simpleText" for="NewPasswordConfirm">Confirm
										new password:</label></td>
								<td class="passwordCellStyle"><input
									id="NewPasswordConfirm" class="passwordForm" type="password"
									name="userPasswordNewCheck" placeholder="password"></td>
							</tr>
							<tr>
								<td class="passwordCellStyle"></td>
								<td><c:if test="${passChanged == 1 }">
										<p class="simpleText">Password changed successfully</p>
									</c:if> <c:if test="${passChanged == 0 }">
										<p class="simpleText">Password not changed!</p>
									</c:if></td>
							</tr>
							<tr>
								<td class="passwordCellStyle"></td>
								<td class="passwordCellStyle"><input type="hidden"
									name="action" value="changeMyPassword">
									<button class="buttonStyle" type="submit">Change</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</c:if>


	<c:if test="${listUsersForApproval == 1 }">
		<div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<table
					class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-2 col-xs-8">
					<tr>
						<th colspan="3" class="approveHeadStyle text-center">New
							users</th>
					</tr>
					<c:forEach items="${pendingUsers}" var="pendingUser">
						<tr>
							<td><p class="approveCellStyle">${pendingUser}</p></td>
							<td>
								<form method="post">
									<input type="hidden" name="userForApproval"
										value="${pendingUser}"> <input type="hidden"
										name="action" value="approveUser">
									<button class="btn-block buttonStyle" type="submit">Approve</button>
								</form>
							</td>
							<td>
								<form method="post">
									<input type="hidden" name="userForApproval"
										value="${pendingUser}"> <input type="hidden"
										name="action" value="dropUser">
									<button class="btn-block buttonStyle" type="submit">Remove</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${listUsersForApproval == 2 }">
		<div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground text-center">
				<p class="simpleText">There is no one, who can be approved.</p>
			</div>
		</div>
	</c:if>


	<c:if test="${topicAdding != null}">
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<form method="post" onsubmit="return checkRequiredAddTopic()">
					<c:if test="${existingTopic != null}">
						<p class="simpleText">Topic with this name already exist!</p>
					</c:if>
						<div class="row text-center"><label class="simpleText" for="addTheTopic">Add new Topic:</label></div>
						<div class="row text-center"><input class="inputForNewStuff" id="addTheTopic" type="text" name="addTheTopic" placeholder="topic name"></div>
						<div class="row text-center"><p id="topicNameRequired" class="requiredField ">Topic name is required</p></div>
						<div class="row text-center">
						<input id="addPublic" type="radio" name="visibility" value="public">
						<label class="simpleText" for="public">Public</label>
						<input id="addPrivate" type="radio" name="visibility" value="private" checked>
						<label class="simpleText" for="private">Private</label>
						</div>
						<div class="row text-center"><p id="topicVisibilityRequired" class="requiredField ">Topic visibility is required</p></div>
						<div class="row text-center">
						<input type="hidden" name="action" value="addTheTopic">
						<button class="buttonStyle" type="submit">Add Topic</button>
						</div>
				</form>
			</div>
		</div>
	</c:if>

</c:if>