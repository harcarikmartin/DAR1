<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

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
					<input type="hidden" name="action" value="userRoles">
					<button class="marginHorizontalLeft buttonStyle" id="ApproveUsers"
						type="submit">User roles</button>
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
	<jsp:include page="Profile.jsp" />
</c:if>

<c:if test="${changePassword == 1}">
			<jsp:include page="PasswordChange.jsp" />
		</c:if>


	<c:if test="${listUsersForApproval == 1 && fn:length(pendingUsers) > 0}">
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
							<td><p class="approveCellStyle paddingHorizontal">${pendingUser}</p></td>
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
				<p class="simpleText">There is no one awaiting to be approved.</p>
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
						<div class="row text-center"><input class="inputForNewStuff" id="addTheTopic" type="text" name="addTheTopic" placeholder="topic name" autofocus maxlength="100"></div>
						<div class="row text-center"><p id="topicNameRequired" class="requiredField ">Topic name is required</p></div>
						<div class="row text-center">
						<input id="addPublic" type="radio" name="visibility" value="public">
						<label class="simpleText" for="public">Public</label>
						<input id="addPrivate" type="radio" name="visibility" value="private" checked>
						<label class="simpleText" for="private">Private</label>
						</div>
						<div class="row text-center">
						<c:if test="${existingTopic != null}">
						<p class="simpleText">Topic with this name already exist!</p>
					</c:if>
						</div>
						<div class="row text-center"><p id="topicVisibilityRequired" class="requiredField ">Topic visibility is required</p></div>
						<div class="row text-center">
						<input type="hidden" name="action" value="addTheTopic">
						<button class="buttonStyle" type="submit">Add topic</button>
						</div>
				</form>
			</div>
		</div>
	</c:if>

	<c:if test="${topicToUpdate != null}">
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">

				<form method="post" onsubmit="return checkRequiredEditTopic()">
					<div class="row text-center">
						<label class="simpleText" for="editTopic">Edit Topic: </label>
					</div>
					<div class="row text-center">
						<input class="inputForNewStuff" id="editTopic" type="text" name="editTopic" placeholder="topic name" value="${topicUpdating.topic}" autofocus maxlength="100">
					</div>
					<div class="row text-center">
						<p id="topicNameRequired" class="requiredField ">Topic name is required</p>
					</div>
					<div class="row text-center">
						<c:if test="${topicUpdating.visibility == 'public'}">
							<input type="radio" name="visibility1" value="public" checked>
							<label class="simpleText" for="public">Public</label>
							<input type="radio" name="visibility1" value="private">
							<label class="simpleText" for="private">Private</label>
						</c:if>
						<c:if test="${topicUpdating.visibility == 'private'}">
							<input type="radio" name="visibility1" value="public">
							<label class="simpleText" for="public">Public</label>
							<input type="radio" name="visibility1" value="private" checked>
							<label class="simpleText" for="private">Private</label>
						</c:if>
					</div>
					<div class="row text-center">
						<p id="topicVisibilityRequired" class="requiredField ">Topic visibility is required</p>
					</div>
					<div class="row text-center">
						<c:if test="${existingTopic != null}">
							<p class=" text-center simpleText">Topic with this name already exist!</p>
						</c:if>
					</div>
					<div class="row text-center">
						<input type="hidden" name="original" value="${topicUpdating.topic}"> <input type="hidden" name="action" value="updateTheTopic">
						<button class="buttonStyle" type="submit">Edit topic</button>
					</div>
				</form>
			</div>
		</div>
	</c:if>
	<c:if test="${listUserRoles == 1}">
		<div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<table
					class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-2 col-xs-8">
					<tr>
						<th colspan="3" class="approveHeadStyle text-center">New
							users</th>
					</tr>
					<c:forEach items="${users}" var="user">
						<c:if test="${user.userName != 'jozko'}">
							<tr>
								<td><p class="approveCellStyle paddingHorizontal">${user.userName}</p></td>
								<td>
									<c:if test="${user.role == 'user'}">
									<form method="post">
										<input type="hidden" name="userForToggle"
											value="${user.userName}"> 
										<input type="hidden"
											name="action" value="toggleUserRole">
										<input type="hidden" name="newRole"
											value="admin">	
										<button class="btn-block buttonStyle" type="submit">Promote to Admin</button>
									</form>
									</c:if>
								</td>
								<td>
									<c:if test="${user.role == 'admin'}">
									<form method="post">
										<input type="hidden" name="userForToggle"
											value="${user.userName}"> 
										<input type="hidden"
											name="action" value="toggleUserRole">
										<input type="hidden" name="newRole"
											value="user">
										<button class="btn-block buttonStyle" type="submit">Degrade to user</button>
									</form>
									</c:if>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:if>


