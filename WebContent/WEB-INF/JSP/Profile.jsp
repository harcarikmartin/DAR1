<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<table
					class="col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-1 col-xs-10">
					<tr>
						<td rowspan="6" class="userProfileInfo text-right paddingHorizontal">
						<c:if test="${sessionScope.user.profileImage == null}">
							 <img src="images/default.jpg" height="200" alt="Default profile image">
						</c:if>
						<c:if test="${sessionScope.user.profileImage != null}">
							<img src="images/${sessionScope.user.userID}.jpg" height="200"   alt="User profile image">
						</c:if>
						
						</td>
						<td class="userProfileInfo text-right paddingHorizontal">Username:</td>
						<td class="userProfileInfo paddingHorizontal">${user.userName}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Birthdate:</td>
						<td class="userProfileInfo paddingHorizontal">${user.birthDate}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Role:</td>
						<td class="userProfileInfo paddingHorizontal">${user.role}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Status:</td>
						<td class="userProfileInfo paddingHorizontal">${user.status}</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center"><p class="userProfileInfo">Select image to upload:</p></td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
						<form method="post" enctype="multipart/form-data">
							<label for="file-upload" class="custom-file-upload">Select a file</label>
							<input id="file-upload" type="file" accept="image/jpeg"/>
							<input type="file" name="fileToUpload" id="fileToUpload" size="50" accept="image/jpeg">
							<input type="hidden" name="action" value="UploadImage"><br>
							<button class=" buttonStyle" type="submit">Send</button>				
						</form>
						</td>
					</tr>
					
					
					
				</table>
			</div>

	


<!-- 	<div -->
<!-- 		class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground text-center"> -->
<!-- 		<p class="userProfileInfo">Select image to upload:</p> -->
<!-- 		<form method="post" enctype="multipart/form-data"> -->

<!-- 			<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground text-center"> -->
<!-- 				<label for="file-upload" class="custom-file-upload">Select a file</label> -->
<!-- 				<input id="file-upload" type="file"/> -->
<!-- 				<input type="file" name="fileToUpload" id="fileToUpload" size="50"> -->
<!-- 				<input type="hidden" name="action" value="UploadImage"> -->
<!-- 			</div> -->
<!-- 				<div class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground text-center"> -->
<!-- 				<input type="hidden" name="action" value="sendImage"> -->
<!-- 				<button class=" buttonStyle" type="submit">Send</button> -->
<!-- 				</div> -->
				
<!-- 		</form> -->
<!-- 	</div> -->
</div>