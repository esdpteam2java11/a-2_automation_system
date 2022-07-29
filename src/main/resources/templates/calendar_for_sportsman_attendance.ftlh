<#import "partials/sportsman_main.ftlh" as main />
<@main.renderWith scripts=["/js/fullcalendar/main.js","/js/fullcalendar/calendarShow.js","/js/fullcalendar/ru.js"] styles=["/css/fullcalendar/main.min.css"]>
    <div id="group" class="text-center">
        <h2 class="mt-3">${group.name}</h2>
          </div>
    <div class="container">
        <div id='calendar'>
        </div>
    </div>

</@main.renderWith>
