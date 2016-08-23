<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<div class="row col-lg-12">
	<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
		<table class="col-lg-offset-2 col-lg-8 col-md-offset-2 col-md-8 col-sm-offset-2 col-sm-8 col-xs-12">
			<tr>
				<td rowspan="6" class="userProfileInfo text-right paddingHorizontal">
					<c:if test="${sessionScope.user.profileImage == null}">
						<img src="images/default.jpg" height="200" alt="<fmt:message key="all.label.defImg" />">
					</c:if>
					<c:if test="${sessionScope.user.profileImage != null}">
						<img src="images/${sessionScope.user.userID}.jpg" height="200" alt="<fmt:message key="all.label.userImg" />">
					</c:if>
				</td>
				<td class="userProfileInfo text-right paddingHorizontal">
					<fmt:message key="profile.label.username" />
				</td>
				<td class="userProfileInfo paddingHorizontal">${user.userName}</td>
			</tr>
			<tr>
				<td class="userProfileInfo text-right paddingHorizontal">
					<fmt:message key="profile.label.birthdate" />
				</td>
				<td class="userProfileInfo paddingHorizontal">${user.birthDate}</td>
			</tr>
			<tr>
				<td class="userProfileInfo text-right paddingHorizontal">
					<fmt:message key="profile.label.role" />
				</td>
				<td class="userProfileInfo paddingHorizontal">${user.role}</td>
			</tr>
			<tr>
				<td class="userProfileInfo text-right paddingHorizontal">
					<fmt:message key="profile.label.status" />
				</td>
				<td class="userProfileInfo paddingHorizontal">${user.status}</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<p class="userProfileInfo"></p>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<form method="post" enctype="multipart/form-data">
						<label for="file-upload" class="custom-file-upload">
							<fmt:message key="profile.label.profileImg" />
						</label>
						<input id="file-upload" name="fileToUpload" type="file" accept="image/jpeg" />
						<input type="hidden" name="action" value="UploadImage">
						<br>
						<button class=" buttonStyle" type="submit">
							<fmt:message key="profile.label.sendImg" />
						</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
</div>