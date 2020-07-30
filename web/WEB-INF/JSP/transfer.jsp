<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 19-Jul-20
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Transfer</title>
    <link rel="stylesheet" type="text/css" href="css/transfer.css">
</head>
<body>
<%@ include file="header.jsp"%>
<script type="text/javascript" src="javascript/transfer.js"></script>
<div class="mainContainer">
    <div class="box">
        <h1>Transfer</h1>
        <form action="transfer.do" method="post">
            <div class="horizontalForm">
                <label for="sourceAccountId">Sursa</label>
                <select name="sourceAccountId" id="sourceAccountId">
                    <option value="">Alegeti contul</option>
                </select>
                <input type="text" name="sourceAccountSum" id="accountSum" placeholder="Suma cont" readonly>
            </div>
            <div class="horizontalForm">
                <label>Destinatie</label>
                <select name="selectDestinationClient" id="selectDestinationClient">
                    <option value="">Alegeti clientul</option>
                    <c:forEach var='cl' items='${clients}'>
                        <option value="<c:out value='${cl.id}'/>"> <c:out value='${cl.lastName}'/> <c:out value='${cl.firstName}'/></option>
                    </c:forEach>
                </select>
                <select name="destinationAccountId" id="destinationAccountId">
                    <option value="">Alegeti contul</option>
                </select>
            </div>
            <div class="horizontalForm">
                <label>Suma transferata</label>
                <input type="text" name="transferSum" placeholder="Suma transfer" value="<c:out value="${param.transferSum}"/>">
            </div>
            <input type="submit" value="Transfera">
            <p class="mesaj">${message}</p>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
