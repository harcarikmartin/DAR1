<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<!DOCTYPE html >
<c:if test="${language == null}">
	<html lang="en">
</c:if>
<c:if test="${language != null}">
	<html lang="${language}">
</c:if>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Forum</title>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script> 
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<style type="text/css">
<%@include file="bootstrap/css/bootstrap.min.css"%>
</style>
<style type="text/css">
<%@include file="bootstrap/css/bootstrap.min.css"%>
</style>
<jsp:include page="ColorCssInclude.jsp" />
</head>
<body>
	<div class="wrapper">
		<jsp:include page="Header.jsp" />
		<jsp:include page="RegisterForm.jsp" />
		<c:if test="${user.role == 'admin'}">
			<jsp:include page="AdminMenu.jsp" />
		</c:if>
		<c:if test="${user.role=='user'}">
			<jsp:include page="UserMenu.jsp" />
		</c:if>
		<c:if test="${registerForm == null  && sessionScope.topic == null}">
			<jsp:include page="Topic.jsp" />
		</c:if>
		<c:if test="${registerForm == null && sessionScope.topic != null}">
			<jsp:include page="Task.jsp" />
		</c:if>
		<c:if test="${registerForm == null && sessionScope.taskID != null}">
			<jsp:include page="Comment.jsp" />
		</c:if>
		<div class="push"></div>
	</div>
	<div class="footer">
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 footerBCG text-center">
				<p>
					<a href="/Forum_Dar1/ForumStatsServlet"><fmt:message key="all.label.stats" /></a>
				</p>
			</div>
		</div>
	</div>
</body>

	<c:if test="${user.role=='admin'}">
		<script type="text/javascript">
		<%@include file="js/DataTablesAdmin.js"%>
		</script>
	</c:if>
	
	<c:if test="${user.role=='user'}">
		<script type="text/javascript">
		<%@include file="js/DataTablesUserGuest.js"%>
		</script>
	</c:if>
	
	<c:if test="${user.role==null}">
		<script type="text/javascript">
		<%@include file="js/DataTablesUserGuest.js"%>
		</script>
	</c:if>
	
	<c:if test="${registerForm != null}">
		<script type="text/javascript">
		<%@include file="js/RegisterForm.js"%>
		</script>
	</c:if>
	
	<c:if test="${user == null  && registerForm == null}">
		<script type="text/javascript">
		<%@include file="js/LoginForm.js"%>
		</script>
	</c:if>
	
	<c:if test="${user.role=='admin' && topicAdding != null}">
		<script type="text/javascript">
		<%@include file="js/AddTopic.js"%>
		</script>
	</c:if>
	
	<c:if test="${topicUpdating != null}">
		<script type="text/javascript">
		<%@include file="js/UpdateTopic.js"%>
		</script>
	</c:if>
	
	<c:if test="${sessionScope.user == null && sessionScope.topic != null}">
		<script type="text/javascript">
		<%@include file="js/Task.js"%>
		</script>
	</c:if>
	<c:if test="${user.role=='admin' && changePassword == 1}">
		<script type="text/javascript">
		<%@include file="js/PasswordChange.js"%>
		</script>
	</c:if>
	
	<c:if test="${user.role=='user' && changePassword == 1}">
		<script type="text/javascript">
		<%@include file="js/PasswordChange.js"%>
		</script>
	</c:if>
	
	<c:if test="${sessionScope.topic != null && taskAdding != null}">
		<script type="text/javascript">
		<%@ include file="js/AddTask.js"%>
		</script>
	</c:if>
	
	<c:if test="${sessionScope.topic != null && taskToUpdate != null}">
		<script type="text/javascript">
		<%@ include file="js/UpdateTask.js"%>
		</script>
	</c:if>
	
	<c:if test="${sessionScope.task != null && commentToUpdate == null}">
		<script type="text/javascript">
		<%@ include file="js/AddComment.js"%>
		</script>
	</c:if>
	
	<c:if test="${sessionScope.task != null && commentToUpdate != null && commentToUpdate != null}">
		<script type="text/javascript">
		<%@ include file="js/UpdateComment.js"%>
		</script>
	</c:if>
</html>