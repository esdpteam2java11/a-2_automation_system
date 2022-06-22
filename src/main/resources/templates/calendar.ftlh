<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="apple-touch-icon" type="image/png"
          href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png">
    <meta name="apple-mobile-web-app-title" content="CodePen">
    <link rel="shortcut icon" type="image/x-icon"
          href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">
    <link rel="mask-icon" type="image/x-icon"
          href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg"
          color="#111">
    <title>CodePen - fullCalendar with custom agenda view</title>
    <link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.min.css">
    <script>
        window.console = window.console || function (t) {
        };
    </script>
    <script>
        if (document.location.search.match(/type=embed/gi)) {
            window.parent.postMessage("resize", "*");
        }
    </script>
</head>
<body translate="no">
<div id="calendar" class="fc fc-ltr">
</div>
<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
<script src="https://raw.githubusercontent.com/gf3/moment-range/master/lib/moment-range.js"></script>
<script id="rendered-js">
    var $calendar = $("#calendar");
    $calendar.fullCalendar({
        header: {
            left: ' today,prev,next,  title',
            right: 'month,agendaWeek,agendaDay '
        },

        weekends: true,
        allDaySlot: true,
        droppable: true,
        eventAfterAllRender: function (view) {
            var header = $calendar.find(".fc-header");
            // trigger current view
            header.find('.fc-header-right').find('.fc-button').off('mouseup').on('mouseup', function () {
                if (!$(this).hasClass('fc-button-agendaView')) {
                    $calendar.data("view", '');
                }
            });

            if ($calendar.data("view") != 'agendaView') {
                header.find(".fc-button-agendaView").removeClass('fc-state-active active');
                $("#agendaView").remove();

            } else {
                renderAgendaView();
            }


        },
        eventRender: function (event, e) {

            currentView = $('#calendar').fullCalendar('getView').name; /* 用來偵測哪個view進行什麼樣的處理用 */
        }, dayClick: function (date, jsEvent, view) {


        },
        eventClick: function (calEvent, jsEvent, view) {
            console.log("===== eventClick =====");
            console.log(calEvent);


        }
    });


    var headerRight = $calendar.find(".fc-header").find(".fc-header-right");

    var agendaBtn = headerRight.find(".fc-corner-right").removeClass('fc-corner-right').clone().addClass('fc-corner-right fc-button-agendaView').removeClass('fc-button-agendaDay').text("agenda");

    agendaBtn.on('click', function () {
        renderAgendaView();
    });

    headerRight.find(".fc-header-space").before(agendaBtn);

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var newEvent = {
        title: 'NEW EVENT',
        start: new Date(y, m, d, 10),
        end: new Date(y, m, d, 15),
        editable: true
    };


    var event = $('#calendar').fullCalendar('renderEvent', newEvent, 'stick');


    function renderAgendaView() {

        if ($calendar.fullCalendar('getView') != 'agendaView') {
            $calendar.fullCalendar('changeView', 'month');
            var newView = $calendar.fullCalendar('getView');
            newView.name = 'agendaView';

            $calendar.fullCalendar('changeView', 'agendaView');
        }

        var events = $calendar.fullCalendar('clientEvents');

        var currentDate = $calendar.fullCalendar('getDate');

        $calendar.find(".fc-header").find(".fc-button-agendaView").siblings().removeClass('fc-state-active').end().addClass('fc-state-active');

        $calendar.data("view", 'agendaView');
        var agendaViewHtml = document.createElement('div');
        agendaViewHtml.setAttribute("id", "agendaView");
        var contents = "<table>" +
            "<thead><tr>" +
            "<th class='fc-widget-header fc-agendaView-event-start'>DateStart</th>" +
            "<th class='fc-widget-header fc-agendaView-event-end'>DateEnd</th>" +
            "<th class='fc-widget-header fc-agendaView-event-title'>Event</th>" +
            "</tr></thead>" +
            "<tbody>";

        for (key in events) {
            //  detect month range
            var monthRange = moment().range(moment(currentDate).startOf('month'), moment(currentDate).endOf('month'));
            var eventStart = moment(events[key].start).format("YYYY/MM/DD-H:mm:ss");
            var eventEnd = moment(events[key].end).format("YYYY/MM/DD-H:mm:ss");
            if (monthRange.contains(events[key].start) && monthRange.contains(events[key].end)) {
                var eventTitle = events[key].title;
                contents += '<tr>' +
                    '<td class="fc-widget-content">' + eventStart + '</td>' +
                    '<td class="fc-widget-content">' + eventEnd + '</td>' +
                    '<td class="fc-widget-content">' + eventTitle + '</td>' +
                    '</tr>';

            }
        }
        contents += "</tbody></table>";
        agendaViewHtml.innerHTML = contents;
        $calendar.find(".fc-content").html(agendaViewHtml);

    }
</script>


</body>
</html>