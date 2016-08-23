<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<div class="row col-lg-12">
	<div class="col-lg-offset-1 col-lg-10 text-center rowBackground">
		<form method="post">
			<input type="hidden" name="action" value="mainPage">
			<button class=" fullWidth mainPage" type="submit">
				<fmt:message key="header.label.forum" />
			</button>
		</form>
	</div>
</div>

<div class="row col-lg-12">
	<c:if test="${user == null  && registerForm == null}">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left paddingHorizontal text-center-xs col-lg-8  col-md-8 col-sm-8 col-xs-12">
					<jsp:include page="ColorSelect.jsp" />
				</div>
				<div class="text-right text-center-xs col-lg-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<jsp:include page="SelectLanguages.jsp" />
				</div>
			</div>
		</div>
		<div id="login" class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left col-lg-9 col-md-9 col-sm-8 col-xs-12">
					<form method="post" onsubmit="return checkRequiredLog()" action="">
						<table class="loginTable">
							<tr>
								<td class="text-right paddingHorizontal">
									<p id="userNameRequired" class="requiredField">
										<fmt:message key="header.label.userNameReq" />
									</p>
								</td>
							</tr>
							<tr>
								<td>
									<label class="labels" for="userName">
										<fmt:message key="header.label.userName" />
										<input id="userName" class="loginForm" type="text" name="userName" placeholder="<fmt:message key="header.label.passPlaceholder" />"
											required="autofocus" maxlength="100">
									</label>
								</td>
							</tr>
						</table>

						<table class="loginTable">
							<tr>
								<td class="text-right paddingHorizontal">
									<p id="userPasswordRequired" class="requiredField ">
										<fmt:message key="header.label.passReq" />
									</p>
								</td>
							</tr>
							<tr>
								<td>
									<label class="labels" for="userPassword">
										<fmt:message key="header.label.pass" />
										<input id="userPassword" class="loginForm" type="password" name="userPassword"
											placeholder="<fmt:message key="header.label.userNamePlaceholder" />" maxlength="100">
									</label>
								</td>
							</tr>
						</table>

						<table class="loginTable">
							<tr>
								<td>
									<input type="hidden" name="action" value="login">
									<button class="marginHorizontal buttonStyle" type="submit">
										<fmt:message key="header.label.login" />
									</button>
								</td>
							</tr>
						</table>
						<c:if test="${error == '5'}">
							<p class="simpleText marginHorizontal">
								<fmt:message key="header.label.wrongLogin" />
							</p>
						</c:if>
						<c:if test="${error == '6'}">
							<p class="simpleText marginHorizontal">
								<fmt:message key="header.label.notApproved" />
							</p>
						</c:if>
						<c:if test="${succesRegister == '1'}">
							<p class="simpleText marginHorizontal">
								<fmt:message key="header.label.regSucces" />

							</p>
						</c:if>
					</form>
				</div>
				<div class="text-right  col-lg-3 col-md-3 col-sm-4 col-xs-12">
					<form method="post" class="userMenu">
						<input type="hidden" name="action" value="register">
						<button class="marginHorizontal  buttonStyle" type="submit">
							<fmt:message key="header.label.registration" />
						</button>
					</form>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${user != null  && registerForm == null}">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left paddingHorizontal text-center-xs col-lg-8  col-md-8 col-sm-8 col-xs-12">
					<jsp:include page="ColorSelect.jsp" />
				</div>
				<div class="text-right text-center-xs col-lg-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<jsp:include page="SelectLanguages.jsp" />
				</div>
			</div>
		</div>
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="row">
				<div class="text-left text-center-xs col-lg-8  col-md-8 col-sm-8 col-xs-12">
					<h4 class="simpleText">
						<fmt:message key="header.label.logged" />${user.userName}</h4>
				</div>
				<div class="text-right text-center-xs col-lg-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<form method="post" class="userMenu">
						<input type="hidden" name="action" value="logout">
						<button class="marginHorizontal buttonStyle" type="submit">
							<fmt:message key="header.label.logout" />
						</button>
					</form>
				</div>
			</div>
		</div>
	</c:if>
</div>