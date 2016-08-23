<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<div class="row">
	<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
		<form method="post" onsubmit="return checkRequiredAddComment()">
			<div class="row text-center">
				<textarea class="inputForNewStuff" id="comment" name="comment" spellcheck="true" lang="en" cols="50" rows="5"
					placeholder="<fmt:message key="comment.label.commentPlaceholder"/>" maxlength="500"></textarea>
			</div>
			<div class="row text-center">
				<p id="commentRequired" class="requiredField ">
					<fmt:message key="comment.label.commentRequired" />
				</p>
			</div>
			<div class="row text-center">
				<input type="hidden" name="action" value="addTheComment">
				<button class="buttonStyle" type="submit">
					<fmt:message key="comment.label.addComment" />
				</button>
			</div>
		</form>
	</div>
</div>