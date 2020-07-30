<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 17-Jul-20
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Alimentare Cont</title>
    <link rel="stylesheet" type="text/css" href="css/alimentareCont.css">
</head>
<body>
<%@ include file="header.jsp"%>
<script src="javascript/accounts.js"></script>
<div class="mainContainer">
    <div class="box">
        <h1>Alimentare Cont</h1>
        <form action="alimentareCont.do" method="post">
            <div class="horizontalForm">
                <label for="selectAccount">Sursa</label>
                <select name="accountId" id="selectAccount">
                    <option value="">Alegeti contul</option>
                </select>
                <input type="text" id="accountSum" placeholder="Suma cont" readonly>
            </div>
            <input type="text" name="depositSum" placeholder="Suma alimentare" value="<c:out value="${param.depositSum}"/>">
            <input type="submit" value="Transfera">
            <p id="mesaj">${message}</p>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
