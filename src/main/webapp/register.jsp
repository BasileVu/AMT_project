<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>AMT project</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">AMT project</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="container" class="container">
        <h1>Register</h1>
        <form method="POST">
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
    </div>
</body>
</html>
