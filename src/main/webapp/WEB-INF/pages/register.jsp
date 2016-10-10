<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:container>
    <jsp:attribute name="inner">
        <h1>Register</h1>
        <form method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" class="form-control" type="text" placeholder="Username" name="username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" class="form-control" type="password" placeholder="Password" name="password">
            </div>

            <div class="form-group">
                <label for="password-confirmation">Confirm password</label>
                <input id="password-confirmation" class="form-control" type="password"
                       placeholder="Confirm password" name="password-confirmation">
            </div>

            <c:if test="${not empty error}">
                <t:error>
                    <jsp:attribute name="inner">
                        ${error}
                    </jsp:attribute>
                </t:error>
            </c:if>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </jsp:attribute>
</t:container>