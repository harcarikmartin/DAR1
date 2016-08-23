<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<div class="row col-lg-12">
	<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground ">
		<form method="post" onsubmit="return checkRequiredPassChange()">
			<table class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-2 col-sm-8 col-xs-offset-1 col-xs-10">
				<tr>
					<td class="passwordCellStyle">
						<label class="simpleText" for="OldPassword">
							<fmt:message key="pass.label.old" />
						</label>
					</td>
					<td class="passwordCellStyle">
						<input id="OldPassword" class="passwordForm" type="password" name="userPasswordOld"
							pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" oninvalid="alert('Old password is not valid')"
							placeholder="<fmt:message key="pass.label.pass" />" required="autofocus" maxlength="100">
					</td>
					<td class="passwordCellStyle text-center">
						<p id="opRequired" class="requiredField">
							<fmt:message key="all.label.requiredField" />
						</p>
					</td>
				</tr>
				<tr>
					<td class="passwordCellStyle">
						<label class="simpleText" for="NewPassword">
							<fmt:message key="pass.label.new" />
						</label>
					</td>
					<td class="passwordCellStyle">
						<input id="NewPassword" class="passwordForm" type="password" name="userPasswordNew"
							pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" oninvalid="alert('New password is not valid')"
							placeholder="<fmt:message key="pass.label.pass" />" maxlength="100">
					</td>
					<td class="passwordCellStyle text-center">
						<p id="npRequired" class="requiredField">
							<fmt:message key="all.label.requiredField" />
						</p>
					</td>
				</tr>
				<tr>
					<td class="passwordCellStyle"></td>
					<td class="passwordCellStyle">
						<p class="topicInfoStyle">
							<fmt:message key="pass.label.passRegex" />
						</p>
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="passwordCellStyle">
						<label class="simpleText" for="NewPasswordConfirm">
							<fmt:message key="pass.label.newConfirm" />
						</label>
					</td>
					<td class="passwordCellStyle">
						<input id="NewPasswordConfirm" class="passwordForm" type="password" name="userPasswordNewCheck"
							placeholder="<fmt:message key="pass.label.pass" />" maxlength="100">
					</td>
					<td class="passwordCellStyle text-center">
						<p id="npcRequired" class="requiredField">
							<fmt:message key="all.label.requiredField" />
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-center">
						<c:if test="${passChanged == 1 }">
							<p class="simpleText">
								<fmt:message key="pass.label.succes" />
							</p>
						</c:if>
						<c:if test="${passChanged == 0 }">
							<p class="simpleText">
								<fmt:message key="pass.label.notChanged" />
							</p>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="passwordCellStyle text-center">
						<input type="hidden" name="action" value="changeMyPassword">
						<button class="buttonStyle" type="submit">
							<fmt:message key="pass.label.change" />
						</button>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="registerCellStyle text-center">
						<c:if test="${error == '7'}">
							<p class="warning">
								<fmt:message key="pass.label.oldWrong" />
							</p>
						</c:if>
						<c:if test="${error == '8'}">
							<p class="warning">
								<fmt:message key="pass.label.match" />
							</p>
						</c:if>
						<c:if test="${error == '2'}">
							<p class="warning">
								<fmt:message key="pass.label.length" />
							</p>
						</c:if>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>