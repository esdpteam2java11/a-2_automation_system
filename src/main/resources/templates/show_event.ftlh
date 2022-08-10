<#import "partials/sportsman_main.ftlh" as main />
<@main.renderWith scripts=["/js/ckeditor/ckeditor.js"]>
<div class="container">
    <form method="get" action="calendar_sportsman/${event.group.id}/calendar/${event.id}" >
        <table class="table table-dark table-striped">
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
                <td><input type="date" disabled = "disabled" name="eventStartDate" value="${event.eventDate}" min="${.now?date?iso_utc}"></td>
                <td><input type="time" disabled = "disabled" name="timeStart" value="${event.startTime}"></td>
                <td><input type="time" disabled = "disabled" name="timeEnd" value="${event.endTime}">

        </table>
        <h3>Программа занятий</h3>
        <textarea cols="80" disabled = "disabled" rows="10" id="content" name="content">
          <#if event.trainingProgram??>
              ${event.trainingProgram}
          </#if>
      </textarea>
        <script type="text/javascript">
            CKEDITOR.replace('content');
        </script>
    </form>
    <a href="/calendar_sportsman/${event.group.id}/">
        <button type="button" class="button-85">Назад</button>
    </a>
</@main.renderWith>