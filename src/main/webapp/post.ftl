<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Post</title>
    <link href="https://cdn.jsdelivr.net/npm/fastbootstrap@1.1.2/dist/css/fastbootstrap.min.css" rel="stylesheet"
          integrity="sha256-xLGBU65wCDv2/qEdq3ZYw2Qdiia/wxxeGepRyZmpQdY=" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#save-button", function () {
            let text = $("#text").val()

            $.post("/post/", {
                "text": text,
                "post_id": post.id,
                "feedback": -1
            }, function (response) {
                alert(response)
            })
        })
    </script>
</head>

<body>
<div class="content container">

    <div class="card">
        <div class="card-footer text-muted">
            <span class="text-start">${post.author}</span>
            <span class="text-end">${post.dateTime}</span>
        </div>
        <h5 class="card-header">${post.title}</h5>
        <div class="card-body">
            <#if post.text??><p class="card-text">${post.text}</p></#if>
        </div>
        <#if post.img??><img src="${post.img}" class="card-img-top"/></#if>
    </div>
    <br>
    <div class="card">
        <form action="/post/">
            <div class="mb-3">
                <label for="text" class="form-label">Leave your comment</label>
                <textarea id="text" class="form-control"></textarea>
            </div>
            <button id="save-button" class="btn btn-primary">Send</button>
        </form>
    </div>

    <#if comments??>
        <#list comments as comment>
            <div class="card">
                ${comment.author} <br>
                ${comment.dateTime} <br>
                ${comment.text} <br>
            </div>
        </#list>
    </#if>

</div>
</body>

</html>