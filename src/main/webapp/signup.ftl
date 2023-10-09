<html lang="en">
<#include "base.ftl">

<#macro title>Sign up</#macro>

<#macro content>
    <form action="signup" method="post">
        Name: <input type="text" name="name"/><br>
        Surname: <input type="text" name="lastname"/><br>
        Login: <input type="text" name="login"/><br>
        Password: <input type="password" name="password"/><br>
        Repeat password: <input type="password" name="password_2"/><br>
        <input type="submit" value="sign up"/>
    </form>
    <#if login_error??>User with this login already exist<br></#if>
    <#if passwords_not_equal_error??>Passwords not equal<br></#if>
    <#if password_length_error??>Password must contain at least 8 characters<br></#if>
    <#if password_letters_error??>Password must contain letters<br></#if>
    <#if password_dig_error??>Password must contain digits<br></#if>
    <#if empty_field_error??>Fields must be not empty<br></#if>
</#macro>

</html>