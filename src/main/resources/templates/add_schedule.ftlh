<#import "partials/admin_main.ftlh" as main />
<@main.renderWith title="Добавить занятие">
    <body>
    <form action="/add/event" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div>
            <h2>Форма создания занятия или занятий для группы:</h2>
            <h3>${(group.name)}</h3>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Дата</p>
                <input type="datetime-local" name="dateTime" id="date">
            </label>
        </div>
        <div>
            <button type="submit">Добавить</button>
        </div>

    </form>
    </body>

    </html>
</@main.renderWith>