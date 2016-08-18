<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>

<div class="row col-lg-12">
	<div class="col-lg-offset-1 col-lg-10 text-center">
		<form method="post">
			<input type="hidden" name="action" value="mainPage">
			<button class=" fullWidth mainPage" type="submit">FORUM</button>
		</form>
	</div>
</div>

<div class="row col-lg-12">
	<c:if test="${user == null  && registerForm == null}">
		<div id="login" class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left col-lg-10 col-md-10 col-sm-10 col-xs-12">
					<form method="post" onsubmit="return checkRequiredLog()" action="">
						<table class="loginTable">
							<tr>
								<td class="text-right paddingHorizontal">
									<p id="userNameRequired" class="requiredField">Username is required</p>
								</td>
							</tr>
							<tr>
								<td>
									<label class="labels" for="userName">
										Username:
										<input id="userName" class="loginForm" type="text" name="userName" placeholder="name ..." autofocus maxlength="100">
									</label>
								</td>
							</tr>
						</table>
						
						<table class="loginTable">
							<tr>
								<td class="text-right paddingHorizontal">
									<p id="userPasswordRequired" class="requiredField ">Password is required</p>
								</td>
							</tr>
							<tr>
								<td>
									<label class="labels" for="userPassword">
										Password:
										<input id="userPassword" class="loginForm" type="password" name="userPassword" placeholder="password ..." maxlength="100">
									</label>
								</td>
							</tr>
						</table>

						<table class="loginTable">
							<tr>
								<td>
									<input type="hidden" name="action" value="login">
									<button class="marginHorizontal buttonStyle" type="submit">Login</button>
								</td>
							</tr>
						</table>
						<c:if test="${error == '5'}">
							<p class="simpleText marginHorizontal">Wrong login details!</p>
						</c:if>
						<c:if test="${error == '6'}">
							<p class="simpleText marginHorizontal">Not yet approved by admin!</p>
						</c:if>
						<c:if test="${succesRegister == '1'}">
							<p class="simpleText marginHorizontal">
								Your registration was successful.
								<br>
								Allow some time for your account to be approved by admin.
								<br>
								You are not able to log in yet.
							</p>
						</c:if>
					</form>
				</div>
				<div class="text-right  col-lg-2 col-md-2 col-sm-2 col-xs-12">
					<form method="post"  action="">
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
					<form method="post"  action="">
						<input type="hidden" name="action" value="logout">
						<button class="marginHorizontal buttonStyle" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</div>
	</c:if>
</div>