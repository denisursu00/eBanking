<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 25-Jul-20
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Aprobare</title>
    <link rel="stylesheet" type="text/css" href="css/aprobare.css">
    <script type="text/javascript" src="javascript/lib/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="javascript/aprobare.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="mainContainer">
    <div class="box">
        <h1>Lista conturilor de aprobat:</h1>
        <form action="aprobare.do" method="post">
            <table>
                <tr>
                    <th>Client</th>
                    <th>Cont</th>
                    <th>Aprobare</th>
                    <th>Respingere</th>
                </tr>
                <c:forEach var='account' items='${pendingAccounts}'>
                    <tr>
                        <td><c:out value='${account.clientName}'/></td>
                        <td>Cont <c:out value='${account.accountNumber}'/></td>
                        <td>
                            <input type="checkbox" name="<c:out value='${account.id}'/>" value="1" checked>
                        </td>
                        <td>
                            <input type="checkbox" name="<c:out value='${account.id}'/>" value="3">
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <input type="button" value="Aprobare">
            <br>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
