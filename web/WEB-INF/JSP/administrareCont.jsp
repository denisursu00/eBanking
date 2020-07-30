<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 15-Jul-20
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Administrare Cont</title>
    <link rel="stylesheet" type="text/css" href="css/administrareCont.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="mainContainer">
        <div class="box">
            <h1>Administrare Cont</h1>
            <div class="content">
                <div class="rowFlex">
                    <p>Solicitare creare account nou!</p>
                    <button onclick="location.href='main.do'">Renunta</button>
                </div>
                <div class="conturi">
                    <h4>LISTA CONTURI ACTIVE:</h4>
                    <c:choose>
                        <c:when test="${empty accounts}">
                            <p>Nu aveti conturi active!</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var='account' items='${accounts}'>
                                <p>Cont <c:out value=' ${account.accountNumber}'/></p>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                <form action="administrareCont.do" method="post">
                    <div class="formInput">
                        <label for="accountNumber">Cont Nou:</label>
                        <input type="text" name="accountNumber" id="accountNumber">
                    </div>

                    <input type="submit" value="Trimite solicitare">
                </form>
                <p class="mesaj">${message}</p>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp"%>
</body>
</html>
