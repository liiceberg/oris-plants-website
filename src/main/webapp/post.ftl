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
    <div class="content container mt-3" style="width: 680px; margin: 0 auto">

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

        <form action="/post/">
            <div class="mb-3">
                <label for="text" class="form-label text-muted">Leave your comment</label>
                <textarea id="text" class="form-control"></textarea>
            </div>
            <button id="save-button" class="btn btn-primary">Send</button>
        </form>

        <br>

        <#if comments??>
            <div id="comments-container">
                <#list comments as comment>
                    <div class="d-flex justify-content-between">
                        <div class="d-flex align-items-center">
                            <img src="${comment.author.img}" class="avatar avatar-lg fs-5" alt="ava" width="36px"
                                 style="border-radius: 18px"/>
                            <h6 class="text-start">${comment.author}</h6>
                        </div>
<#--                        <#if comment.author>-->
                            <button class="btn btn-subtle text-end" type="button">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                </svg>
                            </button>
<#--                        </#if>-->
                    </div>
                    <p class="text-start">${comment.text}</p>
                    <span class="text-end text-muted">${comment.dateTime}</span>
                    <hr class="border border-secondary border-1 opacity-30">
                    <br>
                </#list>
            </div>
        </#if>

    </div>
</#macro>

</html>