<html lang="en">
<#include "base.ftl">

<#macro title>Exception</#macro>

<#macro content>
    <h1>Details:</h1>
    <strong>Status code:</strong>${status_code}<br>
    <strong>Request URI:</strong>${uri}
    <#if message??><strong>Message:</strong>${message}</#if>
</#macro>


</html>