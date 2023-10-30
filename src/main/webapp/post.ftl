<html lang="en">

<#include "basic/base.ftl">

<#macro title>Post</#macro>

<#macro scripts>
    <script>
        $(document).ready(function () {
            let feedback = null
            $("#save-button").click(function () {
                let text = $("#text").val()

                $.post("/post?id=" + ${post.id}, {
                    "text": text,
                    "feedback": feedback
                }, function (response) {
                    if (response.hasOwnProperty("exc")) {
                        alert(response.exc)
                    }
                    let str = "<div " + "id=\"cmt-" + response.id + "\">\n" +
                        "<div class=\"d-flex justify-content-between\">\n" +
                        "                        <div class=\"d-flex align-items-center\">\n" +
                        "<a href=\"/view/" + response.author.name + " " + response.author.lastname + "?login=" + response.author.login + "\">\n" +
                        "                       <img src=\"" + response.author.img + "\" class=\"avatar avatar-lg fs-5\" alt=\"ava\" width=\"36px\"\n" +
                        "                                 style=\"border-radius: 18px\"/>\n" + "</a>" +
                        "                            <h6 class=\"text-start ms-3\">" + response.author.name + " " + response.author.lastname + "</h6>\n" +
                        "                        </div>\n" +
                        "                                                <div class=\"d-flex align-items-center\">\n" +
                        "<button type=\"button\" class=\"btn btn-link reply-btn\">Reply</button>\n" +
                        "<button class=\"btn btn-link text-end delete-comment\" type=\"button\" value=\"" + response.id + "\">\n" +
                        "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\"\n" +
                        "class=\"bi bi-trash3\" viewBox=\"0 0 16 16\">\n" +
                        "<path d=\"M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z\"/>\n" +
                        "</svg>\n" +
                        "</button>\n" +
                        "</div>\n" +
                        "                    </div>\n" +
                        "                    <p class=\"text-start mt-3\">\n"
                    if (response.feedbackCommentId !== undefined) {
                        str += "<button type=\"button\" class=\"btn btn-link feedback\" id=\"up-btn\" value=\"" + response.feedbackCommentId + "\">\n" +
                            "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-arrow-90deg-down\" viewBox=\"0 0 16 16\">\n" +
                            "<path fill-rule=\"evenodd\" d=\"M4.854 14.854a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V3.5A2.5 2.5 0 0 1 6.5 1h8a.5.5 0 0 1 0 1h-8A1.5 1.5 0 0 0 5 3.5v9.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4z\"/>\n" +
                            "</svg>\n" +
                            "</button>\n"
                    }
                    str += response.text + "</p>\n" +
                        "                    <span class=\"text-end text-muted\">" + response.dateTime + "</span>\n" +
                        "                    <hr class=\"border border-secondary border-1 opacity-30\">\n" +
                        "                    <br>"
                    $("#comments-container").prepend(str)
                    $("#text").val("")
                    feedback = null

                    // function removeAllListeners(element) {
                    //     var clone = element.cloneNode(true)
                    //     element.parentNode.replaceChild(clone, element)
                    // }
                    //
                    // let textarea = document.getElementById("text")
                    // removeAllListeners(textarea)
                })
            })

            $(".reply-btn").click(function () {

                feedback = $(this).val()
                let author = $(this).attr("author") + ", "
                let textarea = document.getElementById("text")
                textarea.value = ""
                textarea.addEventListener("focus", function () {
                    if (textarea.value.length === 0) {
                        textarea.value = author;
                        textarea.setSelectionRange(textarea.value.length, textarea.value.length);
                    }
                })


                $("#text").on("input", function () {
                    if (String($(this).val()).indexOf(author) == -1) {
                        $(this).val(author)
                    }
                })
                $("#text").focus()
            })


            $(".delete-comment").click(function () {
                let id = $(this).val()
                $.get("/comment?id=" + id,
                    function () {
                        $("#cmt-" + id).remove()
                    }
                )
            })

            $(".feedback").click(function () {
                let id = "cmt-" + $(this).val()
                let c = document.getElementById(id)
                c.scrollIntoView()
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

        <form>
            <div class="mb-3">
                <label for="text" class="form-label text-muted">Leave your comment</label>
                <textarea id="text" class="form-control"></textarea>
            </div>
            <button type="button" id="save-button" class="btn btn-primary">Send</button>
        </form>

        <br>

        <#if comments??>
            <div id="comments-container">
                <#list comments as comment>
                    <div id="cmt-${comment.id}">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex align-items-center">
                                <a href="/view/${post.author}?login=${post.author.login}">
                                    <img src="${comment.author.img}" class="avatar avatar-lg fs-5" alt="ava"
                                         width="36px"
                                         style="border-radius: 18px"/>
                                </a>
                                <h6 class="text-start ms-3">${comment.author}</h6>
                            </div>

                            <div class="d-flex align-items-center">
                                <button type="button" class="btn btn-link reply-btn" value="${comment.id}"
                                        author="${comment.author}">
                                    Reply
                                </button>
                                <#if Session.user_id?number == comment.author.id>
                                    <button class="btn btn-link text-end delete-comment" type="button"
                                            value="${comment.id}">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor"
                                             class="bi bi-trash3" viewBox="0 0 16 16">
                                            <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                        </svg>
                                    </button>
                                </#if>
                            </div>

                        </div>
                        <p class="text-start mt-3">
                            <#if comment.feedbackCommentId??>
                                <button type="button" class="btn btn-link feedback" id="up-btn"
                                        value="${comment.feedbackCommentId}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-arrow-90deg-down" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M4.854 14.854a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V3.5A2.5 2.5 0 0 1 6.5 1h8a.5.5 0 0 1 0 1h-8A1.5 1.5 0 0 0 5 3.5v9.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4z"/>
                                    </svg>
                                </button>
                            </#if>
                            ${comment.text}
                        </p>
                        <span class="text-end text-muted">${comment.dateTime}</span>
                        <hr class="border border-secondary border-1 opacity-30">
                        <br>
                    </div>
                </#list>
            </div>
        </#if>

    </div>
</#macro>

</html>