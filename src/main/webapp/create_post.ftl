<html lang="en">
<#include "basic/base.ftl">

<#macro title>New post</#macro>

<#macro content>
    <form method="post">
        <input type="image" name="img"/><br>
        Title: <input type="text" name="title"/><br>
        Your question: <input type="text" name="text"/><br>
        <input type="submit" value="save"/>
    </form>
</#macro>

</html>