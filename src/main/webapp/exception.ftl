<html lang="en">
<#include "basic/base.ftl">

<#macro title>Exception</#macro>

<#macro script></#macro>

<#macro content>
    <h3 class="text-center">Details</h3>
    <strong>Status code:</strong>${status_code}<br>
    <strong>Request URI:</strong>${uri}
    <#if message??><strong>Message:</strong>${message}</#if>
</#macro>


</html>