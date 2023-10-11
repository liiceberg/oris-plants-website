<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Profile</#macro>

<#macro content>
    <img src="${user.img}"/><br>
    Name: ${user.name}<br>
    Surname: ${user.lastname}<br>
    <a href="/edit">edit</a><br>
    <a href="/logout">log out</a><br>
</#macro>

</html>