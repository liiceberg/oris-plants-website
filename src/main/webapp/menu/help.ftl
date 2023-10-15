<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Help</#macro>

<#macro content>
    <#list posts as post>
        ${post.title}<br>
        <#if post.img??><img src="${post.img}"/><br></#if>
        <#if post.text??>${post.text}<br></#if>
        ${post.dateTime}<br>
        ${post.author}<br>
        <br>
    <#else>
        Published posts will be here
    </#list>
</#macro>

</html>