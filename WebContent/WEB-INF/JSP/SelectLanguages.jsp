<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<form method="post" class="userMenu">
	<input type="hidden" name="action" value="language">
	<select class="marginHorizontal buttonStyle" id="language" name="language" onchange="this.form.submit();">
		<c:if test="${language == 'en' || language == null}">
			<option value="en" selected>EN</option>
			<option value="sk">SK</option>
		</c:if>
		<c:if test="${language == 'sk'}">
			<option value="en">EN</option>
			<option value="sk" selected>SK</option>
		</c:if>
	</select>
</form>