<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Registration form</title>
<style>
   <%@ include file="normalize.css"%>
   <%@ include file="Forum.css"%>
</style>
</head>
<body>
	<h3>
		<a href="/Forum/Forum">Forum</a>
	</h3>
	<div class="centerAlign">
		<form method="post">
			<input type="hidden" name="action" value="generate">
			<button type="submit">Generate</button><br><br>
		</form>
	</div>

	<c:if test="${user == null}">
		<div id="login" class="content">
			<c:if test="${error == '5'}">
				<p class="warning">Wrong login details!</p>
			</c:if>
			<form method="post">
				<div class="left">
					<label for="userName">Name:</label> <input id="userName"
						class="userName" type="text" name="userName"
						placeholder="name ..."> <label for="userPassword">Password:</label>
					<input id="userPassword" class="userPassword" type="text"
						name="userPassword" placeholder="password ..."> <input
						type="hidden" name="action" value="login">
					<button type="submit">Login</button>
				</div>
				<div class="right">
					<button name="action" value="registrationShow" type="button"
						onclick="registerFcn()">Registration</button>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</c:if>

	<c:if test="${user != null}">
		<div class="content">
			<div class="left">
				<p>Prihlásený ako ${user.userName}</p>
				<p>heslo je ${user.userPassword }</p>
			</div>
			<div class="right">
				<form method="post">
					<input type="hidden" name="action" value="logout">
					<button type="submit">Logout</button>
				</form>
			</div>
		</div>
	</c:if>

	<c:if test="${user == null}">
		<div id="register" class="content">
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

			<div>
				<form method="post" onsubmit="return checkRequiredReg()">
					<input type="hidden" name="role" value="user"> <input
						type="hidden" name="status" value="pending">

					<div class="leftRegistration">
						<label for="userNameReg">Name:</label>
					</div>
					<div class="rightRegistration">
						<input id="userNameReg" class="registrationForm" type="text"
							name="userName" placeholder="name ...">
						<p id="rfName" class="requiredField">Required field</p>
					</div>
					<div class="clear"></div>
					<div class="leftRegistration">
						<label for="userPassReg">Password:</label>
					</div>
					<div class="rightRegistration">
						<input id="userPassReg" class="registrationForm" type="password"
							name="userPassword"
							pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
							oninvalid="alert('Password is not valid')" placeholder="password">
						<p id="rfPass" class="requiredField">Required field</p>
					</div>
					<div class="clear"></div>
					<div class="leftRegistration"></div>
					<div class="rightRegistration">
						<p>* Password must contain minimum 8 characters, at least 1
							Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special
							Character [!@#$%^&*_=+-]</p>
					</div>
					<div class="clear"></div>
					<div class="leftRegistration">
						<label for="userPassRegCheck">Password for check:</label>
					</div>
					<div class="rightRegistration">
						<input id="userPassRegCheck" class="registrationForm"
							type="password" name="userPasswordCheck"
							placeholder="password again ...">
						<p id="rfPassCheck" class="requiredField">Required field</p>
					</div>
					<div class="clear"></div>
					<div class="leftRegistration">
						<label for="birthdate">Birthdate:</label>
					</div>
					<div class="rightRegistration">
						<input id="birthdate" class="registrationForm" type="date"
							name="birthdate">
						<p id="rfBirthdate" class="requiredField">Required field</p>
					</div>
					<div class="clear"></div>
					<div class="leftRegistration"></div>
					<div class="rightRegistration">
						<input type="hidden" name="action" value="registration">
						<button type="submit">Registration</button>
						<br>
						<button type="button" onClick="window.location.reload()">Back</button>
					</div>
					<div class="clear"></div>
				</form>
			</div>
		</div>
	</c:if>

	<jsp:include page="AdminMenu.jsp" />

	<jsp:include page="UserMenu.jsp" />
	
	<c:if test="${user.role == 'admin'}">
		<form method="post">
			<label for="addTopic">Add new Topic: </label>
			<input id="addTopic" type="text" name="addTopic" placeholder="topic name">
			<input type="hidden" name="action" value="addTopic" >
			<button type="submit">Add Topic</button>
		</form>
	</c:if>

	<div>
		<table>
			<c:forEach items="${topics}" var="topic">
				<c:if test="${user.role == null}">
					<c:if test="${topic.visibility == 'public'}">
					<tr>
						<td>Name of topic: ${topic.topic}</td>
						<td>Create by: ${topic.creator.userName}</td>
						<td>Visibility: ${topic.visibility}</td>
						</tr>
					</c:if>
				</c:if>
				
				<c:if test="${user.role != null}">
					<tr>
					<td>Name of topic: ${topic.topic}</td>
					<td>Create by: ${topic.creator.userName}</td>
					<td>Visibility: ${topic.visibility}</td>
					 
					<td>
						<c:if test="${user.role == 'admin'}">
						<form method="post" class="updateTopicButton">
						<input type="hidden" name="updateTopic&idTopic" value="${topic.topicID}">
						<button type="submit">Update</button>
						</form>
						<form method="post" class="removeTopicButton">
						<input type="hidden" name="removeTopic&idTopic" value="${topic.topicID}">
						<button type="submit">Remove</button>
						</form>
						</c:if>
					</td>
					
				</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>

	
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