<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ch.heigvd.amt.amtproject.util.FieldLength" %>

<t:container>
    <jsp:attribute name="inner">
        <h1>Welcome, ${sessionScope.username}!</h1>
        <h3>Your quote</h3>
        <c:choose>
            <c:when test="${not empty error}">
                <div class="alert alert-danger">
                    ${error}
                </div>
            </c:when>
            <c:otherwise>
                <form method="POST">
                    <div class="form-group">
                        <textarea class="form-control" rows="5" name="quote"
                                  maxlength="${FieldLength.QUOTE_MAX_LENGTH}">${requestScope.quote}</textarea>
                    </div>

                    <c:if test="${not empty info}">
                        <div class="alert alert-info">
                            ${info}
                        </div>
                    </c:if>
                    
                    <div class="form-group">
                        <button class="btn btn-primary pull-right" type="submit">Save</button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</t:container>
