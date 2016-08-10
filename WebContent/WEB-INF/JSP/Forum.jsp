<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Registration form</title>
<style>
<%@include file="bootstrap/css/bootstrap.css"%>
<%@include file="ForumForBootstrap.css"%>
</style>
</head>

<body>
	<div class="row col-lg-12 header">
		<div class="row">
			<a class="col-lg-10 col-lg-offset-1" href="/Forum/Forum"> <img
				src="images\forumLink.jpg" alt="Forum"></a>
		</div>
		<div class="row text-center">
			<form method="post">
				<input type="hidden" name="action" value="generate">
				<button type="submit">Generate</button>
			</form>
		</div>
	</div>

	<div class="row col-lg-12">
		<c:if test="${user == null  && registerForm == null}">
			<div id="login" class="col-lg-offset-1 col-lg-10 rowBackground">
				<div class="row">
					<div class="text-left col-lg-8 col-md-8 col-sm-8 col-xs-12">
						<form method="post">
							<label class="labels" for="userName">Name: <input
								id="userName" class="loginForm" type="text" name="userName"
								placeholder="name ..."></label> <label class="labels"
								for="userPassword">Password: <input id="userPassword"
								class="loginForm" type="text" name="userPassword"
								placeholder="password ..."></label> <input type="hidden"
								name="action" value="login">
							<button class="paddingHorizontal buttonStyle" type="submit">Login</button>
							<c:if test="${error == '5'}">
								<p class="simpleText">Wrong login details!</p>
							</c:if>
						</form>
					</div>
					<div class="paddingHorizontal text-right col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<form method="post">
							<input type="hidden" name="action" value="register">
							<button class="buttonStyle" type="submit">Registration</button>
						</form>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${user != null  && registerForm == null}">
			<div class="col-lg-offset-1 col-lg-10 rowBackground">
				<div class="row">
					<div class="text-left col-lg-6">
						<h4 class="simpleText">Logged as: ${user.userName}</h4>
					</div>
					<div class="col-lg-6 text-center">
						<form method="post">
							<input type="hidden" name="action" value="logout">
							<button class="buttonStyle" type="submit">Logout</button>
						</form>
					</div>
				</div>
			</div>
		</c:if>
	</div>




																																			<%-- 	<c:if test="${user == null}"> --%>
																																			<!-- 		<div id="register" class="content row"> -->
																																			<!-- 			<div class="row col-sm-offset-2"> -->
																																			<!-- 				<form method="post" onsubmit="return checkRequiredReg()"> -->
																																			<!-- 					<input type="hidden" name="role" value="user"> <input -->
																																			<!-- 						type="hidden" name="status" value="pending"> -->
																																		
																																			<!-- 					<div class="row"> -->
																																			<!-- 						<div class="col-sm-4 text-right"> -->
																																			<!-- 							<label for="userNameReg">Name:</label> -->
																																			<!-- 						</div> -->
																																			<!-- 						<div class="col-sm-4"> -->
																																			<!-- 							<input id="userNameReg" class="registrationForm" type="text" -->
																																			<!-- 								name="userName" placeholder="name ..."> -->
																																			<!-- 							<p id="rfName" class="requiredField">Required field</p> -->
																																			<!-- 						</div> -->
																																			<!-- 					</div> -->
																																		
																																			<!-- 					<div class="row"> -->
																																			<!-- 						<div class="col-sm-4 text-right"> -->
																																			<!-- 							<label for="userPassReg">Password:</label> -->
																																			<!-- 						</div> -->
																																			<!-- 						<div class="col-sm-4"> -->
																																			<!-- 							<input id="userPassReg" class="registrationForm" type="password" -->
																																			<!-- 								name="userPassword" -->
																																			<!-- 								pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" -->
																																			<!-- 								oninvalid="alert('Password is not valid')" -->
																																			<!-- 								placeholder="password"> -->
																																			<!-- 							<p id="rfPass" class="requiredField">Required field</p> -->
																																			<!-- 						</div> -->
																																			<!-- 					</div> -->
																																		
																																			<!-- 					<div class="row"> -->
																																			<!-- 						<div class="col-sm-4 col-sm-offset-4"> -->
																																			<!-- 							<p>* Password must contain minimum 8 characters, at least 1 -->
																																			<!-- 								Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special -->
																																			<!-- 								Character [!@#$%^&*_=+-]</p> -->
																																			<!-- 						</div> -->
																																			<!-- 					</div> -->
																																		
																																			<!-- 					<div class="row"> -->
																																			<!-- 						<div class="col-sm-4 text-right"> -->
																																			<!-- 							<label for="userPassRegCheck">Password for check:</label> -->
																																			<!-- 						</div> -->
																																			<!-- 						<div class="col-sm-4"> -->
																																			<!-- 							<input id="userPassRegCheck" class="registrationForm" -->
																																			<!-- 								type="password" name="userPasswordCheck" -->
																																			<!-- 								placeholder="password again ..."> -->
																																			<!-- 							<p id="rfPassCheck" class="requiredField">Required field</p> -->
																																			<!-- 						</div> -->
																																			<!-- 					</div> -->
																																		
																																			<!-- 					<div class="row"> -->
																																			<!-- 						<div class="col-sm-4 text-right"> -->
																																			<!-- 							<label for="birthdate">Birthdate:</label> -->
																																			<!-- 						</div> -->
																																			<!-- 						<div class="col-sm-4"> -->
																																			<!-- 							<input id="birthdate" class="registrationForm" type="date" -->
																																			<!-- 								name="birthdate"> -->
																																			<!-- 							<p id="rfBirthdate" class="requiredField">Required field</p> -->
																																			<!-- 						</div> -->
																																			<!-- 					</div> -->
																																		
																																			<!-- 					<div class="row"> -->
																																		
																																			<!-- 						<div class="col-sm-4 col-sm-offset-4"> -->
																																			<!-- 							<input type="hidden" name="action" value="registration"> -->
																																			<!-- 							<button type="submit">Registration</button> -->
																																			<!-- 							<br> -->
																																			<!-- 							<button type="button" onClick="window.location.reload()">Back</button> -->
																																			<!-- 						</div> -->
																																			<!-- 					</div> -->
																																			<!-- 				</form> -->
																																			<!-- 			</div> -->
																																		
																																		
																																			<!-- 		</div> -->
																																			<!-- 		<div class="row"> -->
																																			<%-- 			<c:if test="${error == '1'}"> --%>
																																			<!-- 				<p class="warning">Passwords must match!</p> -->
																																			<%-- 			</c:if> --%>
																																			<%-- 			<c:if test="${error == '2'}"> --%>
																																			<!-- 				<p class="warning">Password must be at least 8 digits long!</p> -->
																																			<%-- 			</c:if> --%>
																																			<%-- 			<c:if test="${error == '3'}"> --%>
																																			<!-- 				<p class="warning">Username already exists!</p> -->
																																			<%-- 			</c:if> --%>
																																			<%-- 			<c:if test="${error == '4'}"> --%>
																																			<!-- 				<p class="warning">Not registered yet!</p> -->
																																			<%-- 			</c:if> --%>
																																			<!-- 		</div> -->
																																			<%-- 	</c:if> --%>

	<jsp:include page="RegisterForm.jsp" />

	<jsp:include page="AdminMenu.jsp" />

	<jsp:include page="UserMenu.jsp" />


	<c:if test="${user.role == 'admin'}">
	<div class="row">
			<div class="col-lg-offset-1 col-lg-10 rowBackground">
		<form method="post">
		<c:if test="${existingTopic != null}">
		<p class="simpleText">Topic with this name already exist!</p>
		</c:if>
			<label class="simpleText" for="addTopic">Add new Topic: </label> 
			<input id="addTopic" type="text" name="addTopic" placeholder="topic name"> 
			<input id="public" type="radio" name="visibility" value="public"> <label class="simpleText" for="public">Public</label>		
			<input id="private" type="radio" name="visibility" value="private" checked> <label class="simpleText" for="private">Private</label>	
			<input type="hidden" name="action" value="addTopic">
			<button class="buttonStyle" type="submit">Add Topic</button>
		</form>
		</div>
		</div>
	</c:if>



	<c:if test="${registerForm == null}">
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10">
				<table>
					<tr>
						<th class="topicCell text-center topicHeadStyle">Topic</th>
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

						<c:if test="${topicUpdating == null}">

							<c:if test="${user.role == 'admin'}">
								<!-- 						<tr> -->
								<!-- 							<th>Name of topic</th> -->
								<!-- 							<th>Create by</th> -->
								<!-- 							<th>Visibility</th> -->
								<!-- 							<th>Action</th> -->
								<!-- 						</tr> -->
								<tr>
									<td class="topicCell text-center">
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
										<td class="actionCell text-center">
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
						</c:if>


						<c:if test="${user.role == 'user'}">
							<c:if test="${topic.visibility == 'public'}">
								<tr>
									<td class="topicCell text-center">
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
											<td class="topicCell text-center">
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


					<c:if test="${topicUpdating != null}">
						<form method="post">
							<label for="editTopic">Edit Topic: </label> <input id="editTopic"
								type="text" name="editTopic" value="${topicUpdating.topic}">
							<c:if test="${topicUpdating.visibility == 'public'}">
								<input type="radio" name="visibility1" value="public" checked>Public
							<input type="radio" name="visibility1" value="private">Private
						</c:if>
							<c:if test="${topicUpdating.visibility == 'private'}">
								<input type="radio" name="visibility1" value="public">Public
							<input type="radio" name="visibility1" value="private" checked>Private
						</c:if>

							<!-- 						<input type="radio" name="visibility" value="public"  -->
							<%-- 							<c:if test="${topicUpdating.visibility == 'public'}"> checked="checked" </c:if> --%>
							<!-- 							/> Public  -->
							<!-- 						<input type="radio" name="visibility" value="private" -->
							<%-- 							<c:if test="${topicUpdating.visibility == 'private'}"> checked="checked" </c:if> --%>
							<!-- 							/> Private  -->
							<input type="hidden" name="original"
								value="${topicUpdating.topic}"> <input type="hidden"
								name="action" value="updateTheTopic">
							<button type="submit">Edit Topic</button>
						</form>
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
<%@include file="Forum.js"%>
</script>
</c:if>




	
	

</html>