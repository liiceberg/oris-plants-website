<#--noinspection ALL-->
<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>My posts</#macro>

<#macro scripts>
    <script>
        $(document).ready(function () {
            let id
            $(".delete-post").click(function () {
                id = $(this).val()
            })

            $("#continue-btn").click( function () {
                $.post("/myposts", {
                        "id": id
                    },
                    function () {
                        $("#" + id).remove()
                    }
                )

            })
        })
    </script>
</#macro>

<#macro content>
    <br>
    <div class="text-center">
        <a href="/add" class="btn btn-primary">Add new post</a>
    </div>
    <br>

    <#list posts as post>
        <div class="card" style="width: 680px; margin: 0 auto" id="${post.id}">
            <div class="card-header d-flex justify-content-between">
                <div class="d-flex align-items-center">
                    <img src="${post.author.img}" class="avatar avatar-lg fs-5" alt="ava" width="36px"
                         style="border-radius: 18px"/>
                    <div class="ms-3">
                        <h6 class="mb-0 fs-sm">${post.author}</h6>
                        <span class="text-muted fs-sm">${post.dateTime}</span>
                    </div>
                </div>
                <button class="btn btn-subtle text-end delete-post" type="button" value="${post.id}" data-bs-toggle="modal" data-bs-target="#modal">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-x-lg" viewBox="0 0 16 16">
                        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                    </svg>
                </button>
            </div>

            <div class="card-body">
                <p class="card-text">${post.title}</p>
                <#if post.img??><img class="card-img-top" src="${post.img}"
                                     style="width: 400px; margin: 0 auto; display: block"/></#if>
            </div>
            <div class="card-footer d-flex">
                <a class="btn btn-link p-0 me-auto fw-bold" href="/post?id=${post.id}" style="text-decoration: none">Read
                    more</a>
            </div>
        </div>
        <br>
    <#else>
        Your posts will be here
    </#list>

</#macro>

<div class="modal" tabindex="-1" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Warning</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>The deleted post cannot be restored.</p>
            </div>
            <div class="modal-footer">
                <button type="button" data-bs-dismiss="modal" class="btn btn-secondary" id="cancel-btn">Cancel</button>
                <button type="button" data-bs-dismiss="modal" class="btn btn-primary" id="continue-btn">Continue</button>
            </div>
        </div>
    </div>
</div>

</html>