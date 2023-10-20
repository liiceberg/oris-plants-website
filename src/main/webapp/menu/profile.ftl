<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Profile</#macro>

<#macro content>

    <div class="card" style="width: 680px; margin: 0 auto;">

        <div class="row g-0">
            <div class="col-md-4">
                <img class="img-fluid rounded-start" src="${user.img}" width="200">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <p class="card-text"><strong>Login:</strong> ${user.login}</p>
                    <p class="card-text"><strong>Name:</strong> ${user.name}</p>
                    <p class="card-text"><strong>Surname:</strong> ${user.lastname}</p>
                </div>
            </div>
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