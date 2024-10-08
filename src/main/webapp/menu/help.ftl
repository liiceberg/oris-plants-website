<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Help</#macro>

<#macro scripts></#macro>

<#macro content>
    <#list posts as post>
        <div class="card" style="width: 680px; margin: 0 auto">
            <div class="card-header d-flex align-items-center">
                <a href="/view/${post.author}?login=${post.author.login}">
                    <img src="${post.author.img}" class="avatar avatar-lg fs-5" alt="ava" width="36px"
                         style="border-radius: 18px"/>
                </a>
                <div class="ms-3">
                    <h6 class="mb-0 fs-sm">${post.author}</h6>
                    <span class="text-muted fs-sm">${post.dateTime}</span>
                </div>
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
        Published posts will be here
    </#list>
</#macro>

</html>