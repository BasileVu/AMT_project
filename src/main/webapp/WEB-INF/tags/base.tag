<%@tag description="Overall page template" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="container" fragment="true" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>AMT project</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <jsp:invoke fragment="head"/>
</head>

<body>

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">AMT project</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <jsp:invoke fragment="container"/>
</body>
</html>
