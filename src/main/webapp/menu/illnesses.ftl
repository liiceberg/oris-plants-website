<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Ilnesses</#macro>

<#macro scripts></#macro>

<#macro content>
    <div class="content container">
        <#if items??>
            <#list items as item>
                <p>
                    <a href="/damage/${item.name}?id=${item.id}" class="link-offset-2 link-underline link-underline-opacity-10">
                        ${item.name}
                    </a>
                </p>
            </#list>
        </#if>
    </div>
</#macro>

</html>