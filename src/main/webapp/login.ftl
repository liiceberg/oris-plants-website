<html lang="en">
<#include "basic/base.ftl">

<#macro title>Log in</#macro>

<#macro scripts></#macro>

<#macro content>
    <#include "basic/header.ftl">
    <div class="content container">
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
        <br>
        <div class="content container" style="width: 500px; margin: 0 auto">
            <#if error??>
                <div class="text-center alert alert-danger">Login or password entered incorrectly</div><br></#if>
            <#if db_error??>
                <div class="text-center alert alert-danger">Sorry, it is not possible to log in</div><br></#if>
        </div>
    </div>
</#macro>

</html>