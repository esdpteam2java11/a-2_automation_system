<#import "partials/admin_main.ftlh" as main />

<@main.renderWith title="Добавить занятие">
    <form action="create" method="post" name="eventForm" onsubmit="return validateForm()">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div>
            <h2 class="text-center mt-3 mb-3">Форма создания занятия или занятий для спортсмена:</h2>
            <h3 class="text-center mb-5">${sportsman.name}</h3>
        </div>
        <div id="eventCreationForm" class="container">
            <label class="flex flex-v-center required" for="date">
                Дата:
            </label>
            <input type="date" name="eventDate" id="date" min="${.now?date?iso_utc}" required>
            <button class="bn30 ml-5" type="submit">Добавить</button>
                <a href="/sportsman_cabinet/" class="bn5 ml-5">Назад</a>
            <br>
            <#if errorDate??>
                <span style="color: red">
                ${errorDate} </span><br>
            </#if>
            <#if errorDayOfWeek??>
                <span style="color: red">
                ${errorDayOfWeek} </span><br>
            </#if>
            <#if errors?? >
                <p>Входные данные с ошибками!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>

            <label class="flex flex-v-center" for="recurring" id="reoccurringLabel">
                Повторяющиеся занятие:
                <input type="checkbox" name="recurring" id="recurring">
            </label>

        </div>
    </form>
    <script>
        // function validateForm() {
        //     let eventStart = new Date(document.forms["eventForm"]["eventDate"].value);
        //     // let eventEnd = new Date(document.forms["eventForm"]["dateEnd"].value);
        //     // if (eventEnd!== null && eventEnd < eventStart) {
        //     //     let span = document.getElementById('messageForDate');
        //     //     if(span!==null){
        //     //         span.remove();
        //     //     }
        //         //const endDate = document.getElementById("labelForEndDate");
        //        // const createSpan = document.createElement("span");
        //        // const checkbox = document.getElementsByName("dayOfWeek[]");
        //         console.log(checkbox.values());
        //         createSpan.setAttribute('class','flex flex-v-center');
        //         createSpan.setAttribute('style','color:red');
        //         createSpan.setAttribute('id','messageForDate');
        //         createSpan.innerHTML = '&nbsp;неправильная дата';
        //         endDate.append(createSpan);
        //         return false;
        //     }
        // }
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
            const additionalDiv = document.createElement('div')
            additionalDiv.setAttribute('class','flex flex-v-center');
            additionalDiv.setAttribute('id','additionalDiv');
            eventCreationForm.append(additionalDiv);

            const endDateLabel = document.createElement('label');
            endDateLabel.setAttribute('class', 'flex flex-v-center');
            endDateLabel.setAttribute('id','labelForEndDate');
            endDateLabel.setAttribute('for','endDate');
            additionalDiv.append(endDateLabel);

            endDateLabel.innerHTML ='Дата окончания:'+
                    '<input type="date" name="dateEnd" id="dateEnd" min="${(.now?long + 86400000)?number_to_date?iso_utc}" required>';

            const br1 = document.createElement('br');
            br1.setAttribute('id','br1')
            additionalDiv.append(br1);

            const mondayLabel = document.createElement('label');
            mondayLabel.setAttribute('class', 'flex flex-v-center');
            mondayLabel.setAttribute('id','labelMonday')
            mondayLabel.setAttribute('for','monday')
            additionalDiv.append(mondayLabel);

            mondayLabel.innerHTML = 'Пон:'+
                '<input type="checkbox" name="dayOfWeek[]" id="monday" value="MONDAY">';

            const br2 = document.createElement('br');
            br2.setAttribute('id','br2')
            additionalDiv.append(br2);

            const tuesdayLabel = document.createElement('label');
            tuesdayLabel.setAttribute('class', 'flex flex-v-center');
            tuesdayLabel.setAttribute('id','labelTuesday')
            tuesdayLabel.setAttribute('for','tuesday')
            additionalDiv.append(tuesdayLabel);

            tuesdayLabel.innerHTML = 'Вто:'+
                '<input type="checkbox" name="dayOfWeek[]" id="tuesday" value="TUESDAY">';

            const br3 = document.createElement('br');
            br3.setAttribute('id','br3')
            additionalDiv.append(br3);

            const wednesdayLabel = document.createElement('label');
            wednesdayLabel.setAttribute('class', 'flex flex-v-center');
            wednesdayLabel.setAttribute('id','labelWednesday')
            wednesdayLabel.setAttribute('for','wednesday')
            additionalDiv.append(wednesdayLabel);

            wednesdayLabel.innerHTML = 'Сре:'+
                '<input type="checkbox" name="dayOfWeek[]" id="wednesday" value="WEDNESDAY">';

            const br4 = document.createElement('br');
            br4.setAttribute('id','br4')
            additionalDiv.append(br4);

            const thursdayLabel = document.createElement('label');
            thursdayLabel.setAttribute('class', 'flex flex-v-center');
            thursdayLabel.setAttribute('id','labelThursday')
            thursdayLabel.setAttribute('for','thursday')
            additionalDiv.append(thursdayLabel);

            thursdayLabel.innerHTML = 'Чет:'+
                '<input type="checkbox" name="dayOfWeek[]" id="thursday" value="THURSDAY">';

            const br5 = document.createElement('br');
            br5.setAttribute('id','br5')
            additionalDiv.append(br5);

            const fridayLabel = document.createElement('label');
            fridayLabel.setAttribute('class', 'flex flex-v-center');
            fridayLabel.setAttribute('id','labelFriday')
            fridayLabel.setAttribute('for','friday')
            additionalDiv.append(fridayLabel);

            fridayLabel.innerHTML = 'Пят:'+
                '<input type="checkbox" name="dayOfWeek[]" id="friday" value="FRIDAY">';

            const br6 = document.createElement('br');
            br6.setAttribute('id','br6')
            additionalDiv.append(br6);

            const saturdayLabel = document.createElement('label');
            saturdayLabel.setAttribute('class', 'flex flex-v-center');
            saturdayLabel.setAttribute('id','labelSaturday')
            saturdayLabel.setAttribute('for','saturday')
            additionalDiv.append(saturdayLabel);

            saturdayLabel.innerHTML = 'Суб:'+
                '<input type="checkbox" name="dayOfWeek[]" id="saturday" value="SATURDAY">';

            const br7 = document.createElement('br');
            br7.setAttribute('id','br7')
            additionalDiv.append(br7);

            const sundayLabel = document.createElement('label');
            sundayLabel.setAttribute('class', 'flex flex-v-center');
            sundayLabel.setAttribute('id','labelSunday')
            sundayLabel.setAttribute('for','sunday')
            additionalDiv.append(sundayLabel);

            sundayLabel.innerHTML = 'Вос:'+
                '<input type="checkbox" name="dayOfWeek[]" id="sunday" value="SUNDAY">';
        }

        function unChecked(){
            const additionalDiv = document.getElementById('additionalDiv');
            additionalDiv.remove();
        }
    </script>
</@main.renderWith>