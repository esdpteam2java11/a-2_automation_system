<html lang='ru'>

<head>
    <meta charset='utf-8' />
    <link href="/css/fullcalendar/main.min.css" rel='stylesheet' />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <script src="/js/fullcalendar/main.js"> </script>
    <script src="/js/fullcalendar/calendarShow.js"></script>
    <script src="/js/fullcalendar/ru.js"></script>
</head>

<body>
<div>
    <form method="get" action="calendar/create" >
        <input type="submit" value="Создать занятие" class="btn btn-primary">
    </form>
</div>
<div id="group">
   <h2>${group.name}</h2>
    <input id="group_Id" hidden value="${group.id}" />
</div>

<div id='calendar'></div>
</body>

</html>