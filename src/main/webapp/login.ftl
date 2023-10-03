<html lang="en">
<#include "base.ftl">

<#macro title>Login</#macro>

<#macro content>
    <form action="login" method="post">
        Login: <input type="text" name="login"/><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="send">
    </form>
</#macro>

</html>