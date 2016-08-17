<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<div class="row marginHeavy">
	<div class="col-lg-offset-1 col-lg-10">
		
		<c:if test="${sessionScope.task == null}">
		<div>&nbsp</div>
		
		<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<form class="navigationMenu" method="post">
				<input type="hidden" name="action" value="showTopics">
						<button class="marginHorizontalLeft marginVertical buttonStyle" type="submit">Go to topics</button>
			</form>
		</div>
		
		<div class="row rowBackground col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="col-lg-9 col-md-9 col-sm-9 col-xs-7">
			<span class="topicHierarchy">${topic.topic}</span>
		</div>


		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-5">
			<c:if test="${sessionScope.user != null}">
				<form method="post">
					<input type="hidden" name="action" value="addTask">
					<button class="btn-block-right addTaskBtn" type="submit">Add
						new task</button>
				</form>
			</c:if>
			<c:if test="${sessionScope.user == null}">
				<form method="post">
					<input type="hidden" name="action" value="addTask">
					<button class="btn-block-right addTaskBtn" disabled="disabled"
						type="submit">Add new task</button>
				</form>
			</c:if>
		</div>
		</div>
</c:if>

		<c:if test="${taskAdding != null}">
			<jsp:include page="AddTask.jsp" />
		</c:if>

		<c:if test="${taskToUpdate != null}">
			<jsp:include page="UpdateTask.jsp" />
		</c:if>

	<c:if test="${fn:length(topicTasks) > 0}">
	
	<div class="row">
	<div class="rowBackground ">
		<table id="tasksTable" class="rowBackground">
			
			<thead><tr>
				<th class="topicCell text-left paddingHorizontal2 taskHeadStyle">Task</th>
				<th class="actionCell text-center taskHeadStyle">Added by</th>
				<th class="actionCell text-center taskHeadStyle no-sort">Update</th>
				<th class="actionCell text-center taskHeadStyle no-sort">Remove</th>
			</tr></thead>

<tbody>
			<c:forEach items="${topicTasks}" var="topicTask">
				<tr class="commentTR">
					<td>
						<c:if test="${sessionScope.user != null}">
						<form method="post">
							<input type="hidden" name="action" value="openTask"> <input
								type="hidden" name="idOfTask" value="${topicTask.taskID}">
							<button class="btn-block-left2 taskStyle" type="submit">${topicTask.taskName}</button>
						</form>
						</c:if>
						
						<c:if test="${sessionScope.user == null}">
						<form method="post">
							<input type="hidden" name="action" value="openTask"> <input
								type="hidden" name="idOfTask" value="${topicTask.taskID}">
							<button class="btn-block-left2 taskStyle" disabled="disabled" type="submit">${topicTask.taskName}</button>
						</form>
						</c:if>
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
			</tbody>
		</table>
		</div>
</div>
		</c:if>
	</div>
</div>