<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
<c:if test="${user.role=='user'}">
	<form method="post">
		<label for ="seeProfile"> My profile</label>
		<input type="hidden" name="action" value="showProfile">
		<button type="submit">Profile</button>
	</form>

	<form method="post">
		<label for ="seeTopic"> My topics</label>
		<input type="hidden" name="action" value="showMyTopics">
		<button type="submit">Topic</button>
	</form>

   <c:if test="${listProfile != null}">
	  	<p>Username: ${user.userName} </p>
		<p>Birthdate: ${user.birthDate}</p>
		<p>Role: ${user.role}</p>
		<p>Status: ${user.status}</p>
 
	   	<form method="post">
			<input type="hidden" name="action" value="changePassword" >
		    <button type="submit">Change Password</button>
		</form>
   
   		<c:if test="${changePassword == 1 }">
			<form method="post">
				<label for="OldPassword">Old password:</label>
				<input id="OldPassword" type="password" name="userPasswordOld" placeholder="password"><br>
				<label for="NewPassword">New password:</label>
				<input id="NewPassword"type="password" name="userPasswordNew" 
           		pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" 
            	oninvalid="alert('Password is not valid')" placeholder="password">
            	<p>* Password must contain minimum 8 characters, at least 1 Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special Character [!@#$%^&*_=+-]</p>
				<label for="NewPasswordConfirm">Confirm new password:</label>
				<input id="NewPasswordConfirm" type="password" name="userPasswordNewCheck" placeholder="password"><br>
				
				<input type="hidden" name="action" value="changeMyPassword" >
	    		<button type="submit">Change</button>
			</form>
		</c:if>
 	 </c:if>    
  
  	<c:if test="${listTopics != null}">
	   	<form>
	     	<table>
		        <tr>
		            <th>Name of topic</th>
		        </tr>
				<c:forEach var="topic" items="${topics}">
					<tr>
						<td>
							<input type="checkbox" name="topic" value="	${topic.topicID}"> ${topic.topic}
						</td>
					</tr>
				</c:forEach>
	   		</table>   
	    	<input type="hidden" name="action" value="changeTopics">
	     	 <button type="submit">Change topics </button>
		</form>
  	</c:if>
</c:if>   

    
     