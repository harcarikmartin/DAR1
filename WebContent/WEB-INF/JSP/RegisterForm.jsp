<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<c:if test="${user == null && registerForm != null}">
	<div class="row">
	<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 rowBackground">
		<div class="text-left col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 rowBackground">
			<form method="post" onsubmit="return checkRequiredReg()">
				<input type="hidden" name="role" value="user"> <input
					type="hidden" name="status" value="pending">
				<table>
					<tr>
						<th colspan="3" class="registerHeadStyle text-center">Registration form</th>
					</tr>
					<tr>
						<td class="registerCellStyle text-center"><label
							for="userNameReg">Username:</label></td>
						<td class="registerCellStyle text-left"><input
							id="userNameReg" class="registrationForm" type="text"
							name="userName" placeholder="name" autofocus maxlength="100"></td>
						<td class="registerCellStyle text-center"><p id="rfName"
								class="requiredField">Required field</p></td>
					</tr>
					<tr>
						<td class="registerCellStyle text-center"><label
							for="userPassReg">Password:</label></td>
						<td class="registerCellStyle text-left"><input
							id="userPassReg" class="registrationForm" type="password"
							name="userPassword"
							pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
							oninvalid="alert('Password is not valid')" placeholder="password" maxlength="100"></td>
						<td class="registerCellStyle text-center"><p id="rfPass"
								class="requiredField">Required field</p></td>
					</tr>
					<tr>
						<td class="registerCellStyle text-center"></td>
						<td class="registerCellStyle text-left">
							<p class="registerInfoStyle">* Password must contain minimum 8
								characters, at least 1 Uppercase letter, 1 Lowercase letter, 1
								Number and 1 Special Character [!@#$%^&*_=+-]</p>
						</td>
						<td class="registerCellStyle text-center"></td>
					</tr>
					<tr>
						<td class="registerCellStyle text-center"><label
							for="userPassRegCheck">Confirm password:</label></td>
						<td class="registerCellStyle text-left"><input
							id="userPassRegCheck" class="registrationForm" type="password"
							name="userPasswordCheck" placeholder="password" maxlength="100"></td>
						<td class="registerCellStyle text-center"><p id="rfPassCheck"
								class="requiredField">Required field</p></td>
					</tr>
					<tr>
						<td class="registerCellStyle text-center"><label
							for="birthdate">Date of birth:</label></td>
						<td class="registerCellStyle text-left"><input id="birthdate"
							class="registrationForm" type="date" name="birthdate" onchange="checkDate()" maxlength="10"></td>
						<td class="registerCellStyle text-center"><p id="rfBirthdate"
								class="requiredField">Required field</p></td>
					</tr>
					<tr>
						<td class="registerCellStyle text-center"></td>
						<td class="registerCellStyle text-left">
							<p class="registerInfoStyle">* yyyy-mm-dd</p>
						</td>
						<td class="registerCellStyle text-center"></td>
					</tr>
					<tr>
						
						<td colspan="3" class="registerCellStyle text-center"><c:if
								test="${error == '1'}"> 
								<p class="warning">Passwords must match!</p>
							</c:if> <c:if test="${error == '2'}"> 
								<p class="warning">Password must be at least 8 digits long!</p>
							</c:if> <c:if test="${error == '3'}"> 
								<p class="warning">Username already exists!</p>
							</c:if> <c:if test="${error == '4'}"> 
								<p class="warning">Not registered yet!</p>
							</c:if> <c:if test="${error == '9'}"> 
								<p class="warning">Date of birth is not in proper format!</p>
							</c:if></td>
						
					</tr>
					<tr>
						<td class="registerCellStyle text-center"></td>
						<td class="registerCellStyle text-left"><input type="hidden"
							name="action" value="registration">
							<button class="btn-block topicStyle" type="submit">Register</button>
						</td>
						<td class="registerCellStyle text-center"></td>
					</tr>
				</table>
			</form>
		</div>
		</div>
	</div>
</c:if>