<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <nav class="container navbar navbar-expand-lg">
        <p class="navbar-brand">
            <img src="/img/icon.png" alt="Logo" class="d-inline-block align-text-bottom" width="36"/>
            Houseplant Adviser
        </p>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav navbar-nav-underline">
                <li class="nav-item">
                    <a href="/main" class="nav-link">Main</a>
                </li>
                <li class="nav-item">
                    <a href="/favourites" class="nav-link">Favourites</a>
                </li>
                <li class="nav-item">
                    <a href="/help" class="nav-link">Help</a>
                </li>
                <li class="nav-item">
                    <a href="/myposts" class="nav-link">My Posts</a>
                </li>
                <li class="nav-item">
                    <a href="/profile" class="nav-link">Profile</a>
                </li>
            </ul>
            <div class="d-flex align-items-center ms-auto">
                <a class="btn btn-default px-3 me-2" href="/login">
                    Sign in
                </a>
                <a class="btn btn-primary me-3" href="/signup">
                    Sign up
                </a>
            </div>
        </div>
    </nav>
    <@scripts></@scripts>
</head>

<body>

<div class="content container">
    <@content></@content>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>

</html>