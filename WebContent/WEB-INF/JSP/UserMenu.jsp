<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
<c:if test="${user.role=='user'}">

	<form>
	<label for ="seeProfile"> My profile</label>
	<input type="hidden" name="action" value="profile">
	<button type="submit">Profile</button>
	</form>


	<form>
	<label for ="seeTopic"> My topics</label>
	<input type="hidden" name="action" value="topic">
	<button type="submit">Topic</button>
	</form>


   <c:if test="${action=='profile'}">
		<form method="post">
       	 <label for="userName"><h4>${userName}</h4></label>
        
        <label for="OldPass">Old password:</label>
        <input id="OldPass" type="password" name="OldPassword" placeholder="old password ...">
        
         <label for="newPass">New password:</label>
         <input id="newPass" type="password" name="newPassword" 
         		pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,}$" 
           		oninvalid="alert('Password is not valid')"  placeholder="new password ...">
       	 <p>* Password must contain minimum 8 characters, at least 1 Uppercase letter, 1 Lowercase letter, 1 Number and 1 Special Character (!@#$%^&*_=+-)</p>
        
         <label for="newPassCheck">Password for check:</label>
         <input id="newPassCheck"  type="password" name="newPasswordCheck" placeholder="password again ...">
       
       <input type="hidden" name="action" value="changePass">
        <button type="submit">Change password </button>
    </form>
  </c:if>   
 
    
    
    
  
  <c:if test="${action=='topic'}">
   <form>
     <table>
        <tr>
            <th>Name of topic</th>
        </tr>
        
		<c:forEach var="topic" items="${Topic}">
			<tr>
				<td>
					<input type="checkbox" name="topic" value="	${Topic.topicId}"> ${Topic.topic}
				</td>
			</tr>
		</c:forEach>
    </table>   
    	<input type="hidden" name="action" value="changeTopics">
     	  <button type="submit">Change topics </button>
	</form>
  </c:if>


</c:if>   

    
     