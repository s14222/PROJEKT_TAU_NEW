<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Undead</a>
        </div>
        <ul class="nav navbar-nav">

            <c:if test="${!isLoggedIn}">
                <li><a id="loginId" href="/login">Login</a></li>
                <li><a id="registerId" href="/registration">Register</a></li>
            </c:if>
            <c:if test="${isLoggedIn}">
                <li><a id="addUndeadId" href="/addUndead">Add Undead</a></li>
                <li><a id="logoutId" onclick="document.forms['logoutForm'].submit()">Logout</a></li>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <!-- wylogowanie usera -->
                        <form id="logoutForm" method="POST" action="${contextPath}/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </c:if>
            </c:if>
        </ul>
    </div>
</nav>

</body>
</html>
