<html lang="en">
<#include "basic/base.ftl">

<#macro title>New post</#macro>

<#macro scripts></#macro>
<#macro content>
    <div class="content container" style="width: 500px; margin: 0 auto">
        <h3 class="text-center mt-3">New Post</h3>
        <form method="post" action="/add" enctype="multipart/form-data" >
            <div class="mb-3">
                <label for="img" class="form-label">Add picture</label>
                <input type="file" class="form-control" id="img" name="img"/>
            </div>
            <div class="mb-3">
                <label for="title" class="form-label">Your question</label>
                <input type="text" class="form-control" id="title" name="title"/>
            </div>
            <div class="mb-3">
                <label for="text" class="form-label">Describe in more details</label>
                <textarea id="text" class="form-control" name="text"></textarea>
            </div>
            <div class="text-center">
                <input type="submit" value="Save" class="btn btn-primary"/>
            </div>
        </form>
        <br>
        <#if empty_title_error??><div class="text-center alert alert-danger">Post can't be created without text</div></#if>
    </div>
</#macro>

</html>