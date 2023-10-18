<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
    <link href="https://cdn.jsdelivr.net/npm/fastbootstrap@1.1.2/dist/css/fastbootstrap.min.css" rel="stylesheet" integrity="sha256-xLGBU65wCDv2/qEdq3ZYw2Qdiia/wxxeGepRyZmpQdY=" crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <h3 class="text-center">Ooops, something went wrong</h3>
        <strong>Status code:</strong> ${status_code} <br>
        <strong>Request URI:</strong> ${uri} <br>
        <#if message??><strong>Message:</strong> ${message} <br></#if>
        <a class="btn btn-primary" href="/main">Return to main page</a>
        <img class="blankslate-bottom-img" src="img/cat.jpg" />
    </div>
</body>


</html>