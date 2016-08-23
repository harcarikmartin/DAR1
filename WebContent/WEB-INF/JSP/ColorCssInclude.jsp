<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${color == null || color == 'orange'}">
	<style type="text/css">
		<%@include file="css/Orange.css"%>
	</style>	
</c:if>	
<c:if test="${color == 'blue'}">
	<style type="text/css">
		<%@include file="css/Blue.css"%>
	</style>
</c:if>
<c:if test="${color == 'green'}">
	<style type="text/css">
		<%@include file="css/Green.css"%>
	</style>
</c:if>