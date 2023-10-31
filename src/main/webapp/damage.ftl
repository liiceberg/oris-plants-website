<html lang="en">

<#include "basic/base.ftl">

<#macro title>Damage</#macro>

<#macro scripts></#macro>

<#macro content>
    <#if damage??>
        <div class="content container mt-3" style="width: 700px; margin: 0 auto">

            <h3 class="text-center">${damage.causativeAgentDescription}</h3>

            <figure class="figure mt-3 mb-3" style="width: 520px; margin: 0 auto; display: block">
                <img src="${damage.img}" class="figure-img img-fluid rounded" alt="Photo">
            </figure>

            <div class="d-flex">
                <h5 class="me-2">Type:</h5>
                <p>
                    <#if damage.causativeAgentType.value == 0>Pest</#if>
                    <#if damage.causativeAgentType.value == 1>Desease</#if>
                </p>
            </div>

            <h5>Symptoms:</h5>
            <p style="white-space: pre-wrap">${damage.symptoms}</p>

            <h5>Control measures:</h5>
            <p style="white-space: pre-wrap"> ${damage.controlMeasures} </p>

        </div>
    </#if>
</#macro>

</html>