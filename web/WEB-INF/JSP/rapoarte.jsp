<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 21-Jul-20
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rapoarte</title>
    <link rel="stylesheet" type="text/css" href="css/rapoarte.css">
    <link rel="stylesheet" type="text/css" href="css/rapoartePrint.css" media="print">
    <script type="text/javascript" src="javascript/lib/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="javascript/reports.js"></script>
    <script type="text/javascript" src="javascript/footer.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="mainContainer">
    <div class="box">
        <h1>Lista tranzactii conturi:</h1>
        <form action="rapoarte.do" method="post">
            <div class="verticalForm">
                <label>Sursa</label>
                <select name="accountId">
                    <option value="">Alegeti contul</option>
                    <c:forEach var='account' items='${accounts}'>
                        <option value="<c:out value='${account.id}'/>"> <c:out value='${account.accountNumber}'/></option>
                    </c:forEach>
                </select>
            </div>
            <div class="verticalForm">
                <label>Start Date:</label>
                <input type="date" name="startDate" value="<c:out value="${param.startDate}"/>">
            </div>
            <div class="verticalForm">
                <label>End Date:</label>
                <input type="date" name="endDate" value="<c:out value="${param.endDate}"/>">
            </div>
            <input type="submit" value="Afisare">
        </form>
        <table class="toPrint">
            <tr>
                <th>Data</th>
                <th>Cont Debitor/Creditor</th>
                <th>Suma Debit</th>
                <th>Suma Credit</th>
                <th>Sold curent</th>
            </tr>
            <c:forEach var='transaction' items='${requestScope.transactions}'>
                <tr>
                    <td><c:out value='${transaction.transactionDate}'/></td>
                    <td>Cont <c:out value='${transaction.accountId}'/></td>
                    <td><c:out value='${transaction.debitSum}'/></td>
                    <td><c:out value='${transaction.creditSum}'/></td>
                    <td><c:out value='${transaction.currentSum}'/></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <p>${message}</p>
        <br>
        <c:if test="${!empty requestScope.transactions}">
            <div class="buttons">
                <form action="textReportServlet.do" method="post">
                    <input type="submit" value="Raport TXT">
                </form>
                <form>
                    <input type="button" value="Raport HTML" id="htmlReport">
                </form>
                <form action="pdfReportServlet.do" method="post">
                    <input type="submit" value="Raport PDF">
                </form>
            </div>
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>