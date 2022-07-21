<#import "partials/admin_main.ftlh" as main />
<@main.renderWith scripts=["/js/ckeditor/ckeditor.js"]>


    <div class="container">
    <form method="post" action="${event.id}/edit" name="editEvent">
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
        <button class="bn30 mb-2" type="submit">Редактировать занятие</button>
    </form>
                <form method="get" action="${event.id}/delete">
                    <button class="bn30 mb-2" type="submit">Удалить занятие</button>
                </form>
                <form method="get" action="${event.id}/deleteConnected">
                    <button class="bn30 mb-3" type="submit">Удалить серию занятий</button>
                </form>
        <h3>Программа занятий</h3>
        <#if programMessage??>
            <span style="color: red">
                ${programMessage} </span><br>
        </#if>

    <form action="${event.id}/trainingProgram" method="post">
        <div>
      <textarea cols="80" rows="10" id="content" name="content">
          <#if event.trainingProgram??>
              ${event.trainingProgram}
          </#if>
      </textarea>
            <script type="text/javascript">
                CKEDITOR.replace('content');
            </script>
            <button type="submit" class="bn30 mb-3">Отправить</button>
        </div>
    </form>

        <form method="get" action="/group/${group.id}/calendar">
            <button class="bn30 mb-3" type="submit">Назад</button>
        </form>
    </div>
</@main.renderWith>