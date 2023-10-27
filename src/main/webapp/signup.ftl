<html lang="en">
<#include "basic/base.ftl">

<#macro title>Sign up</#macro>
<#macro scripts></#macro>

<#macro content>
    <#include "basic/header.ftl">
    <div class="content container">
        <form action="signup" method="post" style="width: 500px; margin: 0 auto">
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

        <#if login_error??><div class="text-center alert alert-danger">User with this login already exist</div><br></#if>
        <#if passwords_not_equal_error??><div class="text-center alert alert-danger">Passwords not equal</div><br></#if>
        <#if password_length_error??><div class="text-center alert alert-danger">Password must contain at least 8 characters</div><br></#if>
        <#if password_letters_error??><div class="text-center alert alert-danger">Password must contain letters</div><br></#if>
        <#if password_dig_error??><div class="text-center alert alert-danger">Password must contain digits</div><br></#if>
        <#if empty_field_error??><div class="text-center alert alert-danger">Fields must be not empty</div><br></#if>
    </div>
</#macro>

</html>