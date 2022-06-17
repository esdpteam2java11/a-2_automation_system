<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Название группы</th>
            <th scope="col">Имя тренера</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${group.name}</td>
            <td>${group.trainer.name}</td>
        </tr>
        </tbody>
    </table>
    <button class="btn btn-primary">Редактировать</button>
</@main.renderWith>