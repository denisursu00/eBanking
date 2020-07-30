<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script type="text/javascript" src="javascript/lib/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="javascript/testEditAccount.js"></script>
		
		<link rel="stylesheet" type="text/css" href="css/testEditAccount.css">
		
	</head>
	<body>
	
		<h1>Modificare account</h1>
	
		<c:if test="${!empty message}">
			<div id="message">
				<c:out value="${message}" />
			</div>
		</c:if>
	
		<form id="editAccountForm" action="testEditAccount.do" method="post">
		
			<input type="hidden" name="id" value="<c:out value="${editForm.idAsString}" />">
			
			<table>
				<tr>
					<td>
						<label>Nume</label>
					</td>
					<td>
						<input type="text" name="name" value="<c:out value="${editForm.nameAsString}" />">
					</td>
				</tr>
				<tr>
					<td>
						<label>Suma</label>
					</td>
					<td>
						<input type="text" name="sum" value="<c:out value="${editForm.sumAsString}" />">
						<input type="button" id="increaseSumButton" value=" + ">
					</td>
				</tr>
			</table>
			
			<input type="submit" id="submitButton" value="Salveaza">
			
		</form>
	</body>
</html>