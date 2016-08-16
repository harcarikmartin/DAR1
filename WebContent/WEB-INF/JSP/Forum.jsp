<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Forum - Topics</title>

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script> 
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.css"> -->

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>

<style>
<%@include file="DataTables.css"%>
<%@include file="bootstrap/css/bootstrap.css"%>
<%@include file="ForumForBootstrap.css"%>
</style>
</head>

<body>
	<jsp:include page="Header.jsp" />

	<jsp:include page="RegisterForm.jsp" />

	<c:if test="${user.role == 'admin'}">
		<jsp:include page="AdminMenu.jsp" />
	</c:if>
	
	<c:if test="${user.role=='user'}">
		<jsp:include page="UserMenu.jsp" />
	</c:if>

	<c:if test="${registerForm == null  && sessionScope.topic == null}">
		<%@ include file="Topic.jsp"%>
	</c:if>
	
	<c:if test="${registerForm == null && sessionScope.topic != null}">
		<%@ include file="Task.jsp"%>
	</c:if>
	
	<c:if test="${registerForm == null && sessionScope.taskID != null}">
		<%@ include file="Comment.jsp"%>
	</c:if>
	
</body>


<script>
  	$(document).ready( function () {
    //https://datatables.net/
     // pre nezobrazovanie ponuky search 
  	 //  $('#table_id').dataTable({bFilter: false, bInfo: false});	
      
      

	$('#topicsTable').dataTable({
			"columnDefs" : [ {
				"targets" : 'no-sort',
				"orderable" : false,
			} ]
		});

	$('#tasksTable').dataTable({
		"columnDefs" : [ {
			"targets" : 'no-sort',
			"orderable" : false,
		} ]
	});
	
	$('#commentTable').dataTable({
		"columnDefs" : [ {
			"targets" : 'no-sort',
			"orderable" : false,
		} ]
	});
	
		$('div.dataTables_filter input').addClass('searchInTable')
		$('div.dataTables_length select').addClass('numberOfRows')
	});
</script>



<c:if test="${registerForm != null}">
	<script type="text/javascript">
	<%@include file="RegisterForm.js"%>
	</script>
</c:if>

<c:if test="${user == null  && registerForm == null}">
	<script type="text/javascript">
	<%@include file="LoginForm.js"%>
	</script>
</c:if>

<c:if test="${user.role=='admin' && topicAdding != null}">
	<script type="text/javascript">
	<%@include file="AddTopic.js"%>
	</script>
</c:if>

<c:if test="${topicUpdating != null}">
	<script type="text/javascript">
	<%@include file="UpdateTopic.js"%>
	</script>
</c:if>

<c:if test="${user.role=='admin' && changePassword == 1}">
	<script type="text/javascript">
	<%@include file="PasswordChange.js"%>
	</script>
</c:if>

<c:if test="${user.role=='user' && changePassword == 1}">
	<script type="text/javascript">
	<%@include file="PasswordChange.js"%>
	</script>
</c:if>

<c:if test="${sessionScope.topic != null && taskAdding != null}">
	<script type="text/javascript">
	<%@ include file="AddTask.js"%>
	</script>
</c:if>

<c:if test="${sessionScope.topic != null && taskToUpdate != null}">
	<script type="text/javascript">
	<%@ include file="UpdateTask.js"%>
	</script>
</c:if>

<c:if test="${sessionScope.task != null && commentAdding != null}">
	<script type="text/javascript">
	<%@ include file="AddComment.js"%>
	</script>
</c:if>

<c:if test="${sessionScope.task != null && commentToUpdate != null}">
	<script type="text/javascript">
	<%@ include file="UpdateComment.js"%>
	</script>
</c:if>
</html>