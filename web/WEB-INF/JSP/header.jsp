<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 16-Jul-20
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="css/dropDown.css">
<script type="text/javascript" src="javascript/lib/jquery-3.5.1.js"></script>
<script type="text/javascript" src="javascript/dropDown.js"></script>

<div class="headerHorizontalFlex">
    <p id="greeting">
        Hello,
        <c:out value='${client.lastName} ${client.firstName}!'/>
    </p>
    <p>Date</p>
    <p>Time</p>
    <p>/<a href="logout.do">LogOut</a></p>
</div>
<h1 class="appTitle">eBanking</h1>
<div class="menuDiv">
    <nav>
        <ul>
            <li class="dropdown">
                <a href="#">CONTURI</a>
                <ul class="dropdownContent displayNone">
                    <li><a href="administrareCont.do">Administrare account</a></li>
                    <li><a href="vizualizareConturi.do">Vizualizare conturi</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#">OPERATII</a>
                <ul class="dropdownContent displayNone">
                    <li><a href="alimentareCont.do">Alimentare Cont</a></li>
                    <li><a href="transfer.do">Transfer</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#">RAPOARTE</a>
                <ul class="dropdownContent displayNone">
                    <li><a href="rapoarte.do">Extras cont</a></li>
                </ul>
            </li>
            <li><a href="aprobare.do">APROBARE</a></li>
        </ul>
    </nav>
</div>
