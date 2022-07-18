<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <main class="main-color-groups">
        <table class="table table-bordered border-primary table-groups ">
            <tbody>
            <#list groups as group>
                <tr>
                    <td class="groups-td"><a href="/group/${group.id}" class="groups-name"><p>${group.name}</p></a></td>
                    <td style="background-color: ${group.color}"></td>
                </tr>
            </#list>
            </tbody>
        </table>
        <div class="text-center buttons-groups">
            <a href="/group/add">
                <button type="button"  class="button-85">Добавить группу</button>
            </a>
            <a href="#">
                <button type="button" class="button-85">Отметить посещения</button>
            </a>
            <a href="/">
                <button type="button" class="button-85">Назад</button>
            </a>
        </div>
    </main>
</@main.renderWith>