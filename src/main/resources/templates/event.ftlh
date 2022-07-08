<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <form method="post" action="${event.id}/edit" name="editEvent">
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
            <td><input type="date"  name="eventStartDate" value="${event.eventDate}" min="${.now?date?iso_utc}"></td>
            <td><input type="time" name="timeStart" value="${event.startTime}"></td>
            <td><input type="time" name="timeEnd" value="${event.endTime}"> <#if errorTime??>
                    <span style="color: red">
                ${errorTime}</span>
                </#if></td>
            <input hidden name="id" value="${event.id}">
        </tr>
        </tbody>
    </table>
        <#if message??>
            <span style="color: red">
                ${message} </span><br>
        </#if>
        <input type="submit" value="Редактировать занятие" class="btn btn-primary">
    </form>
    <table>
        <tr>

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