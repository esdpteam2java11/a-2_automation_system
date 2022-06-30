

async function myFunction() {
    const linkBase = window.location.origin;
    const groupId = document.getElementById("group_Id");
    console.log(groupId)
     const res = await fetch('')
    // const data = await res.json()
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

