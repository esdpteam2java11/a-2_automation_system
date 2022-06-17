<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <#list groups as group>
        <div>
            <a href="/group/${group.id}"><p>${group.name}</p></a>
        </div>
    </#list>
    <a href="">
        <button class="btn btn-primary">Добавить группу</button>
    </a>
</@main.renderWith>