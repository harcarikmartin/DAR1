<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

<div>&nbsp</div>

<div class="row">
	<div class="col-lg-offset-1 col-lg-10">
		
		<div class="rowBackground col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<form method="post">
				<input type="hidden" name="action" value="showTasks">
						<button class="btn-block-left2 taskStyle" type="submit">${task.taskName}</button>
			</form>
		</div>
		<div class="rowBackground col-lg-12 col-md-12 col-sm-12 col-xs-12 paddingHorizontal1-2">
			<p class="commentStyle">${task.task}</p>
		</div>

		<c:if test="${commentToUpdate != null}">
			<%@ include file="UpdateComment.jsp"%>
		</c:if>

		

	<c:if test="${fn:length(taskComments) > 0}">
	<div class="row ">
	<div class="rowBackground">
		<table id="commentTable" class="rowBackground">
			<thead><tr>
			<th class="actionCell text-center taskHeadStyle">Added by</th>
				<th class="topicCell text-left paddingHorizontal3 taskHeadStyle">Comment</th>
				
				<th class="actionCell text-center taskHeadStyle">Update</th>
				<th class="actionCell text-center taskHeadStyle">Remove</th>
			</tr></thead>
<tbody>
			<c:forEach items="${taskComments}" var="taskComment">
				<tr>
				<td class="actionCell text-center taskInfoStyle">${taskComment.user.userName}</td>
					<td>
						<p class="commentStyle">${taskComment.comment}</p>
					</td>
					
					
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
<!-- 			<tr> -->
<!-- 			<td colspan="4"> -->
<%-- 						<%@ include file="AddComment.jsp"%> --%>
			
<!-- 			</td> -->
<!-- 			</tr> -->
			</tbody>
		</table>
		</div>
		</div>
		
		</c:if>
							<%@ include file="AddComment.jsp"%>
		
<%-- 		<c:if test="${commentAdding != null}"> --%>
<%-- 		</c:if> --%>
	</div>
</div>