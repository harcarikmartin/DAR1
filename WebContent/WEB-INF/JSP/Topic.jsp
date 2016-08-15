<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>

	
<div>&nbsp</div>
	
<c:if test="${fn:length(topics) > 0}">
<div class="row">
	<div class="col-lg-offset-1 col-lg-10">
		<table>
			<tr>
				<th class="topicCell text-left paddingHorizontal topicHeadStyle">Topic</th>
				<th class="actionCell text-center topicHeadStyle">Created by</th>
				<th class="actionCell text-center topicHeadStyle">Visibility</th>
				<c:if test="${user.role == 'admin'}">
					<th class="actionCell text-center topicHeadStyle">Update</th>
					<th class="actionCell text-center topicHeadStyle">Delete</th>
				</c:if>
			</tr>
			<c:forEach items="${topics}" var="topic">
				<c:if test="${user.role == null}">
					<c:if test="${topic.visibility == 'public'}">
						<tr>
							<td>
								<form method="post">
									<input type="hidden" name="action" value="openTopic"> <input
										type="hidden" name="topic" value="${topic.topic}">
									<button class="btn-block-left topicStyle" type="submit">${topic.topic}</button>
								</form>
							</td>
							<td class="actionCell text-center topicInfoStyle">${topic.creator.userName}</td>
							<td class="actionCell text-center topicInfoStyle">${topic.visibility}</td>
						</tr>
					</c:if>
				</c:if>

				<c:if test="${user.role == 'admin'}">
					<tr>
						<td class="topicCell text-left">
							<form method="post">
								<input type="hidden" name="action" value="openTopic"> <input
									type="hidden" name="topic" value="${topic.topic}">
								<button class="btn-block-left topicStyle" type="submit">${topic.topic}</button>
							</form>
						</td>
						<td class="actionCell text-center topicInfoStyle">${topic.creator.userName}</td>
						<td class="actionCell text-center topicInfoStyle">${topic.visibility}</td>

						<c:if test="${user.role == 'admin'}">
							<td class="actionCell">
								<form method="post" class="topicToUpdate">
									<input type="hidden" name="topicToUpdate"
										value="${topic.topic}"> <input type="hidden"
										name="action" value="updateTopic">
									<button type="submit" class="btn-block topicStyle">Update</button>
								</form>
							</td>
							<td class="actionCell text-center">
								<form method="post" class="topicToRemove">
									<input type="hidden" name="topicToRemove"
										value="${topic.topic}"> <input type="hidden"
										name="action" value="removeTopic">
									<button type="submit" class="btn-block topicStyle">Remove</button>
								</form>
							</td>
						</c:if>
					</tr>
				</c:if>


				<c:if test="${user.role == 'user'}">
					<c:if test="${topic.visibility == 'public'}">
						<tr>
							<td class="topicCell text-left">
								<form method="post">
									<input type="hidden" name="action" value="openTopic"> <input
										type="hidden" name="topic" value="${topic.topic}">
									<button class="btn-block-left topicStyle" type="submit">${topic.topic}</button>
								</form>
							</td>
							<td class="actionCell text-center topicInfoStyle">${topic.creator.userName}</td>
							<td class="actionCell text-center topicInfoStyle">${topic.visibility}</td>
						</tr>
					</c:if>

					<c:forEach var="userTopic" items="${userTopics}">
						<c:if test="${topic.topicID == userTopic.topic.topicID}">
							<c:if test="${userTopic.user.userID == user.userID}">
								<tr>
									<td class="topicCell text-left">
										<form method="post">
											<input type="hidden" name="action" value="openTopic">
											<input type="hidden" name="topic"
												value="${userTopic.topic}">
											<button class="btn-block-left topicStyle" type="submit">${userTopic.topic}</button>
										</form>
									</td>
									<td class="actionCell text-center topicInfoStyle">${userTopic.topic.creator.userName}</td>
									<td class="actionCell text-center topicInfoStyle">${userTopic.topic.visibility}</td>
								</tr>
							</c:if>
						</c:if>
					</c:forEach>

				</c:if>
			</c:forEach>

		</table>

	</div>
</div>
</c:if>