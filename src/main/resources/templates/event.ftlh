<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Занятие для</th>
            <th scope="col">Дата занятия</th>
            <th scope="col">Время начала</th>
            <th scope="col">Время конца</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${event.group.name}</td>
            <td>${event.eventDate}</td>
            <td>${event.startTime}</td>
            <td>${event.endTime}</td>
        </tr>
        </tbody>
    </table>
    <table>
        <tr>
            <td>
                <form method="get" action="edit">
                    <input type="submit" value="Редактировать занятие" class="btn btn-primary">
                </form>
            </td>
            <td>
                <form method="get" action="${event.id}/delete">
                    <input type="submit" value="Удалить занятие" class="btn btn-primary">
                </form>
            </td>
            <td>
                <form method="get" action="${event.id}/deleteConnected">
                    <input type="submit" value="Удалить серию занятий" class="btn btn-primary">
                </form>
            </td>
        </tr>
    </table>
</@main.renderWith>