<html lang="en">

<#include "basic/base.ftl">

<#macro title>Profile</#macro>

<#macro content>

    <img src="${user.img}"/><br>

    <form method="post" action="upload" enctype="multipart/form-data">
        Change profile picture: <input type="file" name="img"/><br>
        Name: <input type="text" name="name" value="${user.name}"/><br>
        Surname: <input type="text" name="lastname" value="${user.lastname}"/><br>
        <input type="submit" value="save"/>
    </form>
    <#if empty_field_error??>Fields must be not empty<br></#if>
</#macro>

</html>