<html lang="en">

<#include "basic/base.ftl">

<#macro title>Post</#macro>

<#macro scripts>
    <script>
        $(document).on("click", "#save-button", function () {
            let text = $("#text").val()

            $.post("/post/", {
                "text": text,
                "feedback": -1
            }, function (response) {
                // alert(response)
            })
        })
    </script>
</#macro>

<#macro content>
    <div class="content container">

        <div class="card">
            <div class="card-header text-muted d-flex justify-content-between">
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
            <div id="comments-container">
                <#list comments as comment>
                    <div class="card">
                        ${comment.author} <br>
                        ${comment.dateTime} <br>
                        ${comment.text} <br>
                    </div>
                </#list>
            </div>
        </#if>

    </div>
</#macro>

</html>