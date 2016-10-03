<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:container>
    <jsp:attribute name="inner">
        <h1>Welcome, ${sessionScope.username}!</h1>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </jsp:attribute>
</t:container>