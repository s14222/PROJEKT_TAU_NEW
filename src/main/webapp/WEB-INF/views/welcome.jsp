<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<jsp:include page="/layout"/>
<div class="container">

        <table id="tableListUndead" class="table table-striped">
            <thead>
            <th>Name</th>
            <th>Strength</th>
            <th>Health</th>
            <th>Ability</th>
            <th>Author</th>
            </thead>

            <c:forEach items="${undeadList}" var="undead">

                <tr>
                    <td><c:out value="${undead.name}"/></td>
                    <td><c:out value="${undead.strength}"/></td>
                    <td><c:out value="${undead.health}"/></td>
                    <td><c:out value="${undead.ability}"/></td>
                    <td><c:out value="${undead.userName}"/></td>
                </tr>
            </c:forEach>
        </table>

    </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
