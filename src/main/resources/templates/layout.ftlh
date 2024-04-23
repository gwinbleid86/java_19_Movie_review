<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Movie Review</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
                crossorigin="anonymous"></script>

    </head>
    <body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div class="container">
            <a class="navbar-brand" href="/">Movie Review</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav flex-fill">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pricing</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                    </li>
                    <#--                    <li class="nav-item ms-auto">-->
                    <#--                        <a href="/auth/login" class="nav-link active">Log In</a>-->
                    <#--                    </li>-->
                </ul>
            </div>
            <#assign
            known = SPRING_SECURITY_CONTEXT??
            >
            <#if known>
                <#assign
                user = SPRING_SECURITY_CONTEXT.authentication.principal
                name = user.getUsername()
                >
                <a href="/profile/${name}"
                   class="text-light m-2 m-sm-0">Hello ${name}</a>


                <a href="/logout" class="btn btn-outline-info m-2"
                   id="logout">Logout</a>

            <#else>
                <a href="/auth/login"
                   class="btn btn-outline-info m-2">Login</a>
                <a href="/auth/register"
                   class="btn btn-outline-info m-2">Register</a>
            </#if>
        </div>
    </nav>

    <div class="container">
        <#nested >
    </div>

    </body>
    </html>

</#macro>