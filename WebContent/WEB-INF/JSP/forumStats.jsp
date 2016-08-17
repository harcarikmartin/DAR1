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
				<li>Admins: <b>${adminsCount}</b>, Regular users: <b>${usersCount}</b></li>
				<li>Approved: <b>${approvedCount}</b>, Not Approved: <b>${notApprovedCount}</b></li>
			</ul>
		<li>Most active user is <b>${mostActiveUser}</b> with <b>${commentsForMostActiveUser}</b> comments.</li>
		<li>Topics: <b>${topicsCount}</b></li>
			<ul>
				<li>Public: <b>${publicTopicsCount}</b></li>
				<li>Private: <b>${privateTopicsCount}</b></li>
			</ul>
		<li>Tasks: <b>${tasksCount}</b></li>
			<ul>
				<li>Most answered task is <b>${mostAnsweredTask}</b>, commented <b>${commentsForMostAnsweredTask}</b> times.</li>
			</ul>
		<li>Comments total: <b>${commentsCount}</b></li>
	</ol>
	<p>to <a href="/Forum/Forum">Forum</a></p>
</body>
</html>