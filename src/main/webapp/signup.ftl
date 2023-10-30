<#ftl encoding="utf-8">
<html lang="en">
<#include "basic/base.ftl">

<#macro title>Sign up</#macro>

<#macro scripts>
    <script>
        $(document).ready(function () {
            let nameError = true
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

            let lastnameError = true
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

            let loginError = true
            const login = document.getElementById("login")
            login.addEventListener('keyup', function () {
                loginError = validateLogin()
            })

            function validateLogin() {
                if (login.value.length === 0) {
                    $("#login-feedback").text("Login must be not empty")
                    $("#login").addClass("is-invalid")
                    return true
                } if (login.value.length > 30) {
                    $("#login-feedback").text("Too long login")
                    $("#login").addClass("is-invalid")
                    return true
                } if (!login.value.match(/\w/)) {
                    $("#login-feedback").text("Login must contains word characters (letters, numbers, underscores)")
                    $("#login").addClass("is-invalid")
                    return true
                }
                else {
                    $("#login-feedback").text("Looks good")
                    $("#login").removeClass("is-invalid")
                    $("#login").addClass("is-valid")
                    return false
                }
            }

            let passwordError = true
            const password = document.getElementById("password")
            password.addEventListener('keyup', function () {
                passwordError = validatePassword()
            })

            function validatePassword() {
                if (password.value.length === 0) {
                    $("#password-feedback").text("Password must be not empty")
                    $("#password").addClass("is-invalid")
                    return true
                }
                if (password.value.length < 8) {
                    $("#password-feedback").text("Password must be at least 8 characters long")
                    $("#password").addClass("is-invalid");
                    return true
                }

                let re2 = /[a-zA-ZА-Яа-я]/
                let re3 = /[0-9]/
                if (re2.test(password.value) &&  !re3.test(password.value)) {
                    $("#password-feedback").text("Password must contains digits")
                    $("#password").addClass("is-invalid");
                    return true
                }

                if (re3.test(password.value) && !re2.test(password.value)) {
                    $("#password-feedback").text("Password must contains letters")
                    $("#password").addClass("is-invalid");
                    return true
                }
                else {
                    $("#password-feedback").text("It is strong password")
                    $("#password").removeClass("is-invalid")
                    $("#password").addClass("is-valid")
                    return false
                }
            }

            let passwordsNotEqualsError = true
            const password_2 = document.getElementById("password_2")
            password_2.addEventListener('keyup', function () {
                passwordsNotEqualsError = validatePassword2()
            })

            function validatePassword2() {
                if (password_2.value !== password.value) {
                    $("#password-2-feedback").text("Password doesn't match")
                    $("#password_2").addClass("is-invalid")
                    return true
                } else {
                    $("#password-2-feedback").text("Password match")
                    $("#password_2").removeClass("is-invalid")
                    $("#password_2").addClass("is-valid")
                    return false
                }
            }

            const form = document.getElementById("form")
            form.addEventListener('submit', function (event) {
                if (nameError || lastnameError || loginError || passwordError || passwordsNotEqualsError) {
                    event.preventDefault()
                }
            })
        })
    </script>
</#macro>

<#macro content>
    <#include "basic/header.ftl">
    <div class="content container">
        <form action="/signup" method="post" style="width: 500px; margin: 0 auto" novalidate id="form">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" required pattern="[A-Za-z]"/>
                <div id="name-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="lastname" class="form-label">Surname</label>
                <input type="text" class="form-control" id="lastname" name="lastname" required pattern="[A-Za-z]"/>
                <div id="lastname-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="login" class="form-label">Login</label>
                <input type="text" class="form-control" id="login" name="login" required pattern="\w"/>
                <div id="login-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required minlength="8"/>
                <div id="password-feedback"></div>
            </div>
            <div class="mb-3">
                <label for="password_2" class="form-label">Repeat password</label>
                <input type="password" class="form-control" id="password_2" name="password_2" required minlength="8"/>
                <div id="password-2-feedback"></div>
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-primary" value="Sign up"/>
            </div>
        </form>
        <br>
        <#if login_error??>
            <div class="text-center alert alert-danger">User with this login already exist</div><br></#if>
        <#if passwords_not_equal_error??>
            <div class="text-center alert alert-danger">Passwords not equal</div><br></#if>
        <#if password_length_error??>
            <div class="text-center alert alert-danger">Password must contain at least 8 characters</div><br></#if>
        <#if password_letters_error??>
            <div class="text-center alert alert-danger">Password must contain letters</div><br></#if>
        <#if password_dig_error??>
            <div class="text-center alert alert-danger">Password must contain digits</div><br></#if>
        <#if empty_field_error??>
            <div class="text-center alert alert-danger">Fields must be not empty</div><br></#if>
    </div>
</#macro>

</html>