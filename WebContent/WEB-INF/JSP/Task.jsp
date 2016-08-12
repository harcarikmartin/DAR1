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

			<%@ include file="AddTask.jsp"%>

		<table>
			<tr>
				<th class="topicCell text-left paddingHorizontal taskHeadStyle">Task</th>
				<th class="actionCell text-center taskHeadStyle">Added by</th>
				<th class="actionCell text-center taskHeadStyle">Update</th>
				<th class="actionCell text-center taskHeadStyle">Delete</th>
			</tr>

			<tr>
				<td><button class="btn-block-left topicStyle" type="submit">task
						1</button></td>
				<td class="actionCell text-center topicInfoStyle">creator</td>
				<td class="actionCell text-center">
					<form>
						<input type="hidden" name="taskToUpdate" value="${task.task}">
						<input type="hidden" name="action" value="updateTask">
						<button class="btn-block topicStyle" type="submit">Update</button>
					</form>
				</td>
				<td class="actionCell text-center">
					<form>
						<input type="hidden" name="taskToRemove" value="${task.task}">
						<input type="hidden" name="action" value="removeTask">
						<button class="btn-block topicStyle" type="submit">Remove</button>
					</form>
				</td>
			</tr>

			<c:forEach var="topicTask" items="${topicTasks}">
				<tr>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="openTask"> <input
								type="hidden" name="idOfTask" value="${task.taskID}">
							<button class="btn-block-left topicStyle" type="submit">${topicTask.task.task}</button>
						</form>
					</td>
					<td class="actionCell text-center topicInfoStyle">${topicTask.topic.user.userName}</td>
					<td>
						<form>
							<input type="hidden" name="taskToUpdate" value="${task.task}">
							<input type="hidden" name="action" value="updateTask">
							<button type="submit">Remove</button>
						</form>
					</td>
					<td>
						<form>
							<input type="hidden" name="taskToRemove" value="${task.task}">
							<input type="hidden" name="action" value="removeTask">
							<button type="submit">Remove</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>




