<#import "partials/sportsman_main.ftlh" as main />
<@main.renderWith scripts=["/js/ckeditor/ckeditor.js"]>
    <div class="container">
    <form method="post" action="${event.id}/edit" name="editEvent">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th scope="col">Занятие для</th>
            <th scope="col">Дата занятия</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${event.sportsman.name}</td>
            <td><input type="date"  name="eventDate" value="${event.eventDate}" min="${.now?date?iso_utc}"></td>
        </tr>
        </tbody>
    </table>
        <#if message??>
            <span style="color: red">
                ${message} </span><br>
        </#if>
        <button class="bn30 mb-2" type="submit">Редактировать заметку</button>
    </form>
                <form method="get" action="${event.id}/delete">
                    <button class="bn30 mb-2" type="submit">Удалить заметку</button>
                </form>
                <form method="get" action="${event.id}/deleteConnected">
                    <button class="bn30 mb-3" type="submit">Удалить серию заметок</button>
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
                <button type="submit" class="mt-3 bn30 mb-3">Отправить</button>
            </div>
        </form>
    <h3>Диета для занятий</h3>
    <#if programMessage??>
        <span style="color: red">
                ${programMessage} </span><br>
    </#if>

    <form action="${event.id}/food" method="post">
        <div>
      <textarea cols="80" rows="10" id="contentFood" name="contentFood">
          <#if event.food??>
              ${event.food}
          </#if>
      </textarea>
            <script type="text/javascript">
                CKEDITOR.replace('contentFood');
            </script>
            <button type="submit" class="mt-3 bn30 mb-3">Отправить</button>
        </div>
    </form>
<#--        <form method="get" action="/group/calendar">-->
<#--            <button class="bn30 mb-3" type="submit">Назад</button>-->
<#--        </form>-->

</@main.renderWith>