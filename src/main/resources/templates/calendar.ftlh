<#import "partials/admin_main.ftlh" as main />
<@main.renderWith scripts=["/js/fullcalendar/main.js","/js/fullcalendar/calendarShow.js","/js/fullcalendar/ru.js"] styles=["/css/fullcalendar/main.min.css"]>
    <div id="group" class="text-center">
        <h2 class="mt-3">${group.name}</h2>
        <#if message??>
            <span style="color: red">
                ${message} </span><br>
        </#if>

        <a href="/group/${group.id}/calendar/events/create"><button class="bn30 mb-5 my-4">Создать занятие</button></a>
    </div>
    <div class="container">
        <div id='calendar'>
        </div>
    </div>

</@main.renderWith>
