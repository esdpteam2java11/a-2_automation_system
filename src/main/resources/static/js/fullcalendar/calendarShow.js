

async function myFunction() {
    const linkBase = window.location.origin;
    const res = await fetch(linkBase+'/calendar')
    const data = await res.json()
    draw(data)
}

myFunction();

function draw(data){
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: data
    });
    calendar.render();
}
