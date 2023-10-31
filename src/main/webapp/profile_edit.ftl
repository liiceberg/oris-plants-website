<#ftl encoding="utf-8">
<html lang="en">
<#include "basic/base.ftl">

<#macro title>Profile</#macro>

<#macro scripts>
    <script>
        $(document).ready(function () {
            let nameError = false
            const name = document.getElementById("name")
            name.addEventListener('keyup', function () {
                nameError = validateName()
            })

            function validateName() {
                if (name.value.length === 0) {
                    $("#name-feedback").text("Name must be not empty");
                    $("#name").addClass("is-invalid")
                    return true
                } if (name.value.length > 60) {
                    $("#name-feedback").text("Too long name");
                    $("#name").addClass("is-invalid")
                    return true
                } if (!name.value.match(/[A-Za-zА-Яа-я]/)) {
                    $("#name-feedback").text("Name must contains letters")
                    $("#name").addClass("is-invalid")
                    return true
                }
                else {
                    $("#name-feedback").text("Looks good")
                    $("#name").removeClass("is-invalid")
                    $("#name").addClass("is-valid")
                    return false
                }
            }

            let lastnameError = false
            const lastname = document.getElementById("lastname")
            lastname.addEventListener('keyup', function () {
                lastnameError = validateLastname()
            })

            function validateLastname() {
                if (lastname.value.length === 0) {
                    $("#lastname-feedback").text("Lastname must be not empty")
                    $("#lastname").addClass("is-invalid")
                    return true
                } if (name.value.length > 60) {
                    $("#lastname-feedback").text("Too long lastname")
                    $("#lastname").addClass("is-invalid")
                    return true
                } if (!name.value.match(/[A-Za-zА-Яа-я]/)) {
                    $("#lastname-feedback").text("Lastname must contains letters")
                    $("#lastname").addClass("is-invalid")
                    return true
                }
                else {
                    $("#lastname-feedback").text("Looks good")
                    $("#lastname").removeClass("is-invalid")
                    $("#lastname").addClass("is-valid")
                    return false
                }
            }


            let descError = false
            const desc = document.getElementById("desc")
            desc.addEventListener('keyup', function () {
                descError = validateDesc()
            })

            function validateDesc() {
                if (desc.value.length > 512) {
                    $("#desc-feedback").text("Too long text")
                    $("#desc").addClass("is-invalid")
                    return true
                } else {
                    $("#desc-feedback").text("Looks good")
                    $("#desc").removeClass("is-invalid")
                    $("#desc").addClass("is-valid")
                    return false
                }
            }

            let oldPasswordError = false
            const old_password = document.getElementById("old_password")
            old_password.addEventListener('keyup', function () {
               oldPasswordError = validateOldPassword()
            })
            function validateOldPassword() {
                if (old_password.value.length === 0) {
                    $("#old-password-feedback").text("You should enter your current password");
                    $("#old_password").addClass("is-invalid");
                    return true
                } else {
                    $("#old-password-feedback").text("");
                    $("#old_password").removeClass("is-invalid");
                    return false
                }
            }


            let passwordError = false
            const password = document.getElementById("new_password")
            password.addEventListener('keyup', function () {
                passwordError = validatePassword()
            })

            function validatePassword() {
                if (password.value.length === 0) {
                    $("#new-password-feedback").text("Password must be not empty")
                    $("#new_password").addClass("is-invalid")
                    return true
                }
                if (password.value.length < 8) {
                    $("#new-password-feedback").text("Password must be at least 8 characters long")
                    $("#new_password").addClass("is-invalid");
                    return true
                }

                let re2 = /[a-zA-ZА-Яа-я]/
                let re3 = /[0-9]/
                if (re2.test(password.value) &&  !re3.test(password.value)) {
                    $("#new-password-feedback").text("Password must contains digits")
                    $("#new_password").addClass("is-invalid");
                    return true
                }

                if (re3.test(password.value) && !re2.test(password.value)) {
                    $("#new-password-feedback").text("Password must contains letters")
                    $("#new_password").addClass("is-invalid");
                    return true
                }
                else {
                    $("#new-password-feedback").text("It is strong password")
                    $("#new_password").removeClass("is-invalid")
                    $("#new_password").addClass("is-valid")
                    return false
                }
            }

            let passwordsNotEqualsError = false
            const new_password_2 = document.getElementById("new_password_2")
            new_password_2.addEventListener('keyup', function () {
                passwordsNotEqualsError = validatePassword2()
            })

            function validatePassword2() {
                if (new_password_2.value !== password.value) {
                    $("#new-password-2-feedback").text("Password doesn't match")
                    $("#new_password_2").addClass("is-invalid")
                    return true
                } else {
                    $("#new-password-2-feedback").text("Password match")
                    $("#new_password_2").removeClass("is-invalid")
                    $("#new_password_2").addClass("is-valid")
                    return false
                }
            }

            const form = document.getElementById("form")
            form.addEventListener('submit', function (event) {
                if (nameError || lastnameError || passwordError || passwordsNotEqualsError || oldPasswordError) {
                    event.preventDefault()
                }
            })
        })
    </script>
</#macro>

<#macro content>
    <div class="content container mb-3 mt-3">

        <h3 class="text-center">Profile editing</h3>

        <form method="post" id="form" action="/edit" enctype="multipart/form-data" style="width: 500px; margin: 0 auto" novalidate>

            <img src="${user.img}" class="img-fluid mb-3" alt="Photo"/>

            <div class="mb-3">
                <label for="img" class="form-label">Change profile picture</label>
                <input type="file" class="form-control" id="img" name="img"/>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${user.name}" required maxlength="60"/>
                <div id="name-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="lastname" class="form-label">Surname</label>
                <input type="text" class="form-control" id="lastname" name="lastname" value="${user.lastname}" required maxlength="60"/>
                <div id="lastname-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="desc" class="form-label">About me</label>
                <textarea class="form-control" id="desc" name="desc" maxlength="512"><#if user.description??>${user.description}</#if></textarea>
                <div id="desc-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="old_password" class="form-label">Old password</label>
                <input type="password" class="form-control" id="old_password" name="old_password"/>
                <div id="old-password-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="new_password" class="form-label">New password</label>
                <input type="password" class="form-control" id="new_password" name="new_password" minlength="8"/>
                <div id="new-password-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="new_password_2" class="form-label">Repeat new password</label>
                <input type="password" class="form-control" id="new_password_2" name="new_password_2" minlength="8"/>
                <div id="new-password-2-feedback"></div>
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