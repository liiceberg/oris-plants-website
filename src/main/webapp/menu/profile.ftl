<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Profile</#macro>

<#macro scripts>
</#macro>

<#macro content>

    <div class="card" style="width: 680px; margin: 0 auto">

        <div class="card-img-top">
            <img class="img-fluid rounded" src="${user.img}">
        </div>
        <div class="card-body">
            <p class="card-text"><strong>Login:</strong> ${user.login}</p>
            <p class="card-text"><strong>Name:</strong> ${user.name}</p>
            <p class="card-text"><strong>Surname:</strong> ${user.lastname}</p>
            <p class="card-text"><strong>About me:</strong><#if user.description??> ${user.description}</#if></p>
        </div>
        <div class="card-footer">
            <ul class="nav nav-pills card-footer-pills">
                <li class="nav-item">
                    <a class="nav-link active" href="/edit">Edit</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#modal" data-bs-toggle="modal" id="logout">Log out</a>
                </li>
            </ul>
        </div>
    </div>


    <div class="modal" tabindex="-1" id="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Warning</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to log out?</p>
                </div>
                <div class="modal-footer">
                    <a data-bs-dismiss="modal" class="btn btn-secondary">Cancel</a>
                    <a href="/logout" class="btn btn-primary">Log out</a>
                </div>
            </div>
        </div>
    </div>

</#macro>

</html>