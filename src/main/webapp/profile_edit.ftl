<html lang="en">

<#include "basic/base.ftl">

<#macro title>Profile</#macro>

<#macro content>

    <img src="${user.img}"/><br>

    <form method="post" enctype="multipart/form-data">
        Change profile picture: <input type="file" name="img"/><br>
        Name: <input type="text" name="name" value="${user.name}"/><br>
        Surname: <input type="text" name="lastname" value="${user.lastname}"/><br>
        Old password: <input type="password" name="old_password"/><br>
        New password: <input type="password" name="new_password"/><br>
        Repeat new password: <input type="password" name="new_password_2"/><br>
        <input type="submit" value="save"/>
    </form>
    <#if empty_field_error??>Name and surname can't be empty<br></#if>
    <#if passwords_not_equal_error??>Passwords not equal<br></#if>
    <#if password_length_error??>Password must contain at least 8 characters<br></#if>
    <#if password_letters_error??>Password must contain letters<br></#if>
    <#if password_dig_error??>Password must contain digits<br></#if>
    <#if old_password_error??>Old password entered incorrect<br></#if>
</#macro>

</html>