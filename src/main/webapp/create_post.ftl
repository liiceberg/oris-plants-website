<html lang="en">
<#include "basic/base.ftl">

<#macro title>New post</#macro>

<#macro content>
    <form method="post" action="/add" enctype="multipart/form-data">
        Add picture: <input type="file" name="img"/><br>
        Your question: <input type="text" name="title"/><br>
        Describe in more detail: <textarea name="text"></textarea><br>
        <input type="submit" value="save"/>
    </form>

    <#if empty_title_error??>Post can't be created without text<br></#if>
</#macro>

</html>