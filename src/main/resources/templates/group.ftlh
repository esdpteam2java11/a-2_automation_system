<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Название группы</th>
            <th scope="col">Имя тренера</th>
            <th scope="col">Цена</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${group.name}</td>
            <td>${group.trainer.name}</td>
            <td>${group.sum}</td>
        </tr>
        </tbody>
    </table>
    <table>
        <tr>
            <td>
                <button class="btn btn-primary">Редактировать</button>
            </td>
            <td>
                <form method="get" action="${group.id}/calendar">
                    <input type="submit" value="Календарь группы" class="btn btn-primary">
                </form>
            </td>
        </tr>
    </table>



</@main.renderWith>