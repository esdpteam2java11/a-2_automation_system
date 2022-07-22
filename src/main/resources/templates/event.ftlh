<#import "partials/admin_main.ftlh" as main />
<@main.renderWith scripts=["/js/ckeditor/ckeditor.js"]>
    <div class="container">
    <form method="post" action="${event.id}/edit" name="editEvent">
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th scope="col">Занятие для</th>
            <th scope="col">Дата занятия</th>
            <th scope="col">Время начала</th>
            <th scope="col">Время конца</th>
        </tr>
        </thead>
        <tbody>
        <tr>

            <td>${event.group.name}</td>
            <td><input type="date"  name="eventStartDate" value="${event.eventDate}" min="${.now?date?iso_utc}"></td>
            <td><input type="time" name="timeStart" value="${event.startTime}"></td>
            <td><input type="time" name="timeEnd" value="${event.endTime}"> <#if errorTime??>
                    <span style="color: red">
                ${errorTime}</span>
                </#if></td>
            <input hidden name="id" value="${event.id}">
        </tr>
        </tbody>
    </table>
        <#if message??>
            <span style="color: red">
                ${message} </span><br>
        </#if>
        <button class="bn30 mb-2" type="submit">Редактировать занятие</button>
    </form>
                <form method="get" action="${event.id}/delete">
                    <button class="bn30 mb-2" type="submit">Удалить занятие</button>
                </form>
                <form method="get" action="${event.id}/deleteConnected">
                    <button class="bn30 mb-3" type="submit">Удалить серию занятий</button>
                </form>
        <h3>Программа занятий</h3>
        <#if programMessage??>
            <span style="color: red">
                ${programMessage} </span><br>
        </#if>

        <form action="${event.id}/trainingProgram" method="post">
            <div>
      <textarea cols="80" rows="10" id="content" name="content">
          <#if event.trainingProgram??>
              ${event.trainingProgram}
          </#if>
      </textarea>
                <script type="text/javascript">
                    CKEDITOR.replace('content');
                </script>
                <button type="submit" class="mt-3 bn30 mb-3">Отправить</button>
            </div>
        </form>

        <form method="get" action="/group/${group.id}/calendar">
            <button class="bn30 mb-3" type="submit">Назад</button>
        </form>


    <#list visit as v>
        <input hidden class="qwe" name="id" value="${v.student.id}">
    </#list>
        <table class="table table-dark table-striped mt-5">
            <thead>
            <tr>
                <th scope="col">ФИО</th>
                <th scope="col">Посещаемость</th>
            </tr>
    <#if users??>
        <form id="note">
            <input hidden name="id" value="${event.id}">
            <#list users as user>
                <tr>
                    <td>${user.fio}</td>
                    <td>
                        <input class="check btn btn-success" id="${user.id}" type="submit" value="Пришел">
                    </td>
                </tr>
            </#list>
        </form>
    </#if>
    </table>
    </div>
    <script>
        const form = document.getElementById("note");

        const buttons = document.querySelectorAll('.check');

        async function handleClick(e) {
            e.preventDefault()
            const data = new FormData(form)
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(Object.fromEntries(data))
            };
            await fetch("/note/" + e.target.id, options)

        }


        const list = document.querySelectorAll('.qwe');
        for (let i = 0; i < buttons.length; i++) {
            for (let j = 0; j < list.length; j++) {
                if (list[j].value === buttons[i].id) {
                    buttons[i].classList.remove('check', 'btn', 'btn-success')
                    buttons[i].classList.add('check', 'btn', 'btn-danger')
                    buttons[i].value = 'Не пришел'
                }
            }

        }


        buttons.forEach(b => {
            b.addEventListener('click', function () {
                if (b.value === 'Пришел') {
                    b.classList.remove('check', 'btn', 'btn-success')
                    b.classList.add('check', 'btn', 'btn-danger')
                    b.value = 'Не пришел'
                } else {
                    b.classList.remove('check', 'btn', 'btn-danger')
                    b.classList.add('check', 'btn', 'btn-success')
                    b.value = 'Пришел'
                }
            })
        })

        buttons.forEach(btn => {
            btn.addEventListener('click', handleClick)
        })
    </script>
</@main.renderWith>