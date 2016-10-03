<%@tag description="Error template" pageEncoding="UTF-8"%>
<%@attribute name="inner" fragment="true" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div id="error" class="container">
    <jsp:invoke fragment="inner"/>
</div>
