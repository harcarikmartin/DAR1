<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />


<c:if test="${user == null && registerForm != null}">
	<div class="row">
		<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 rowBackground">
			<div class="text-left col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-offset-2 col-sm-8 rowBackground">
				<form method="post" onsubmit="return checkRequiredReg()">
					<input type="hidden" name="role" value="user">
					<input type="hidden" name="status" value="pending">
					<table>
						<tr>
							<th colspan="3" class="registerHeadStyle text-center">
								<fmt:message key="register.label.head" />
							</th>
						</tr>
						<tr>
							<td class="registerCellStyle text-center">
								<label for="username">
									<fmt:message key="register.label.username" />
								</label>
							</td>
							<td class="registerCellStyle text-left">
								<input id="userNameReg" class="registrationForm" type="text" name="userName"
									placeholder="<fmt:message key="register.label.namePlaceholder" />" required="autofocus" maxlength="100">
							</td>
							<td class="registerCellStyle text-center">
								<p id="rfName" class="requiredField">
									<fmt:message key="all.label.requiredField" />
								</p>
							</td>
						</tr>
						<tr>
							<td class="registerCellStyle text-center">
								<label for="userPassReg">
									<fmt:message key="register.label.password" />
								</label>
							</td>
							<td class="registerCellStyle text-left">
								<input id="userPassReg" class="registrationForm" type="password" name="userPassword"
									pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" oninvalid="alert('Password is not valid')"
									placeholder="<fmt:message key="register.label.passPlaceholder" />" maxlength="100">
							</td>
							<td class="registerCellStyle text-center">
								<p id="rfPass" class="requiredField">
									<fmt:message key="all.label.requiredField" />
								</p>
							</td>
						</tr>
						<tr>
							<td class="registerCellStyle text-center"></td>
							<td class="registerCellStyle text-left">
								<p class="registerInfoStyle">
									<fmt:message key="register.label.passwordRegex" />
								</p>
							</td>
							<td class="registerCellStyle text-center"></td>
						</tr>
						<tr>
							<td class="registerCellStyle text-center">
								<label for="userPassRegCheck">
									<fmt:message key="register.label.confirmPassword" />
								</label>
							</td>
							<td class="registerCellStyle text-left">
								<input id="userPassRegCheck" class="registrationForm" type="password" name="userPasswordCheck"
									placeholder="<fmt:message key="register.label.passPlaceholder" />" maxlength="100">
							</td>
							<td class="registerCellStyle text-center">
								<p id="rfPassCheck" class="requiredField">
									<fmt:message key="all.label.requiredField" />
								</p>
							</td>
						</tr>
						<tr>
							<td class="registerCellStyle text-center">
								<label for="birthdate">
									<fmt:message key="register.label.dateOfBirth" />
								</label>
							</td>
							<td class="registerCellStyle text-left">
								<input id="birthdate" class="registrationForm" type="date" name="birthdate" onchange="checkDate()" maxlength="10">
							</td>
							<td class="registerCellStyle text-center">
								<p id="rfBirthdate" class="requiredField">
									<fmt:message key="all.label.requiredField" />
								</p>
							</td>
						</tr>
						<tr>
							<td class="registerCellStyle text-center"></td>
							<td class="registerCellStyle text-left">
								<p class="registerInfoStyle">
									<fmt:message key="register.label.dateFormat" />
								</p>
							</td>
							<td class="registerCellStyle text-center"></td>
						</tr>
						<tr>
							<td colspan="3" class="registerCellStyle text-center">
								<c:if test="${error == '1'}">
									<p class="warning">
										<fmt:message key="register.label.passMatch" />
									</p>
								</c:if>
								<c:if test="${error == '2'}">
									<p class="warning">
										<fmt:message key="register.label.passLegth" />
									</p>
								</c:if>
								<c:if test="${error == '3'}">
									<p class="warning">
										<fmt:message key="register.label.usernameExists" />
									</p>
								</c:if>
								<c:if test="${error == '9'}">
									<p class="warning">
										<fmt:message key="register.label.dateOfBirthValid" />
									</p>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="registerCellStyle text-center"></td>
							<td class="registerCellStyle text-left">
								<input type="hidden" name="action" value="registration">
								<button class="btn-block topicStyle" type="submit">
									<fmt:message key="register.label.register" />
								</button>
							</td>
							<td class="registerCellStyle text-center"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</c:if>