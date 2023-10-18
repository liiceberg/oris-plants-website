<html lang="en">
<#include "basic/base.ftl">

<#macro title>Sign up</#macro>
<#macro script></#macro>

<#macro content>
    <form action="signup" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name"/>
        </div>
        <div class="mb-3">
            <label for="lastname" class="form-label">Surname</label>
            <input type="text" class="form-control" id="lastname" name="lastname"/>
        </div>
        <div class="mb-3">
            <label for="login" class="form-label">Login</label>
            <input type="text" class="form-control" id="login" name="login"/>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password"/>
        </div>
        <div class="mb-3">
            <label for="password_2" class="form-label">Repeat password</label>
            <input type="password" class="form-control" id="password_2" name="password_2"/>
        </div>
        <div class="text-center">
            <input type="submit" class="btn btn-primary" value="Sign up"/>
        </div>
    </form>

    <#if login_error??>User with this login already exist<br></#if>
    <#if passwords_not_equal_error??>Passwords not equal<br></#if>
    <#if password_length_error??>Password must contain at least 8 characters<br></#if>
    <#if password_letters_error??>Password must contain letters<br></#if>
    <#if password_dig_error??>Password must contain digits<br></#if>
    <#if empty_field_error??>Fields must be not empty<br></#if>
</#macro>

</html>