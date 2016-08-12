<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Forum - Tasks</title>
<style>
<%@include file="bootstrap/css/bootstrap.css"%>
<%@include file="ForumForBootstrap.css"%>
</style>
</head>

<body>
	<jsp:include page="Header.jsp" />

	<jsp:include page="RegisterForm.jsp" />

	<jsp:include page="AdminMenu.jsp" />

	<jsp:include page="UserMenu.jsp" />

	<c:if test="${registerForm == null}">
		<%@ include file="Task.jsp"%>
	</c:if>

						<%-- <c:if test="${showTaskServlet}"> --%>
						<%-- 	<%@ include file="/Forum/Task"%> --%>
						<%-- </c:if> --%>
					
						<%-- 	<%@ include file="Task.jsp"%> --%>
						<%-- 	<%@ include file="AddTask.jsp"%> --%>

</body>

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

</html>