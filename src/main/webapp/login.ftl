<html lang="en">
<#include "basic/base_with_header.ftl">

<#macro title>Log in</#macro>

<#macro script></#macro>

<#macro content>

        <p class="text-center">
        For the first time on our website? <a class="btn btn-secondary" href="/signup">Sign up</a>
        </p>
        <br>
        <form style="width: 500px; margin: 0 auto" action="login" method="post">
            <div class="mb-3">
                <label for="login" class="form-label">Login</label>
                <input type="text" class="form-control" id="login" name="login"/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password"/>
            </div>
            <div class="mb-3 form-check">
                <input class="form-check-input" type="checkbox" name="save" id="check"/>
                <label class="form-check-label" for="check">Always sign in on this device</label>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="Sign in"/>
            </div>
        </form>
        <#if error??>
            <p class="text-center">Login or password entered incorrectly</p>
        </#if>

</#macro>

</html>