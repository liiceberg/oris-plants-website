<html lang="en">
<#include "basic/base.ftl">
<#macro title></#macro>

<#macro scripts></#macro>
<#macro content>
    <div class="card" style="width: 680px; margin: 0 auto">

        <div class="card-img-top">
            <img src="${user.img}" class="img-fluid rounded" alt="Photo">
        </div>
        <div class="card-body">
            <p class="card-text"><strong>Name:</strong> ${user.name}</p>
            <p class="card-text"><strong>Surname:</strong> ${user.lastname}</p>
            <p class="card-text"><strong>About me:</strong><#if user.description??> ${user.description}</#if></p>
        </div>

    </div>


<#--    <div class="content container" style="width: 500px; margin: 0 auto">-->
<#--        <figure class="figure mt-3" style="width: 400px; margin: 0 auto; display: block">-->
<#--            <img src="${user.img}" class="figure-img img-fluid rounded" alt="Photo">-->
<#--        </figure>-->
<#--        <p><strong>Name:</strong> ${user.name}</p>-->
<#--        <p><strong>Surname:</strong> ${user.lastname}</p>-->
<#--        <#if user.description??>-->
<#--            <p><strong>About me:</strong> ${user.description}</p>-->
<#--        </#if>-->
<#--    </div>-->
</#macro>

</html>