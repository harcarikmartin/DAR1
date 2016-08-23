<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<div class="row">
	<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
		<form method="post" onsubmit="return checkRequiredAddTask()">
			<div class="row text-center">
				<label class="simpleText" for="nameOfTask">
					<fmt:message key="task.label.taskName"/>
				</label>
			</div>
			<div class="row text-center">
				<input class="inputForNewStuff" id="nameOfTask" type="text" name="nameOfTask" placeholder="<fmt:message key="task.label.taskNamePlaceholder"/>"
					required="autofocus" maxlength="100">
			</div>
			<div class="row text-center">
				<p id="taskNameRequired" class="requiredField ">
					<fmt:message key="task.label.taskNameReq"/>
				</p>
			</div>
			<br>
			<div class="row text-center">
				<label class="simpleText" for="bodyOfTask">
					<fmt:message key="task.label.taskLabel"/>
				</label>
			</div>
			<div class="row text-center">
				<textarea class="inputForNewStuff" id="bodyOfTask" name="bodyOfTask" spellcheck="true" lang="en" cols="50" rows="5"
					placeholder="<fmt:message key="task.label.taskPlaceholder"/>" maxlength="500"></textarea>
			</div>
			<div class="row text-center">
				<p id="taskBodyRequired" class="requiredField ">
					<fmt:message key="task.label.taskReq"/>
				</p>
			</div>
			<div class="row text-center">
				<input type="hidden" name="topic" value="${topic}">
				<input type="hidden" name="action" value="addTheTask">
				<button class="buttonStyle" type="submit">
					<fmt:message key="task.label.addTask"/>
				</button>
			</div>
		</form>
	</div>
</div>