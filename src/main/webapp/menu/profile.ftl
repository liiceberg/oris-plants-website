<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Profile</#macro>

<#macro content>

    <div class="card">
        <img class="img-fluid" src="${user.img}"/><br>
        <div class="card-body">
            <p class="card-text"><strong>Login:</strong> ${user.login}</p>
            <p class="card-text"><strong>Name:</strong> ${user.name}</p>
            <p class="card-text"><strong>Surname:</strong> ${user.lastname}</p>
        </div>
        <div class="card-footer">
            <ul class="nav nav-pills card-footer-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="/edit">Edit</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Log out</a>
                </li>
            </ul>
        </div>
    </div>

</#macro>

</html>