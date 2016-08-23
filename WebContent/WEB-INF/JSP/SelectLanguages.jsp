<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="post" class="userMenu">
	<input type="hidden" name="action" value="language">
	<select class="marginHorizontal buttonStyle" id="language" name="language" onchange="this.form.submit();">
		<c:if test="${language == 'en' || language == null}">
			<option value="en" selected>English</option>
			<option value="sk">Slovak</option>
			<option value="ru">Russian</option>
		</c:if>
		<c:if test="${language == 'sk'}">
			<option value="en">English</option>
			<option value="sk" selected>Slovak</option>
			<option value="ru">Russian</option>
		</c:if>
		<c:if test="${language == 'ru'}">
			<option value="en">English</option>
			<option value="sk">Slovak</option>
			<option value="ru" selected>Russian</option>
		</c:if>
	</select>
</form>