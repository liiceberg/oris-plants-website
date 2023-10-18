<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <link href="https://cdn.jsdelivr.net/npm/fastbootstrap@1.1.2/dist/css/fastbootstrap.min.css" rel="stylesheet"
          integrity="sha256-xLGBU65wCDv2/qEdq3ZYw2Qdiia/wxxeGepRyZmpQdY=" crossorigin="anonymous">

    <nav class="navbar navbar-expand-lg">
        <p class="navbar-brand" >
            <img src="/img/icon.png" alt="Logo" class="d-inline-block align-text-bottom" width="36"/>
            Houseplant Adviser
        </p>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav navbar-nav-underline">
                <li class="nav-item">
                    <a href="/main" class="nav-link">Main</a>
                </li>
                <li class="nav-item">
                    <a href="/help" class="nav-link">Help</a>
                </li>
                <li class="nav-item">
                    <a href="/menu/favourites.ftl" class="nav-link">Favourites</a>
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
</head>

<body>

<div class="content container">
    <div class="content"><@content></@content></div>
</div>
<script src="https://cdn.jsdelivr.net/npm/fastbootstrap@1.1.2/dist/js/fastbootstrap.min.js"
        integrity="sha256-+c+/OCMmtlZadi89VaV1yUOkk1T4BD2pwBFpY3OcrqI=" crossorigin="anonymous"></script>
</body>

</html>