<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>My posts</#macro>

<#macro content>
    <a href="/add">Add new post</a><br>

    <#list posts as post>
        <a href="/post/${post.id}">
            ${post.title}<br>
            <#if post.img??><img src="${post.img}"/><br></#if>
            <#if post.text??>${post.text}<br></#if>
            ${post.dateTime}<br>
            ${post.author}<br>
        </a>
        <br>
    <#else>
        Your posts will be here
    </#list>

</#macro>

</html>