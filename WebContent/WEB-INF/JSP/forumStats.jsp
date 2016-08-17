<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum Statistics</title>
<style>
<%@include file="bootstrap/css/bootstrap.min.css"%>
<%@include file="ForumForBootstrap.css"%>
</style>
</head>
<body>
	<div class="wrapper">
		<div class="row col-lg-12">
			<div class="col-lg-offset-1 col-lg-10 text-center">
				<form method="post">
					<p class=" fullWidth mainPage">STATISTICS</p>
				</form>
			</div>
		</div>

		<div class="row col-lg-12">
			<div id="login" class="col-lg-offset-1 col-lg-10 rowBackground">
				<div class="row">
					<div class="text-left col-lg-10 col-md-10 col-sm-10 col-xs-12">
						<ol class="stats">
							<li class="stats">Registered users: <span>${allUsersCount}</span></li>
							<ul>
								<li class="stats">Admins: <span>${adminsCount}</span>,
									Regular users: <span>${usersCount}</span></li>
								<li class="stats">Approved: <span>${approvedCount}</span>,
									Not Approved: <span>${notApprovedCount}</span></li>
							</ul>
							<hr>
							<li class="stats">Most active user is <span>${mostActiveUser}</span>
								with <span>${commentsForMostActiveUser}</span> comments.
							</li>
							<hr>
							<li class="stats">Topics: <span>${topicsCount}</span></li>
							<ul>
								<li class="stats">Public: <span>${publicTopicsCount}</span></li>
								<li class="stats">Private: <span>${privateTopicsCount}</span></li>
							</ul>
							<hr>
							<li class="stats">Tasks: <span>${tasksCount}</span></li>
							<ul>
								<li class="stats">Most answered task is <span>${mostAnsweredTask}</span>,
									commented <span>${commentsForMostAnsweredTask}</span> times.
								</li>
							</ul>
							<hr>
							<li class="stats">Comments total: <span>${commentsCount}</span></li>
						</ol>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="row">
				<div
					class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 footerBCG text-center">
					<p>
						<a href="/Forum/Forum">Forum</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</body>
</html>