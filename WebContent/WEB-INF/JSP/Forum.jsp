<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Forum</title>
<style>
<%@include file="bootstrap/css/bootstrap.css"%>
<%@include file="ForumForBootstrap.css"%>
</style>
</head>

<body>
	<div class="row col-lg-12">
		<div class=" col-lg-offset-1 col-lg-10 text-center">
			<form method="post">
				<input type="hidden" name="action" value="generate">
				<button class="buttonStyle fullWidth" type="submit">Generate</button>
			</form>
		</div>
		<div class="row">
			<a class="col-lg-10 col-lg-offset-1 col-md-12 col-sm-12 col-xs-12 forumHeader text-center" href="/Forum/Forum"> F-O-R-U-M
<!-- 			<img src="images\forumLink.jpg" alt="Forum"> -->
				</a>
		</div>
	</div>

	<div class="row col-lg-12">
		<c:if test="${user == null  && registerForm == null}">
			<div id="login" class="col-lg-offset-1 col-lg-10 rowBackground">
				<div class="row">
					<div class="text-left col-lg-10 col-md-10 col-sm-10 col-xs-12">
						<form method="post" onsubmit="return checkRequiredLog()">
							<table class="loginTable">
								<tr>
									<td><label class="labels" for="userName">Username:<input
											id="userName" class="loginForm" type="text" name="userName"
											placeholder="name ..." autofocus></label></td>
								</tr>
								<tr>
									<td class="text-right paddingHorizontal"><p
											id="userNameRequired" class="requiredField">Username is
											required</p></td>
								</tr>
							</table>

							<table class="loginTable">
								<tr>
									<td><label class="labels" for="userPassword">Password:
											<input id="userPassword" class="loginForm" type="password"
											name="userPassword" placeholder="password ...">
									</label></td>
								</tr>
								<tr>
									<td class="text-right paddingHorizontal"><p
											id="userPasswordRequired" class="requiredField ">Password
											is required</p></td>
								</tr>
							</table>

							<table class="loginTable">
								<tr>
									<td><input type="hidden" name="action" value="login">
										<button class="marginHorizontal buttonStyle" type="submit">Login</button>
									</td>
								</tr>
							</table>
							<c:if test="${error == '5'}">
								<p class="simpleText">Wrong login details!</p>
							</c:if>
							<c:if test="${error == '6'}">
								<p class="simpleText">Not yet approved by admin!</p>
							</c:if>
						</form>
					</div>
					<div class="text-right  col-lg-2 col-md-2 col-sm-2 col-xs-12">
						<form method="post">
							<input type="hidden" name="action" value="register">
							<button class="marginHorizontal buttonStyle" type="submit">Registration</button>
						</form>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${user != null  && registerForm == null}">
			<div class="col-lg-offset-1 col-lg-10 rowBackground">
				<div class="row">
					<div class="text-left text-center-xs col-lg-8  col-md-8 col-sm-8 col-xs-12">
						<h4 class="simpleText">Logged as: ${user.userName}</h4>
					</div>
					<div class="text-right text-center-xs col-lg-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<form method="post">
							<input type="hidden" name="action" value="logout">
							<button class="marginHorizontal buttonStyle" type="submit">Logout</button>
						</form>
					</div>
				</div>
			</div>
		</c:if>
	</div>

	<jsp:include page="RegisterForm.jsp" />

	<jsp:include page="AdminMenu.jsp" />

	<jsp:include page="UserMenu.jsp" />

	<c:if test="${registerForm == null}">
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10">
				<table>
					<tr>
						<th class="topicCell text-left paddingHorizontal topicHeadStyle">Topic</th>
						<th class="actionCell text-center topicHeadStyle">Created by</th>
						<th class="actionCell text-center topicHeadStyle">Visibility</th>
						<c:if test="${user.role == 'admin'}">
							<th class="actionCell text-center topicHeadStyle">Update</th>
							<th class="actionCell text-center topicHeadStyle">Delete</th>
						</c:if>
					</tr>
					<c:forEach items="${topics}" var="topic">
						<c:if test="${user.role == null}">
							<c:if test="${topic.visibility == 'public'}">
								<tr>
									<td>
										<form method="post">
											<input type="hidden" name="action" value="openTopic">
											<input type="hidden" name="idOfTOpic"
												value="${topic.topicID}">
											<button class="btn-block topicStyle" type="submit">${topic.topic}</button>
										</form>
									</td>
									<td class="actionCell text-center topicInfoStyle">${topic.creator.userName}</td>
									<td class="actionCell text-center topicInfoStyle">${topic.visibility}</td>
								</tr>
							</c:if>
						</c:if>

							<c:if test="${user.role == 'admin'}">
								<tr>
									<td class="topicCell text-left">
										<form method="post">
											<input type="hidden" name="action" value="openTopic">
											<input type="hidden" name="idOfTOpic"
												value="${topic.topicID}">
											<button class="btn-block topicStyle" type="submit">${topic.topic}</button>
										</form>
									</td>
									<td class="actionCell text-center topicInfoStyle">${topic.creator.userName}</td>
									<td class="actionCell text-center topicInfoStyle">${topic.visibility}</td>

									<c:if test="${user.role == 'admin'}">
										<td class="actionCell text-left">
											<form method="post" class="topicToUpdate">
												<input type="hidden" name="topicToUpdate"
													value="${topic.topic}"> <input type="hidden"
													name="action" value="updateTopic">
												<button type="submit" class="btn-block topicStyle">Update</button>
											</form>
										</td>
										<td class="actionCell text-center">
											<form method="post" class="topicToRemove">
												<input type="hidden" name="topicToRemove"
													value="${topic.topic}"> <input type="hidden"
													name="action" value="removeTopic">
												<button type="submit" class="btn-block topicStyle">Remove</button>
											</form>
										</td>
									</c:if>
								</tr>
							</c:if>


						<c:if test="${user.role == 'user'}">
							<c:if test="${topic.visibility == 'public'}">
								<tr>
									<td class="topicCell text-left">
										<form method="post">
											<input type="hidden" name="action" value="openTopic">
											<input type="hidden" name="idOfTOpic"
												value="${topic.topicID}">
											<button class="btn-block topicStyle" type="submit">${topic.topic}</button>
										</form>
									</td>
									<td class="actionCell text-center topicInfoStyle">${topic.creator.userName}</td>
									<td class="actionCell text-center topicInfoStyle">${topic.visibility}</td>
								</tr>
							</c:if>

							<c:forEach var="userTopic" items="${userTopics}">
								<c:if test="${topic.topicID == userTopic.topic.topicID}">
									<c:if test="${userTopic.user.userID == user.userID}">
										<tr>
											<td class="topicCell text-left">
												<form method="post">
													<input type="hidden" name="action" value="openTopic">
													<input type="hidden" name="idOfTOpic"
														value="${userTopic.topic.topicID}">
													<button class="btn-block topicStyle" type="submit">${userTopic.topic.topic}</button>
												</form>
											</td>
											<td class="actionCell text-center topicInfoStyle">${userTopic.topic.creator.userName}</td>
											<td class="actionCell text-center topicInfoStyle">${userTopic.topic.visibility}</td>
										</tr>
									</c:if>
								</c:if>
							</c:forEach>

						</c:if>
					</c:forEach>


					<c:if test="${topicToUpdate != null}">
						<div class="row">
							<div
								class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
								
								<form method="post" onsubmit="return checkRequiredEditTopic()">
									<div class="row text-center">
										<label class="simpleText" for="editTopic">Edit Topic:
										</label>
									</div>
									<div class="row text-center">
										<input class="inputForNewStuff" id="editTopic" type="text"
											name="editTopic" placeholder="topic name" value="${topicUpdating.topic}" autofocus>
									</div>
									<div class="row text-center"><p id="topicNameRequired" class="requiredField ">Topic name is required</p></div>
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
											<input type="radio" name="visibility1" value="private"
												checked>
											<label class="simpleText" for="private">Private</label>
										</c:if>
									</div>
									<div class="row text-center"><p id="topicVisibilityRequired" class="requiredField ">Topic visibility is required</p></div>
									<!-- 						<input type="radio" name="visibility" value="public"  -->
									<%-- 							<c:if test="${topicUpdating.visibility == 'public'}"> checked="checked" </c:if> --%>
									<!-- 							/> Public  -->
									<!-- 						<input type="radio" name="visibility" value="private" -->
									<%-- 							<c:if test="${topicUpdating.visibility == 'private'}"> checked="checked" </c:if> --%>
									<!-- 							/> Private  -->
									<div class="row text-center">
										<c:if test="${existingTopic != null}">
									<p class=" text-center simpleText">Topic with this name already exist!</p>
								</c:if>
									</div>
									<div class="row text-center">
										<input type="hidden" name="original"
											value="${topicUpdating.topic}"> <input type="hidden"
											name="action" value="updateTheTopic">
										<button class="buttonStyle" type="submit">Edit Topic</button>
									</div>
								</form>
							</div>
						</div>
					</c:if>
				</table>
			</div>
		</div>
	</c:if>

	<%-- <%@ include file="Task.jsp"%> --%>
	<%-- <%@ include file="AddTask.jsp"%> --%>

</body>

<c:if test="${registerForm != null}">
	<script type="text/javascript">
	<%@include file="RegisterForm.js"%>
	</script>
</c:if>

<c:if test="${user == null  && registerForm == null}">
	<script type="text/javascript">
	<%@include file="LoginForm.js"%>
	</script>
</c:if>

<c:if test="${user.role=='admin' && topicAdding != null}">
	<script type="text/javascript">
	<%@include file="AddTopic.js"%>
	</script>
</c:if>

<c:if test="${topicUpdating != null}">
	<script type="text/javascript">
	<%@include file="UpdateTopic.js"%>
	</script>
</c:if>

<c:if test="${user.role=='admin' && changePassword == 1}">
	<script type="text/javascript">
	<%@include file="PasswordChange.js"%>
	</script>
</c:if>

<c:if test="${user.role=='user' && changePassword == 1}">
	<script type="text/javascript">
	<%@include file="PasswordChange.js"%>
	</script>
</c:if>

</html>