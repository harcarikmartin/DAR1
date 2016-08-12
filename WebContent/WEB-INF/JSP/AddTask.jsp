<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>



	<c:if test="${taskAdding != null}">
		<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<form method="post" onsubmit="return checkRequiredAddTask()">
						<div class="row text-center"><label class="simpleText" for="nameOfTask">Write name of task:</label></div>
						<div class="row text-center"><input class="inputForNewStuff" id="nameOfTask" type="text" name="nameOfTask" placeholder="name of task ..." autofocus></div>
						<div class="row text-center"><p id="taskNameRequired" class="requiredField ">Task name is required</p></div>
						
						
						<div class="row text-center"><label class="simpleText" for="bodyOfTask">Write task:</label></div>
						<div class="row text-center"><textarea class="inputForNewStuff" id="bodyOfTask" name="bodyOfTask" spellcheck="true" lang="en"
							cols="50" rows="5" placeholder="body of task ..."></textarea></div>
						<div class="row text-center"><p id="taskBodyRequired" class="requiredField ">Task is required</p></div>
						
						
						
						<div class="row text-center">
						<input type="hidden" name="topic" value="${topic}">
						<input type="hidden" name="action" value="addTheTask">
						<button class="buttonStyle" type="submit">Add task</button>
						</div>
				</form>
			</div>
		</div>
	</c:if>





