<%@tag description="Container template" pageEncoding="UTF-8"%>
<%@attribute name="inner" fragment="true" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="head">
        <link href="${pageContext.request.contextPath}/css/container.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="container">
        <div id="container" class="container">
            <jsp:invoke fragment="inner"/>
        </div>
    </jsp:attribute>
</t:base>
