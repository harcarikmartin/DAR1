<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>


<form method="post">
	<label for="nameOfTask">Write name of task:</label><br> <input
		id="nameOfTask" type="text" placeholder="name of task ..."><br>
	<label for="bodyOfTask">Write task:</label><br>
	<textarea name="bodyOfTask" id="bodyOfTask" spellcheck="true" lang="en"
		cols="50" rows="5" placeholder="Add body of task ..."></textarea>
	<br>
	<button type="submit">Add Task</button>
</form>



