<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="pl">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add Undead</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<jsp:include page="/layout"/>

<div class="container">



    <form method="POST" action="${contextPath}/saveUndead" class="form-signin">
        <h2 class="form-heading">Add Undead</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="Name" type="text" class="form-control" placeholder="Name"
                   autofocus="true"/>
            <input name="Ability" type="text" class="form-control" placeholder="Ability"/>

            <input name="Strength" type="number" class="form-control" placeholder="Strength"/>

            <input name="Health" type="number" class="form-control" placeholder="Health"/>


            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Create Undead</button>

        </div>

    </form>
    <div class="container">
        <table class="table table-striped">
            <thead>
            <th>Name</th>
            <th>Strength</th>
            <th>Health</th>
            <th>Ability</th>
            <th>Author</th>
            <th>Edit</th>
            <th>Delete</th>
            </thead>

            <c:forEach items="${undeadList}" var="undead">

                <tr>
                    <td><c:out value="${undead.name}"/></td>
                    <td><c:out value="${undead.strength}"/></td>
                    <td><c:out value="${undead.health}"/></td>
                    <td><c:out value="${undead.ability}"/></td>
                    <td><c:out value="${undead.userName}"/></td>
                    <td><a href="/edit">Edit</a></td>
                    <td><a href="<c:url value='/delete/${undead.id}' />" >Delete</a></td>

                </tr>
            </c:forEach>
        </table>

    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
