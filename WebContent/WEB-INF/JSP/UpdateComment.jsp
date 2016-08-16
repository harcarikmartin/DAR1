<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
    

		<div class="row">
			<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<form method="post" onsubmit="return checkRequiredEditComment()">
						<div class="row text-center"><label class="simpleText" for="editedComment">Edit comment:</label></div>
						<div class="row text-center"><textarea class="inputForNewStuff" id="editedComment" name="editComment"  spellcheck="true" lang="en"
							cols="50" rows="5" placeholder="comment ..." autofocus>${commentUpdating.comment}</textarea></div>
						<div class="row text-center"><p id="commentRequired" class="requiredField ">Comment is required</p></div>
						
						
						
						<div class="row text-center">
						<input type="hidden" name="commentID" value="${commentUpdating.commentID}">
						<input type="hidden" name="action" value="updateTheComment">
						<button class="buttonStyle" type="submit">Edit comment</button>
						</div>
				</form>
			</div>
		</div>