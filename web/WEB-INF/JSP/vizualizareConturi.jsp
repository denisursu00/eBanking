<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 16-Jul-20
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Vizualizare Conturi</title>
    <link rel="stylesheet" type="text/css" href="css/vizualizareConturi.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="mainContainer">
    <div class="box">
        <h1>Vizualizare Conturi</h1>
        <p>STARE CONTURI:</p>
        <c:choose>
            <c:when test="${empty accounts}">
                <p>Nu aveti conturi!</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Numar Cont</th>
                        <th>Stare</th>
                        <th>Suma</th>
                    </tr>
                    <c:forEach var='account' items='${accounts}'>
                        <tr>
                            <td><c:out value='${account.accountNumber}'/></td>
                            <td><c:out value='${account.state}'/></td>
                            <td><c:out value='${account.sum}'/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>