<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<c:if test="${user.role=='user'}">

	<div class="row col-lg-12">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left col-lg-6">
					<form method="post">
						<label class="labels" for="SeeProfile">My profile:</label> <input
							type="hidden" name="action" value="showProfile">
						<button class="buttonStyle" type="submit">Profile</button>
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
				<form method="post">
					<input type="hidden" name="action" value="showMyTopics">
					<button class="buttonStyle" type="submit">Subscribed
						topics</button>
				</form>
			</div>
		</div>

		<c:if test="${changePassword == 1 }">
			<div class="row col-lg-12">
				<div class="col-lg-offset-1 col-lg-10 rowBackground ">
					<form method="post">
						<table class="col-lg-offset-2 col-lg-8">
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
</c:if>




		<c:if test="${listTopics != null}">
		<div class="row col-lg-12">
				<div class="col-lg-offset-1 col-lg-10 rowBackground ">
			<form method="post">
				<table>
					<tr>
						<th class="subscribedTopicsHeadStyle">Name of topic</th>
					</tr>
					<c:forEach var="topic" items="${topics}">
						<c:if test="${topic.visibility == 'private'}">
							<tr>
								<td class="subscribedTopicsCellStyle"><input type="checkbox" name="topic"
									value="${topic.topicID}"
									<c:forEach var="userTopic" items="${userTopics}">
									<c:if test="${topic.topicID == userTopic.topic.topicID}">
										<c:if test="${userTopic.user.userID == user.userID}">
										checked 
										</c:if>
									</c:if>
								</c:forEach>>
									${topic.topic}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<input type="hidden" name="action" value="changeTopics">
				<button type="submit">Change topics</button>
			</form>
			</div>
			</div>
		</c:if>
	
</c:if>

