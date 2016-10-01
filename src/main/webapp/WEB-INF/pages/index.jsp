<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:base>
    <jsp:attribute name="head">
        <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="container">
        <header id="top" class="header">
            <div class="text-vertical-center">
                <h1>AMT - Project</h1>
                <h3>Welcome to our app !</h3>
                <br>
                <a href="https://github.com/BenjaminSchubert/AMT-LAB01" class="btn btn-dark btn-lg">Find Out More</a>
            </div>
        </header>
    </jsp:attribute>
</t:base>