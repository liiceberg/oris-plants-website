<html lang="en">
<#include "base.ftl">

<#macro title>Profile</#macro>

<#macro content>
    <img src=""/><br>
    Name: ${user.name}<br>
    Surname: ${user.lastname}<br>
    <a href="/edit">edit</a><br>
    <a href="/logout">log out</a><br>
</#macro>

</html>