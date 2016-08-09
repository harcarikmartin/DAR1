<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Registration form</title>
<style>
<%--    <%@ include file="normalize.css"%> --%>
   
   <%@ include file="bootstrap/css/bootstrap.css"%>
   <%@ include file="ForumForBootstrap.css"%>
</style>
</head>

<body>
	<div class="row">
		<h3 class="text-center">
			<a href="/Forum/Forum">Forum</a>
		</h3>
		<div class="row text-center">
			<form method="post">
				<input type="hidden" name="action" value="generate">
				<button type="submit">Generate</button>
			</form>
		</div>
	</div>


	<div class="row">
		<c:if test="${user == null}">
			<div id="login" class="col-lg-offset-3">
				<div class="col-lg-10">
					<c:if test="${error == '5'}">
						<p class="warning">Wrong login details!</p>
					</c:if>
				</div>

				<div class="row">
					<form method="post">
						<div class="col-lg-5 ">
							<label for="userName">Name:</label> <input id="userName"
								class="userName" type="text" name="userName"
								placeholder="name ..."> <label for="userPassword">Password:</label>
							<input id="userPassword" class="userPassword" type="text"
								name="userPassword" placeholder="password ..."> <input
								type="hidden" name="action" value="login">
							<button type="submit">Login</button>
						</div>
						<div class="col-lg-5 text-center">
							<button name="action" value="registrationShow" type="button" onclick="registerFcn()">Registration</button>
						</div>

					</form>
				</div>
			</div>
		</c:if>

		<c:if test="${user != null}">
			<div class="content">
				<div class="col-sm-6">
					<p>Prihlásený ako ${user.userName}</p>
					<p>heslo je ${user.userPassword }</p>
				</div>
				<div class="col-sm-4">
					<form method="post">
						<input type="hidden" name="action" value="logout">
						<button type="submit">Logout</button>
					</form>
				</div>
			</div>
		</c:if>
	</div>




	<c:if test="${user == null}">
		<div id="register" class="content row">
			
			
			<div class="row col-sm-offset-2">
				<form method="post" onsubmit="return checkRequiredReg()">
					<input type="hidden" name="role" value="user"> <input
						type="hidden" name="status" value="pending">

					<div class="row">
						<div class="col-sm-4 text-right">
							<label for="userNameReg">Name:</label>
						</div>
						<div class="col-sm-4">
							<input id="userNameReg" class="registrationForm" type="text"
								name="userName" placeholder="name ...">
							<p id="rfName" class="requiredField">Required field</p>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 text-right">
							<label for="userPassReg">Password:</label>
						</div>
						<div class="col-sm-4">
							<input id="userPassReg" class="registrationForm" type="password"
								name="userPassword"
								pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
								oninvalid="alert('Password is not valid')"
								placeholder="password">
							<p id="rfPass" class="requiredField">Required field</p>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 col-sm-offset-4">
							<p>* Password must contain minimum 8 characters, at least 1
								Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special
								Character [!@#$%^&*_=+-]</p>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 text-right">
							<label for="userPassRegCheck">Password for check:</label>
						</div>
						<div class="col-sm-4">
							<input id="userPassRegCheck" class="registrationForm"
								type="password" name="userPasswordCheck"
								placeholder="password again ...">
							<p id="rfPassCheck" class="requiredField">Required field</p>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 text-right">
							<label for="birthdate">Birthdate:</label>
						</div>
						<div class="col-sm-4">
							<input id="birthdate" class="registrationForm" type="date"
								name="birthdate">
							<p id="rfBirthdate" class="requiredField">Required field</p>
						</div>
					</div>

					<div class="row">

						<div class="col-sm-4 col-sm-offset-4">
							<input type="hidden" name="action" value="registration">
							<button type="submit">Registration</button>
							<br>
							<button type="button" onClick="window.location.reload()">Back</button>
						</div>
					</div>
				</form>
			</div>
			
			
		</div>
		<div class="row">
				<c:if test="${error == '1'}">
					<p class="warning">Passwords must match!</p>
				</c:if>
				<c:if test="${error == '2'}">
					<p class="warning">Password must be at least 8 digits long!</p>
				</c:if>
				<c:if test="${error == '3'}">
					<p class="warning">Username already exists!</p>
				</c:if>
				<c:if test="${error == '4'}">
					<p class="warning">Not registered yet!</p>
				</c:if>
			</div>
	</c:if>

	<jsp:include page="AdminMenu.jsp" />

	<jsp:include page="UserMenu.jsp" />

	<c:if test="${user.role == 'admin'}">
		<form method="post">
			<label for="addTopic">Add new Topic: </label> <input id="addTopic"
				type="text" name="addTopic" placeholder="topic name"> <input
				type="radio" name="visibility" value="public"> Public <input
				type="radio" name="visibility" value="private" checked>
			Private <input type="hidden" name="action" value="addTopic">
			<button type="submit">Add Topic</button>
		</form>
	</c:if>

	<div>
		<table>
		<tr>
			<th>Name of topic</th>
			<th>Create by</th>
			<th>Visibility</th>
			<c:if test="${user.role == 'admin'}">
			<th>Action</th>
			</c:if>
		</tr>
			<c:forEach items="${topics}" var="topic">
				<c:if test="${user.role == null}">
					<c:if test="${topic.visibility == 'public'}">
						<tr>
							<td>${topic.topic}</td>
							<td>${topic.creator.userName}</td>
							<td>${topic.visibility}</td>
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
							<td>${topic.topic}</td>
							<td>${topic.creator.userName}</td>
							<td>${topic.visibility}</td>

							<c:if test="${user.role == 'admin'}">
								<td>
									<form method="post" class="topicToUpdate">
										<input type="hidden" name="topicToUpdate"
											value="${topic.topic}"> <input type="hidden"
											name="action" value="updateTopic">
										<button type="submit">Update</button>
									</form>
									</td>
									<td>
									<form method="post" class="topicToRemove">
										<input type="hidden" name="topicToRemove"
											value="${topic.topic}"> <input type="hidden"
											name="action" value="removeTopic">
										<button type="submit">Remove</button>
									</form>
								</td>
							</c:if>
						</tr>
					</c:if>
				</c:if>
				
				
				<c:if test="${user.role == 'user'}">
					<c:if test="${topic.visibility == 'public'}">
						<tr>
							<td>${topic.topic}</td>
							<td>${topic.creator.userName}</td>
							<td>${topic.visibility}</td>
						</tr>
					</c:if>
					
					<c:forEach var="userTopic" items="${userTopics}">
						<c:if test="${topic.topicID == userTopic.topic.topicID}">
							<c:if test="${userTopic.user.userID == user.userID}">
							<tr>
							<td>${userTopic.topic.topic}</td>
							<td>${userTopic.topic.creator.userName}</td>
							<td>${userTopic.topic.visibility}</td>
						</tr>
							</c:if>
						</c:if>
					</c:forEach>
					
				</c:if>
			</c:forEach>
			
			
				<c:if test="${topicUpdating != null}">
					<form method="post">
						<label for="editTopic">Edit Topic: </label> 
						<input id="editTopic" type="text" name="editTopic" value="${topicUpdating.topic}">
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
						<input type="hidden" name="original" value="${topicUpdating.topic}">
						<input type="hidden" name="action" value="updateTheTopic">
						<button type="submit">Edit Topic</button>
					</form>
				</c:if>
				
			
		</table>
	</div>

<%-- <%@ include file="Task.jsp"%> --%>
<%-- <%@ include file="AddTask.jsp"%> --%>

</body>

<script type="text/javascript">
	document.getElementById("login").style.display = "inline";
	document.getElementById("register").style.display = "none";
	function registerFcn() {
		document.getElementById("login").style.display = "none";
		document.getElementById("register").style.display = "inline";
	}
	rfName.style.display = "none";
	rfPass.style.display = "none";
	rfPassCheck.style.display = "none";
	rfBirthdate.style.display = "none";
	function clearFcn() {
		if (userNameReg.value != "") {
			rfName.style.display = "none";
			userNameReg.style.border = "solid 1px #D3D3D3"
		}
		if (userPassReg.value != "") {
			rfPass.style.display = "none";
			userPassReg.style.border = "solid 1px #D3D3D3"
		}
		if (userPassRegCheck.value != "") {
			rfPassCheck.style.display = "none";
			userPassRegCheck.style.border = "solid 1px #D3D3D3"
		}
		if (birthdate.value != "") {
			rfBirthdate.style.display = "none";
			birthdate.style.border = "solid 1px #D3D3D3"
		}
	}
	var checkRequiredReg = function() {
		var fail = false;
		var userNameReg = document.getElementById('userNameReg');
		var userPassReg = document.getElementById('userPassReg');
		var userPassRegCheck = document.getElementById('userPassRegCheck');
		var birthdate = document.getElementById('birthdate');
		var rfName = document.getElementById('rfName');
		var rfPass = document.getElementById('rfPass');
		var rfPassCheck = document.getElementById('rfPassCheck');
		var rfBirthdate = document.getElementById('rfBirthdate');
		if (userNameReg.value == "") {
			rfName.style.display = "inline";
			userNameReg.style.border = "solid 1px red"
			fail = true;
		}
		if (userPassReg.value == "") {
			rfPass.style.display = "inline";
			userPassReg.style.border = "solid 1px red"
			fail = true;
		}
		if (userPassRegCheck.value == "") {
			rfPassCheck.style.display = "inline";
			userPassRegCheck.style.border = "solid 1px red"
			fail = true;
		}
		if (birthdate.value == "") {
			rfBirthdate.style.display = "inline";
			birthdate.style.border = "solid 1px red"
			fail = true;
		}
		if (fail == true) {
			alert("Required field was not set!");
		}
		return !fail;
	}
	setInterval('clearFcn()', 100);
<%=request.getAttribute("regWrong")%>
	
</script>
</html>