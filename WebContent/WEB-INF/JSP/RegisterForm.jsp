<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <c:if test="${user == null && registerForm != null}">
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
							
<!-- 							<button type="button" onClick="window.location.reload()">Back</button> -->
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