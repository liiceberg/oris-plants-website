<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>My posts</#macro>

<#macro scripts></#macro>

<#macro content>
    <br>
    <div class="text-center">
        <a href="/add" class="btn btn-primary">Add new post</a>
    </div>
    <br>

    <#list posts as post>
        <div class="card" style="width: 680px; margin: 0 auto">
            <div class="card-header d-flex justify-content-between">
                <div class="d-flex align-items-center">
                    <img src="${post.author.img}" class="avatar avatar-lg fs-5" alt="ava" width="36px"
                         style="border-radius: 18px"/>
                    <div class="ms-3">
                        <h6 class="mb-0 fs-sm">${post.author}</h6>
                        <span class="text-muted fs-sm">${post.dateTime}</span>
                    </div>
                </div>
                <button class="btn btn-subtle text-end" type="button">
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
                <a class="btn btn-link p-0 me-auto fw-bold" href="/post/${post.id}" style="text-decoration: none">Read
                    more</a>
            </div>
        </div>
        <br>
    <#else>
        Your posts will be here
    </#list>

</#macro>

</html>