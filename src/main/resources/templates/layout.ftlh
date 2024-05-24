<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <meta name="_csrf_token" content="${_csrf.token}">
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
    <nav class="navbar navbar-expand-lg bg-body-tertiary mb-5" data-bs-theme="dark">
        <div class="container">
            <a class="navbar-brand" href="/">Movie Review</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav flex-fill">
                    <li class="nav-item">
                        <a class="nav-link" href="/add"><@spring.message 'layout.create.new.movie'/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/api/movies"><@spring.message 'layout.java.script.movies'/></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message 'layout.dropdown'/>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                            <li><a class="dropdown-item" href="?lang=ru"><@spring.message 'layout.ru'/></a></li>
                            <li><a class="dropdown-item" href="?lang=en"><@spring.message 'layout.en'/></a></li>
                        </ul>
                    </li>
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
                   class="text-light m-2 m-sm-0"><@spring.message 'layout.hello'/> ${name}:
                    <#list user.authorities as auth>
                        <span class="text-danger">${auth}</span>
                        <#if auth?has_next>
                            /
                        </#if>
                    </#list>
                </a>

                <#if _csrf??>
                    <form action="/logout" method="post">
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}">
                        <button type="submit"
                                class="btn btn-outline-info m-2"><@spring.message 'layout.logout'/></button>
                    </form>
                <#else>
                    <a href="/logout" class="btn btn-outline-info m-2"
                       id="logout"><@spring.message 'layout.logout'/></a>
                </#if>

            <#else>
                <a href="/auth/login"
                   class="btn btn-outline-info m-2"><@spring.message 'layout.login'/></a>
                <a href="/auth/register"
                   class="btn btn-outline-info m-2"><@spring.message 'layout.register'/></a>
            </#if>
        </div>
    </nav>

    <div class="container">
        <#nested spring>
    </div>

    </body>
    </html>

</#macro>