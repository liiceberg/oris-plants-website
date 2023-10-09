<html lang="en">
<#include "base.ftl">

<#macro title>Profile</#macro>

<#macro content>
    <form method="post">
        <input type="image" name="img"/><br>
        Name: <input type="text" name="name" value="${user.name}"/><br>
        Surname: <input type="text" name="lastname" value="${user.lastname}"/><br>
        <input type="submit" value="save"/>
    </form>
    <#if empty_field_error??>Fields must be not empty<br></#if>
</#macro>

</html>