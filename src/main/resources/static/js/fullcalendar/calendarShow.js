

async function myFunction() {
    const linkBase = window.location.origin;
     const res = await fetch('')
    draw()
}

myFunction();

function draw(){
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        locale:'ru',
        //initialView: 'dayGridMonth',
        events: "calendar/events"
    });
    calendar.render();
}

