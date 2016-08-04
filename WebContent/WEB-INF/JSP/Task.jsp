<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


  <table>
    <c:forEach items="${Task}" var="task">
        <tr>
        	 <td><a href="?action=openTask&idTask=${Task.taskID}">${Task.task}</a></td>
        	 <td>${Task.user.userName}</td>
        </tr>
       </c:forEach>
    </table>
   