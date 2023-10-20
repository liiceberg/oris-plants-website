<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <@script></@script>
</head>

<body>
<div id="header" class="d-flex align-items-center" style="margin: 0 auto; width: 300px">
    <img src="/img/icon.png" alt="Logo" width="36" class="p-0 me-auto fw-bold"/>
    <h3 class="text-center mt-3"> Houseplant Adviser </h3>
</div>
<hr class="border border-primary border-2 opacity-50">

<div class="content container">
    <@content></@content>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>