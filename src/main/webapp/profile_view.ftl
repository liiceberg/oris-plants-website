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

</#macro>

</html>