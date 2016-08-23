<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="languages.text" />

<div class="row marginHeavy">
	<div class="col-lg-offset-1 col-lg-10">
		<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<form class="navigationMenu" method="post">
				<input type="hidden" name="action" value="showTopics">
				<button class="marginHorizontalLeft marginVertical buttonStyle" type="submit">
					<fmt:message key="all.label.goTopics" />
				</button>
			</form>
			<form class="navigationMenu" method="post">
				<input type="hidden" name="action" value="showTasks">
				<button class="marginHorizontalLeft marginVertical buttonStyle" type="submit">
					<fmt:message key="all.label.goTasks" />
				</button>
			</form>
		</div>

		<c:if test="${sessionScope.task != null}">
			<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<span class="topicHierarchy">${topic.topic}</span>
			</div>
			<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<span class="taskHierarchy">${task.taskName}</span>
			</div>
			<div class="rowBackgroundNone col-lg-12 col-md-12 col-sm-12 col-xs-12 paddingHorizontal1-2 borderBtm">
				<p class="taskBody">
					<fmt:message key="comment.label.askedBy" />
					<span>${task.user.userName}</span>
					<br>
					<br>
					${task.task}
				</p>
			</div>
			<c:if test="${commentToUpdate != null}">
				<jsp:include page="UpdateComment.jsp" />
			</c:if>
		</c:if>

		<c:if test="${fn:length(taskComments) > 0}">
			<div class="row ">
				<div class="rowBackground">
					<table id="commentTable" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
						<tbody>
							<c:forEach items="${taskComments}" var="taskComment">
								<tr class="commentTR">
									<td class="commentImage text-center taskNoButton">
										<c:if test="${taskComment.user.profileImage == null}">
											<img src="images/default.jpg" height="200" alt="<fmt:message key="all.label.defImg"/>" class="commentIMG">
										</c:if>
										<c:if test="${taskComment.user.profileImage != null}">
											<img src="images/${taskComment.user.userID}.jpg" height="50" alt="<fmt:message key="all.label.userImg"/>" class="commentIMG">
										</c:if>
									</td>
									<td class="commentBody commentStyle rowBackground">
										<p class="  verticalAlign">
											<fmt:message key="comment.label.who" />
											<span>${taskComment.user.userName}</span>
											<fmt:message key="comment.label.day" />
											<span>
												<fmt:formatDate value="${taskComment.addedOn}" pattern="MM/dd/yyyy" />
											</span>
											<fmt:message key="comment.label.time" />
											<span>
												<fmt:formatDate value="${taskComment.addedOn}" pattern="HH:mm" />
											</span>
										</p>
										${taskComment.comment}
									</td>
									<td class="commentBtn text-center taskNoButton">
										<c:if test="${taskComment.user.userID == sessionScope.user.userID || sessionScope.user.role == 'admin'}">
											<form method="post">
												<input type="hidden" name="CommentToUpdate" value="${taskComment.commentID}">
												<input type="hidden" name="action" value="updateComment">
												<button class="btn-block taskStyle" type="submit">
													<fmt:message key="all.label.update" />
												</button>
											</form>
										</c:if>
									</td>
									<td class="commentBtn text-center taskNoButton">
										<c:if test="${taskComment.user.userID == sessionScope.user.userID || sessionScope.user.role == 'admin'}">
											<form method="post">
												<input type="hidden" name="CommentToRemove" value="${taskComment.commentID}">
												<input type="hidden" name="action" value="removeComment">
												<button class="btn-block taskStyle" type="submit">
													<fmt:message key="all.label.remove" />
												</button>
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
			<jsp:include page="AddComment.jsp" />
		</c:if>
	</div>
</div>