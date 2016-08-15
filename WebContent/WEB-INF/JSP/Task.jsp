<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<div class="row">
	<div class="col-lg-offset-1 col-lg-10">

		<div class=" rowBackground">
			<form method="post">
				<input type="hidden" name="action" value="addTask">
				<button class="btn-block-left topicStyle" type="submit">Add
					new task</button>
			</form>
		</div>

		<c:if test="${taskAdding != null}">
			<%@ include file="AddTask.jsp"%>
		</c:if>

		<c:if test="${taskToUpdate != null}">
			<%@ include file="UpdateTask.jsp"%>
		</c:if>

		<table>

			<tr>
				<td colspan="4">
					<form method="post">
						<input type="hidden" name="action" value="showTopics">
						<button class="btn-block-left topicStyle" type="submit">${topic}</button>
					</form>
				</td>
			</tr>

			<tr>
				<th class="topicCell text-left paddingHorizontal2 taskHeadStyle">Task</th>
				<th class="actionCell text-center taskHeadStyle">Added by</th>
				<th class="actionCell text-center taskHeadStyle">Update</th>
				<th class="actionCell text-center taskHeadStyle">Delete</th>
			</tr>

			<tr>
				<td><button class="btn-block-left2 topicStyle" type="submit">task
						1</button></td>
				<td class="actionCell text-center topicInfoStyle">creator</td>
				<td class="actionCell text-center">
					<form>
						<button class="btn-block topicStyle" type="submit">Update</button>
					</form>
				</td>
				<td class="actionCell text-center">
					<form>
						<button class="btn-block topicStyle" type="submit">Remove</button>
					</form>
				</td>
			</tr>

			<c:forEach items="${topicTasks}" var="topicTask">
				<tr>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="openTask"> <input
								type="hidden" name="idOfTask" value="${task.taskID}">
							<button class="btn-block-left2 topicStyle" type="submit">${topicTask.task}</button>
						</form>
					</td>
					<td class="actionCell text-center topicInfoStyle">${topicTask.user.userName}</td>
					<td>
						<form>
							<input type="hidden" name="taskToUpdate" value="${task.taskID}">
							<input type="hidden" name="action" value="updateTask">
							<button type="submit">Remove</button>
						</form>
					</td>
					<td>
						<form>
							<input type="hidden" name="taskToRemove" value="${task.taskID}">
							<input type="hidden" name="action" value="removeTask">
							<button type="submit">Remove</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>




