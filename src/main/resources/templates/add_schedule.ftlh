<#import "partials/admin_main.ftlh" as main />
<@main.renderWith title="Добавить занятие">
    <body>
    <form action="add" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div>
            <h2>Форма создания занятия или занятий для группы:</h2>
            <h3>${group.name}</h3>
        </div>
        <div id="eventCreationForm">
            <input name="group" value="${group.id}" hidden>
            <label class="flex flex-v-center" for="date">
                Дата:
                <input type="date" name="eventStartDate" id="date" min="${.now?date?iso_utc}" required>
            </label>
            <br>
            <label class="flex flex-v-center" for="timeStart">
                Время начала:
                <input type="time" name="timeStart" id="timeStart" min="08:00" max="19:00" step="1800" required>
            </label>
            <label class="flex flex-v-center" for="timeEnd">
                Время конца:
                <input type="time" name="timeEnd" id="timeEnd" min="10:00" max="21:00" step="1800" required>
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
            endDateLabel.setAttribute('id','labelForEndDate');
            endDateLabel.setAttribute('for','endDate');
            eventCreationForm.append(endDateLabel);

            endDateLabel.innerHTML ='Дата окончания:'+
                    '<input type="date" name="dateEnd" id="dateEnd" min="${(.now?long + 86400000)?number_to_date?iso_utc}" required>';

            const br1 = document.createElement('br');
            br1.setAttribute('id','br1')
            eventCreationForm.append(br1);

            const mondayLabel = document.createElement('label');
            mondayLabel.setAttribute('class', 'flex flex-v-center');
            mondayLabel.setAttribute('id','labelMonday')
            mondayLabel.setAttribute('for','monday')
            eventCreationForm.append(mondayLabel);

            mondayLabel.innerHTML = 'Пон:'+
                '<input type="checkbox" name="dayOfWeek[]" id="monday" value="MONDAY">';

            const br2 = document.createElement('br');
            br2.setAttribute('id','br2')
            eventCreationForm.append(br2);

            const tuesdayLabel = document.createElement('label');
            tuesdayLabel.setAttribute('class', 'flex flex-v-center');
            tuesdayLabel.setAttribute('id','labelTuesday')
            tuesdayLabel.setAttribute('for','tuesday')
            eventCreationForm.append(tuesdayLabel);

            tuesdayLabel.innerHTML = 'Вто:'+
                '<input type="checkbox" name="dayOfWeek[]" id="tuesday" value="TUESDAY">';

            const br3 = document.createElement('br');
            br3.setAttribute('id','br3')
            eventCreationForm.append(br3);

            const wednesdayLabel = document.createElement('label');
            wednesdayLabel.setAttribute('class', 'flex flex-v-center');
            wednesdayLabel.setAttribute('id','labelWednesday')
            wednesdayLabel.setAttribute('for','wednesday')
            eventCreationForm.append(wednesdayLabel);

            wednesdayLabel.innerHTML = 'Сре:'+
                '<input type="checkbox" name="dayOfWeek[]" id="wednesday" value="WEDNESDAY">';

            const br4 = document.createElement('br');
            br4.setAttribute('id','br4')
            eventCreationForm.append(br4);

            const thursdayLabel = document.createElement('label');
            thursdayLabel.setAttribute('class', 'flex flex-v-center');
            thursdayLabel.setAttribute('id','labelThursday')
            thursdayLabel.setAttribute('for','thursday')
            eventCreationForm.append(thursdayLabel);

            thursdayLabel.innerHTML = 'Чет:'+
                '<input type="checkbox" name="dayOfWeek[]" id="thursday" value="THURSDAY">';

            const br5 = document.createElement('br');
            br5.setAttribute('id','br5')
            eventCreationForm.append(br5);

            const fridayLabel = document.createElement('label');
            fridayLabel.setAttribute('class', 'flex flex-v-center');
            fridayLabel.setAttribute('id','labelFriday')
            fridayLabel.setAttribute('for','friday')
            eventCreationForm.append(fridayLabel);

            fridayLabel.innerHTML = 'Пят:'+
                '<input type="checkbox" name="dayOfWeek[]" id="friday" value="FRIDAY">';

            const br6 = document.createElement('br');
            br6.setAttribute('id','br6')
            eventCreationForm.append(br6);

            const saturdayLabel = document.createElement('label');
            saturdayLabel.setAttribute('class', 'flex flex-v-center');
            saturdayLabel.setAttribute('id','labelSaturday')
            saturdayLabel.setAttribute('for','saturday')
            eventCreationForm.append(saturdayLabel);

            saturdayLabel.innerHTML = 'Суб:'+
                '<input type="checkbox" name="dayOfWeek[]" id="saturday" value="SATURDAY">';

            const br7 = document.createElement('br');
            br7.setAttribute('id','br7')
            eventCreationForm.append(br7);

            const sundayLabel = document.createElement('label');
            sundayLabel.setAttribute('class', 'flex flex-v-center');
            sundayLabel.setAttribute('id','labelSunday')
            sundayLabel.setAttribute('for','sunday')
            eventCreationForm.append(sundayLabel);

            sundayLabel.innerHTML = 'Вос:'+
                '<input type="checkbox" name="dayOfWeek[]" id="sunday" value="SUNDAY">';
        }

        function sendPost(){

        }
        function unChecked(){
            const labelForEnd = document.getElementById('labelForEndDate');
            const br = document.getElementById('br')
            const br1 = document.getElementById('br1')
            const br2 = document.getElementById('br2')
            const br3 = document.getElementById('br3')
            const br4 = document.getElementById('br4')
            const br5 = document.getElementById('br5')
            const br6 = document.getElementById('br6')
            const br7 = document.getElementById('br7')
            const monday = document.getElementById('labelMonday');
            const tuesday = document.getElementById('labelTuesday');
            const wednesday = document.getElementById('labelWednesday');
            const thursday = document.getElementById('labelThursday');
            const friday = document.getElementById('labelFriday');
            const saturday = document.getElementById('labelSaturday');
            const sunday = document.getElementById('labelSunday');
            br.remove();
            labelForEnd.remove();
            br1.remove();
            br2.remove();
            br3.remove();
            br4.remove();
            br5.remove();
            br6.remove();
            br7.remove();
            monday.remove();
            tuesday.remove();
            wednesday.remove();
            thursday.remove();
            friday.remove();
            saturday.remove();
            sunday.remove();
        }
    </script>
    </body>

    </html>
</@main.renderWith>