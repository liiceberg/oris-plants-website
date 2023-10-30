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
                <input type="text" class="form-control" id="name" name="name" value="${user.name}" required maxlength="60"/>
            </div>
            <div class="mb-3">
                <label for="lastname" class="form-label">Surname</label>
                <input type="text" class="form-control" id="lastname" name="lastname" value="${user.lastname}" required maxlength="60"/>
            </div>
            <div class="mb-3">
                <label for="desc" class="form-label">About me</label>
                <textarea class="form-control" id="desc" name="desc" maxlength="512"><#if user.description??>${user.description}</#if></textarea>
                <p id="charsLeft"></p>
            </div>
            <div class="mb-3">
                <label for="old_password" class="form-label">Old password</label>
                <input type="password" class="form-control" id="old_password" name="old_password"/>
            </div>
            <div class="mb-3">
                <label for="new_password" class="form-label">New password</label>
                <input type="password" class="form-control" id="new_password" name="new_password" minlength="8"/>
            </div>
            <div class="mb-3">
                <label for="new_password_2" class="form-label">Repeat new password</label>
                <input type="password" class="form-control" id="new_password_2" name="new_password_2" minlength="8"/>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="Save"/>
            </div>
        </form>
        <br>
        <#if empty_field_error??><div class="text-center alert alert-danger">Name and surname can't be empty</div><br></#if>
        <#if passwords_not_equal_error??><div class="text-center alert alert-danger">Passwords not equal</div><br></#if>
        <#if password_length_error??><div class="text-center alert alert-danger">Password must contain at least 8 characters</div><br></#if>
        <#if password_letters_error??><div class="text-center alert alert-danger">Password must contain letters</div><br></#if>
        <#if password_dig_error??><div class="text-center alert alert-danger">Password must contain digits</div><br></#if>
        <#if old_password_error??><div class="text-center alert alert-danger">Old password entered incorrect</div><br></#if>
        <#if db_error??><div class="text-center alert alert-danger">Sorry, can not edit information</div><br></#if>
    </div>
</#macro>

</html>