<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <div>
        <h2 class="text-center mb-5">${group.name}</h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Название группы</th>
                <th scope="col">Имя тренера</th>
                <th scope="col">Цена</th>
                <th>Количество спортсменов</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${group.name}</td>
                <td>${group.trainer.name}</td>
                <td>${group.sum}</td>
                <td>${count}</td>
            </tr>
            </tbody>
        </table>
        <table>
            <div class="text-center">
                <a href="${group.id}/edit">
                    <button class="bn30 mb-5">Редактировать группы</button>
                </a>
                <a href="${group.id}/calendar" class="calendar-ref">
                    <button class="bn30">Календарь группы</button>
                </a>
            </div>
        </table>
        <table class="table table-bordered align-middle">

            <table class="table table-bordered align-middle">
                <tr>
                    <th>ФИО</th>
                    <th>Возраст</th>
                    <th>Телефон</th>
                    <th>Стоимость обучения</th>
                </tr>
                <#if users??>
                    <#list users as user>
                        <tr>
                            <input type="hidden" value="${user.id}" name="id">
                            <td>${user.fio}</td>
                            <td id="result">${((.now?date?long - user.birthDate?date?long)/(1000*60*60*24*365))?int}</td>
                            <td>${user.phone}</td>
                            <td>${user.amount}</td>
                        </tr>
                    </#list>
                </#if>
            </table>

        </table>
    </div>


</@main.renderWith>