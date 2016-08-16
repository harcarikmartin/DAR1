<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<div>&nbsp</div>

<div class="row">
	<div class="col-lg-offset-1 col-lg-10">
		
		<div class="rowBackground col-lg-10 col-md-10 col-sm-9 col-xs-9">
			<form method="post">
				<input type="hidden" name="action" value="showComments">
						<button class="btn-block-left topicStyle" type="submit">${task.taskName}</button>
			</form>
		</div>
		
		
		<div class=" rowBackground col-lg-2 col-md-2 col-sm-3 col-xs-3">
			<form method="post">
				<input type="hidden" name="action" value="addComment">
				<button class="btn-block-right topicStyle" type="submit">Add
					new comment</button>
			</form>
		</div>

		<c:if test="${commentAdding != null}">
			<%@ include file="AddComment.jsp"%>
		</c:if>

		<c:if test="${commentToUpdate != null}">
			<%@ include file="UpdateComment.jsp"%>
		</c:if>

		

	<c:if test="${fn:length(taskComments) > 0}">
	<div class="row ">
	<div class="rowBackground">
		<table id="commentTable" class="rowBackground">
			<thead><tr>
				<th class="topicCell text-left paddingHorizontal3 taskHeadStyle">Comment</th>
				<th class="actionCell text-center taskHeadStyle">Added by</th>
				<th class="actionCell text-center taskHeadStyle">Update</th>
				<th class="actionCell text-center taskHeadStyle">Remove</th>
			</tr></thead>
<tbody>
			<c:forEach items="${taskComments}" var="taskComment">
				<tr>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="openComment"> <input
								type="hidden" name="idOfComment" value="${taskComment.commentID}">
							<button class="btn-block-left3 taskStyle" type="submit">${taskComment.comment}</button>
						</form>
					</td>
					<td class="actionCell text-center taskInfoStyle">${taskComment.user.userName}</td>
					
					<td class="actionCell text-center taskNoButton">
					<c:if test="${taskComment.user.userID == sessionScope.user.userID}">
						<form method="post">
							<input type="hidden" name="CommentToUpdate" value="${taskComment.commentID}">
							<input type="hidden" name="action" value="updateComment">
							<button class="btn-block taskStyle" type="submit">Update</button>
						</form>
					</c:if>
					</td>
					
					<td class="actionCell text-center taskNoButton">
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
	</div>
</div>