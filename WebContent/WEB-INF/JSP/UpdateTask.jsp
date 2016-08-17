<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
    
		<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<form method="post" onsubmit="return checkRequiredEditTask()">
						<div class="row text-center"><label class="simpleText" for="nameOfEditedTask">Edit name of task:</label></div>
						<div class="row text-center"><input class="inputForNewStuff" id="nameOfEditedTask" type="text" name="editNameTask" value="${taskUpdating.taskName}" placeholder="name of task ..." autofocus maxlength="100"></div>
						<div class="row text-center"><p id="taskNameRequired" class="requiredField ">Task name is required</p></div><br>
						<div class="row text-center"><label class="simpleText" for="bodyOfEditedTask">Edit task:</label></div>
						<div class="row text-center"><textarea class="inputForNewStuff" id="bodyOfEditedTask" name="editBodyTask"  spellcheck="true" lang="en"
							cols="50" rows="5" placeholder="body of task ..." maxlength="500">${taskUpdating.task}</textarea></div>
						<div class="row text-center"><p id="taskBodyRequired" class="requiredField ">Task is required</p></div>
						<div class="row text-center">
						<input type="hidden" name="taskID" value="${taskUpdating.taskID}">
						<input type="hidden" name="action" value="updateTheTask">
						<button class="buttonStyle" type="submit">Edit task</button>
						</div>
				</form>
			</div>
		</div>