<html lang="en">
<#include "basic/base.ftl">

<#macro title>Profile</#macro>

<#macro scripts></#macro>

<#macro content>
    <div class="content container mb-3 mt-3">

        <h3 class="text-center">Profile editing</h3>

        <form method="post" action="/edit" enctype="multipart/form-data" style="width: 500px; margin: 0 auto">

            <img src="${user.img}" class="img-fluid mb-3" alt="Photo"/>

            <div class="mb-3">
                <label for="img" class="form-label">Change profile picture</label>
                <input type="file" class="form-control" id="img" name="img"/>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${user.name}"/>
            </div>
            <div class="mb-3">
                <label for="lastname" class="form-label">Surname</label>
                <input type="text" class="form-control" id="lastname" name="lastname" value="${user.lastname}"/>
            </div>
            <div class="mb-3">
                <label for="old_password" class="form-label">Old password</label>
                <input type="password" class="form-control" id="old_password" name="old_password"/>
            </div>
            <div class="mb-3">
                <label for="new_password" class="form-label">New password</label>
                <input type="password" class="form-control" id="new_password" name="new_password"/>
            </div>
            <div class="mb-3">
                <label for="new_password_2" class="form-label">Repeat new password</label>
                <input type="password" class="form-control" id="new_password_2" name="new_password_2"/>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="Save"/>
            </div>
        </form>
        <#if empty_field_error??>Name and surname can't be empty<br></#if>
        <#if passwords_not_equal_error??>Passwords not equal<br></#if>
        <#if password_length_error??>Password must contain at least 8 characters<br></#if>
        <#if password_letters_error??>Password must contain letters<br></#if>
        <#if password_dig_error??>Password must contain digits<br></#if>
        <#if old_password_error??>Old password entered incorrect<br></#if>
        <#if db_error??>Sorry, can not edit information<br></#if>
    </div>
</#macro>

</html>