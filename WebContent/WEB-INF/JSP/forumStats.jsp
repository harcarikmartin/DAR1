<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum Statistics</title>
</head>
<body class="stats">
	<ol>
		<li>Registered users: <b>${allUsersCount}</b></li>
			<ul>
				<li>Admins: <b>${adminsCount}</b></li>
				<li>Users: <b>${usersCount}</b></li>
					<ul>
						<li>Approved: <b>${approvedCount}</b></li>
						<li>Not Approved: <b>${notApprovedCount}</b></li>
					</ul>
			</ul>
		<li>Topics: <b>${topicsCount}</b></li>
			<ul>
				<li>Public: <b>${publicTopicsCount}</b></li>
				<li>Private: <b>${privateTopicsCount}</b></li>
			</ul>
		<li>Tasks: <b>${tasksCount}</b></li>
			<ul>
				<li>Most answered task: <b>${mostAnsweredTask}</b></li>
			</ul>
		<li>Comments: <b>${commentsCount}</b></li>
	</ol>
</body>
</html>