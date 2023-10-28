<html lang="en">
<#include "basic/base.ftl">

<#macro title>Exception</#macro>

<#macro scripts></#macro>

<#macro content>
    <div class="content container mt-3">
        <h3 class="text-center">Ooops, something went wrong</h3>
        <p class="text-start"><strong>Status code:</strong> ${status_code}</p>
        <p class="text-start"><strong>Request URI:</strong> ${uri}</p>
        <#if message??><p class="text-start"><strong>Message:</strong> ${message}</p></#if>
        <#if 400 <= status_code  && status_code < 500>
            <a class="btn btn-primary" href="/main">Return to main page</a>
        </#if>
        <img src="/img/vred.gif" style="margin: 0 auto; width: 520px; display: block;"/>
    </div>
</#macro>

</html>