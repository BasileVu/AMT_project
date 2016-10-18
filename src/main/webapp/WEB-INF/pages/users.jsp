<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:container>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/dataTables.bootstrap.css">
    </jsp:attribute>

    <jsp:attribute name="inner">
        <h3>Users registered</h3>
        <div class="wrapper">

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="users" class="table table-bordered table-striped" style="background-color: white">
                                <thead>
                                <tr>
                                    <th>Username</th>
                                    <th>Id</th>
                                    <th>Quote</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Username</th>
                                    <th>Id</th>
                                    <th>Quote</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </section>
                <!-- /.content -->
            </div>
        </div>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="${pageContext.request.contextPath}/static/js/jquery-2.2.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
        <script>
            $(function () {
                $('#users').DataTable({
                    ajax: {
                        url: "${pageContext.request.contextPath}/api/users",
                        dataSrc: ""
                    },
                    columns: [
                        {data: "id"},
                        {data: "username"},
                        {data: "quote"}
                    ]
                });
            });
        </script>
    </jsp:attribute>
</t:container>
