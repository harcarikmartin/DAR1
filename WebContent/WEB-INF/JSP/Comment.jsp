<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<div class="row marginHeavy">
	<div class="col-lg-offset-1 col-lg-10">
		<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<form class="navigationMenu" method="post">
				<input type="hidden" name="action" value="showTopics">
						<button class="marginHorizontalLeft marginVertical buttonStyle" type="submit">Go to topics</button>
			</form>
			<form class="navigationMenu" method="post">
				<input type="hidden" name="action" value="showTasks">
						<button class="marginHorizontalLeft marginVertical buttonStyle" type="submit">Go to tasks</button>
			</form>
		</div>
		
		
		<c:if test="${sessionScope.task != null}">
		<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
		
						<span class="topicHierarchy">${topic.topic}</span>
			
		</div>
		<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			
						<span class="taskHierarchy">${task.taskName}</span>
		
		</div>
		<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12 paddingHorizontal1-2 borderBtm">
			<p class="taskBody">
			Asked by <span>${task.user.userName}</span><br><br>
			${task.task}</p>
		</div>

		<c:if test="${commentToUpdate != null}">
			<%@ include file="UpdateComment.jsp"%>
		</c:if>
</c:if>
		

	<c:if test="${fn:length(taskComments) > 0}">
	<div class="row ">
	<div class="rowBackground">
		<table id="commentTable" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground ">

		<tbody>
			<c:forEach items="${taskComments}" var="taskComment">
				<tr class="commentTR">
					<td class="commentBody commentStyle rowBackground">
					<p class="  verticalAlign">
					
					by <span>${taskComment.user.userName}</span> on 
					<span><fmt:formatDate value="${taskComment.addedOn}" pattern="MM/dd/yyyy"/></span> at 
					<span><fmt:formatDate value="${taskComment.addedOn}" pattern="HH:mm"/></span></p>
						${taskComment.comment}
						
					</td>
					
					
					<td class="commentBtn text-center taskNoButton">
					<c:if test="${taskComment.user.userID == sessionScope.user.userID}">
						<form method="post">
							<input type="hidden" name="CommentToUpdate" value="${taskComment.commentID}">
							<input type="hidden" name="action" value="updateComment">
							<button class="btn-block taskStyle" type="submit">Update</button>
						</form>
					</c:if>
					</td>
					
					<td class="commentBtn text-center taskNoButton">
						<c:if test="${taskComment.user.userID == sessionScope.user.userID}">
						<form method="post">
							<input type="hidden" name="CommentToRemove" value="${taskComment.commentID}">
							<input type="hidden" name="action" value="removeComment">
							<button class="btn-block taskStyle" type="submit">Remove</button>
						</form>
						</c:if>
					</td>
				</tr>

			</c:forEach>

			</tbody>
		</table>
		</div>
		</div>
		
		</c:if>
		<c:if test="${sessionScope.task != null && commentToUpdate == null}">
			<%@ include file="AddComment.jsp"%>
		</c:if>
							

	</div>
</div>