<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Help</#macro>

<#macro content>
    <#list posts as post>
        <div class="card">
            <div class="card-header d-flex align-items-center">
                <span class="avatar text-bg-primary avatar-lg fs-5">R</span>
                <div class="ms-3">
                    <h6 class="mb-0 fs-sm">${post.author}</h6>
                    <span class="text-muted fs-sm">${post.dateTime}</span>
                </div>
            </div>
            <div class="card-body">
                <p class="card-text">${post.title}</p>
            </div>
            <#if post.img??><img class="card-img-top" src="${post.img}"/></#if>
            <div class="card-footer d-flex">
                <a class="btn btn-link p-0 me-auto fw-bold" href="/post/${post.id}">Read more</a>
                <button class="btn btn-subtle" type="button"><i class="fas fa-heart fa-lg"></i></button>
            </div>
        </div>
        <br>
    <#else>
        Published posts will be here
    </#list>
</#macro>

</html>