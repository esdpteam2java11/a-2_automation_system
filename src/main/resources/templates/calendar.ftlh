<html lang='ru'>

<head>
    <meta charset='utf-8' />
    <link href="/css/fullcalendar/main.min.css" rel='stylesheet' />
    <script src="/js/fullcalendar/main.js"> </script>
    <script src="/js/fullcalendar/calendarShow.js"></script>
    <script src="/js/fullcalendar/ru.js"></script>
</head>

<body>
<div>
    <form method="get" action="calendar/create">
        <input type="submit" value="Создать занятие">
    </form>
</div>
<div id="group">
   <h2>${group.name}</h2>
    <input id="group_Id" hidden value="${group.id}" />
</div>

<div id='calendar'></div>
</body>

</html>