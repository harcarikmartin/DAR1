<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<div>&nbsp</div>

<div class="row">
	<div class="col-lg-offset-1 col-lg-10">
		
		<div class="rowBackground col-lg-10 col-md-10 col-sm-9 col-xs-9">
			<form method="post">
				<input type="hidden" name="action" value="showTopics">
						<button class="btn-block-left topicStyle" type="submit">${topic}</button>
			</form>
		</div>
		
		
		<div class=" rowBackground col-lg-2 col-md-2 col-sm-3 col-xs-3">
			<form method="post">
				<input type="hidden" name="action" value="addTask">
				<button class="btn-block-right topicStyle" type="submit">Add
					new task</button>
			</form>
		</div>

		<c:if test="${taskAdding != null}">
			<%@ include file="AddTask.jsp"%>
		</c:if>

		<c:if test="${taskToUpdate != null}">
			<%@ include file="UpdateTask.jsp"%>
		</c:if>

		

	<c:if test="${fn:length(topicTasks) > 0}">
		<table>
			<tr>
				<th class="topicCell text-left paddingHorizontal2 taskHeadStyle">Task</th>
				<th class="actionCell text-center taskHeadStyle">Added by</th>
				<th class="actionCell text-center taskHeadStyle">Update</th>
				<th class="actionCell text-center taskHeadStyle">Remove</th>
			</tr>

			<c:forEach items="${topicTasks}" var="topicTask">
				<tr>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="openTask"> <input
								type="hidden" name="idOfTask" value="${topicTask.taskID}">
							<button class="btn-block-left2 taskStyle" type="submit">${topicTask.taskName}</button>
						</form>
					</td>
					<td class="actionCell text-center taskInfoStyle">${topicTask.user.userName}</td>
					
					<td class="actionCell text-center taskNoButton">
					<c:if test="${topicTask.user.userID == sessionScope.user.userID}">
						<form method="post">
							<input type="hidden" name="taskToUpdate" value="${topicTask.taskID}">
							<input type="hidden" name="action" value="updateTask">
							<button class="btn-block taskStyle" type="submit">Update</button>
						</form>
					</c:if>
					</td>
					
					<td class="actionCell text-center taskNoButton">
						<c:if test="${topicTask.user.userID == sessionScope.user.userID}">
						<form method="post">
							<input type="hidden" name="taskToRemove" value="${topicTask.taskID}">
							<input type="hidden" name="action" value="removeTask">
							<button class="btn-block taskStyle" type="submit">Remove</button>
						</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</div>




