<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<table>
	<tr>
		<th>Name of task</th>
		<th>Create by</th>
	</tr>
	
	<tr>
		<td><button>task 1</button></td>
		<td>creator</td>
		<td>
			<form>
			<input type="hidden" name="taskToUpdate" value="${task.task}">
			<input type="hidden" name="action" value="updateTask">
			<button type="submit">Update</button>
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

	<c:forEach var="topicTask" items="${topicTasks}">
		<tr>
			<td><button>${topicTask.task.task}</button></td>
			<td>${topicTask.topic.user.userName}</td>
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



				