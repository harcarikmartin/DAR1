<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<div class="row col-lg-12">
	<div
		class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground ">
		<form method="post" onsubmit="return checkRequiredPassChange()">
			<table
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-2 col-sm-8 col-xs-offset-1 col-xs-10">
				<tr>
					<td class="passwordCellStyle"><label class="simpleText"
						for="OldPassword">Old password:</label></td>
					<td class="passwordCellStyle"><input id="OldPassword"
						class="passwordForm" type="password" name="userPasswordOld"
						pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
						oninvalid="alert('Old password is not valid')"
						placeholder="password" autofocus maxlength="100"></td>
					<td class="passwordCellStyle text-center"><p id="opRequired"
							class="requiredField">Required field</p></td>
				</tr>
				<tr>
					<td class="passwordCellStyle"><label class="simpleText"
						for="NewPassword">New password:</label></td>
					<td class="passwordCellStyle"><input id="NewPassword"
						class="passwordForm" type="password" name="userPasswordNew"
						pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$"
						oninvalid="alert('New password is not valid')"
						placeholder="password" maxlength="100"></td>
					<td class="passwordCellStyle text-center"><p id="npRequired"
							class="requiredField">Required field</p></td>
				</tr>
				<tr>
					<td class="passwordCellStyle"></td>
					<td class="passwordCellStyle"><p class="topicInfoStyle">*
							Password must contain minimum 8 characters, at least 1 Uppercase
							letter, 1 Lowercase letter, 1 Number and 1 Special Character
							[!@#$%^&*_=+-]</p></td>
					<td></td>
				</tr>
				<tr>
					<td class="passwordCellStyle"><label class="simpleText"
						for="NewPasswordConfirm">Confirm new password:</label></td>
					<td class="passwordCellStyle"><input id="NewPasswordConfirm"
						class="passwordForm" type="password" name="userPasswordNewCheck"
						placeholder="password" maxlength="100"></td>
					<td class="passwordCellStyle text-center"><p id="npcRequired"
							class="requiredField">Required field</p></td>
				</tr>
				<tr>

					<td colspan="3" class="text-center"><c:if
							test="${passChanged == 1 }">
							<p class="simpleText">Password changed successfully</p>
						</c:if> <c:if test="${passChanged == 0 }">
							<p class="simpleText">Password not changed!</p>
						</c:if></td>
				</tr>
				<tr>

					<td colspan="3" class="passwordCellStyle text-center"><input
						type="hidden" name="action" value="changeMyPassword">
						<button class="buttonStyle" type="submit">Change</button></td>
				</tr>
				<tr>
					<td colspan="3" class="registerCellStyle text-center"><c:if
							test="${error == '7'}">
							<p class="warning">Old password incorrect!</p>
						</c:if>
						<c:if test="${error == '8'}">
							<p class="warning">Passwords must match!</p>
						</c:if> <c:if test="${error == '2'}">
							<p class="warning">Password must be at least 8 digits long!</p>
						</c:if></td>
				</tr>

			</table>
		</form>
	</div>
</div>