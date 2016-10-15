<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:container>
    <jsp:attribute name="inner">
        <h1>Welcome, ${sessionScope.username}!</h1>
        <h3>Your quote</h3>
        <c:choose>
            <c:when test="${not empty error}">
                <t:error>
                    <jsp:attribute name="inner">
                        ${error}
                    </jsp:attribute>
                </t:error>
            </c:when>
            <c:otherwise>
                <form method="POST">
                    <div class="form-group">
                        <textarea class="form-control" rows="5" name="quote">${requestScope.quote}</textarea>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary pull-right" type="submit">Save</button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</t:container>
