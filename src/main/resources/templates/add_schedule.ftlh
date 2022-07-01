<#import "partials/admin_main.ftlh" as main />
<@main.renderWith title="Добавить занятие">
    <body>
    <form action="/add/event" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div>
            <h2>Форма создания занятия или занятий для группы:</h2>
            <h3>${(group.name)}</h3>
        </div>
        <div id="eventCreationForm">
            <label class="flex flex-v-center" for="date">
                Дата:
                <input type="date" name="date" id="date" required>
            </label>
            <label class="flex flex-v-center" for="timeStart">
                Время начала:
                <input type="time" name="timeStart" id="timeStart" required>
            </label>
            <label class="flex flex-v-center" for="timeEnd">
                Время конца:
                <input type="time" name="timeEnd" id="timeEnd" required>
            </label>
            <br>
            <label class="flex flex-v-center" for="recurring" id="reoccurringLabel">
                Повторяющеяся занятие:
                <input type="checkbox" name="recurring" id="recurring">
            </label>

        </div>
        <div>
            <button type="submit">Добавить</button>
        </div>

    </form>
    <script>
        const checkbox = document.getElementById('recurring');
        checkbox.addEventListener('click',handleClick,false);
        function handleClick(e) {
            let checked = e.path[0].checked;
            console.log(e.path[0].checked);
            if(checked===true){
                onChecked();
            }
            if(checked===false){
                unChecked();
            }
        }

        function onChecked(){
            const eventCreationForm = document.getElementById('eventCreationForm');

            const br = document.createElement('br');
            br.setAttribute('id','br')
            eventCreationForm.append(br);

            const endDateLabel = document.createElement('label');
            endDateLabel.setAttribute('class', 'flex flex-v-center');
            endDateLabel.setAttribute('id','labelForEndDate')
            endDateLabel.setAttribute('for','endDate')
            eventCreationForm.append(endDateLabel);

            endDateLabel.innerHTML ='Дата окончания:'+
                    '<input type="date" name="dateEnd" id="dateEnd">';

            const br1 = document.createElement('br');
            br1.setAttribute('id','br')
            eventCreationForm.append(br1);

            const mondayLabel = document.createElement('label');
            mondayLabel.setAttribute('class', 'flex flex-v-center');
            mondayLabel.setAttribute('id','labelMonday')
            mondayLabel.setAttribute('for','monday')
            eventCreationForm.append(mondayLabel);

            mondayLabel.innerHTML = 'Пон:'+
                '<input type="checkbox" name="monday" id="monday" value="Monday">';

            const tuesdayLabel = document.createElement('label');
            tuesdayLabel.setAttribute('class', 'flex flex-v-center');
            tuesdayLabel.setAttribute('id','labelTuesday')
            tuesdayLabel.setAttribute('for','tuesday')
            eventCreationForm.append(tuesdayLabel);

            tuesdayLabel.innerHTML = 'Вто:'+
                '<input type="checkbox" name="tuesday" id="tuesday" value="Tuesday">';

            const wednesdayLabel = document.createElement('label');
            wednesdayLabel.setAttribute('class', 'flex flex-v-center');
            wednesdayLabel.setAttribute('id','labelWednesday')
            wednesdayLabel.setAttribute('for','wednesday')
            eventCreationForm.append(wednesdayLabel);

            wednesdayLabel.innerHTML = 'Сре:'+
                '<input type="checkbox" name="wednesday" id="wednesday" value="Wednesday">';
        }
        function unChecked(){
            const labelForEnd = document.getElementById('labelForEndDate');
            const br = document.getElementById('br')
            br.remove();
            labelForEnd.remove();
        }
    </script>
    </body>

    </html>
</@main.renderWith>