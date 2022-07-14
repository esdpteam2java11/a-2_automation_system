<#--<html lang='ru'>-->

<#--<head>-->
<#--    <meta charset='utf-8' />-->
<#--    <link href="/css/fullcalendar/main.min.css" rel='stylesheet' />-->
<#--    <script src="/js/fullcalendar/main.js"> </script>-->
<#--    <script src="/js/fullcalendar/calendarShow.js"></script>-->
<#--    <script src="/js/fullcalendar/ru.js"></script>-->
<#--</head>-->

<#--<body>-->
<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <div id="group" class="text-center">
        <h2 class="mt-3">${group.name}</h2>
        <#if message??>
            <span style="color: red">
                ${message} </span><br>
        </#if>
<#--        <input id="group_Id" hidden value="${group.id}"/>-->
<#--        <form method="get" action="calendar/events/create">-->
<#--            <input type="submit" value="Создать занятие" class="fc-button fc-button-primary">-->
<#--        </form>-->
        <a href="/group/${group.id}/calendar/events/create"><button class="bn30 mb-5 my-4">Создать занятие</button></a>
    </div>

    <div id='calendar'>
    </div>
</@main.renderWith>
<#--</body>-->
<#--</html>-->