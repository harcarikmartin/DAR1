<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

	<div class="row col-lg-12">
		<div class="col-lg-offset-1 col-lg-10 rowBackground">
			<div class="text-left text-center-xs">
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="showProfile">
					<button class="marginHorizontalLeft buttonStyle" type="submit">Profile</button>
				</form>
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="changePassword">
					<button class="marginHorizontalLeft buttonStyle" type="submit">Change
						Password</button>
				</form>
				<form class="userMenu" method="post">
					<input type="hidden" name="action" value="showMyTopics">
					<button class="marginHorizontalLeft buttonStyle" type="submit">Subscribed
						topics</button>
				</form>
			</div>
		</div>
	</div>

	<c:if test="${listProfile != null }">
		<jsp:include page="Profile.jsp" />
	</c:if>

	<c:if test="${changePassword == 1 }">
		<jsp:include page="PasswordChange.jsp" />
	</c:if>

	<c:if test="${listTopics != null}">
		<div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground  ">
				<form method="post">
					<table
						class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-2 col-xs-8">
						<c:forEach var="topic" items="${topics}">
							<c:if test="${topic.visibility == 'private'}">
								<tr>
									<td
										class="subscribedTopicsTableCell paddingHorizontal text-right"><input
										type="checkbox" name="topic" value="${topic.topicID}"
										<c:forEach var="userTopic" items="${userTopics}">
									<c:if test="${topic.topicID == userTopic.topic.topicID}">
										<c:if test="${userTopic.user.userID == user.userID}">
										checked 
										</c:if>
									</c:if>
								</c:forEach>>
									</td>
									<td class="subscribedTopicsTableCell paddingHorizontal">${topic.topic}</td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td class="subscribedTopicsTableCell"></td>
							<td class="text-left paddingHorizontal subscribedTopicsTableCell"><input
								type="hidden" name="action" value="changeTopics">
								<button class="buttonStyle" type="submit">Change topics</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</c:if>

