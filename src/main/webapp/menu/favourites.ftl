<html lang="en">
<#include "../basic/menu_page.ftl">

<#macro title>Favourites</#macro>

<#macro content>
    <div class="content container" style="margin: 0 auto" style="width: 640px;">
        <#list plants as plant>
            <a class="card" href="/plant/${plant.name}?id=${plant.id}" style="text-decoration: none">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="${plant.img}" class="img-fluid rounded-start">
                    </div>
                    <div class="col-md-8">
                        <div class="card-header d-flex justify-content-between">
                            <h5 class="card-title">${plant.name}</h5>
                            <button class="btn btn-subtle text-end" type="button">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                </svg>
                            </button>
                        </div>
                        <div class="card-body">

                        </div>
                    </div>
                </div>

            </a>
            <br>
        </#list>
    </div>
</#macro>

</html>