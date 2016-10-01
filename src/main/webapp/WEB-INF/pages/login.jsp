<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="head">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="container">
        <h1>Login</h1>
        <form method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <div id="register">
            <a href="${pageContext.request.contextPath}/register">No account yet ? Register.</a>
        </div>
    </jsp:attribute>
</t:base>