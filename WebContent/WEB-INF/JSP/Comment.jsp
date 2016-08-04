<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<table id="printComments" >
	<c:forEach items="${Comments}" var="Comments">
		<tr>
			<td>${Comments.user.userName}
			<td>${Comments.comment}
		</tr>
	</c:forEach>
</table>
