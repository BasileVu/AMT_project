<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="container">
        <h1>Register</h1>
        <form method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password">
            </div>

            <div class="form-group">
                <label for="password-confirmation">Confirm password</label>
                <input type="password" class="form-control" id="password-confirmation" placeholder="Confirm password">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </jsp:attribute>
</t:base>