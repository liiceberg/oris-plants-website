<html lang="en">
<#include "basic/base.ftl">

<#macro title>Log in</#macro>

<#macro content>
    <a href="/signup">sign up</a>
    <br>
    <form action="login" method="post">
        Login: <input type="text" name="login"/><br>
        Password: <input type="password" name="password"><br>
        <input type="checkbox" name="save"/> Remember me<br/>
        <input type="submit" value="log in"/>
    </form>
    <#if error??>Login or password entered incorrectly</#if>
</#macro>

</html>