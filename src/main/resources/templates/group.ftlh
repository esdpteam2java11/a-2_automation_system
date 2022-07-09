<#import "partials/admin_main.ftlh" as main />
<@main.renderWith>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Название группы</th>
            <th scope="col">Имя тренера</th>
            <th scope="col">Цена</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${group.name}</td>
            <td>${group.trainer.name}</td>
            <td>${group.sum}</td>
        </tr>
        </tbody>
    </table>
    <table>
        <tr>
            <td>
                <form method="get" action="${group.id}/edit">
                    <input type="submit" value="Редактировать группы" class="btn btn-primary">
                </form>
            </td>
            <td>
                <form method="get" action="${group.id}/calendar">
                    <input type="submit" value="Календарь группы" class="btn btn-primary">
                </form>
            </td>
        </tr>
    </table>
    <table class="table table-bordered align-middle">

        <table class="table table-bordered align-middle">
            <tr>
                <th>ФИО</th>
                <th>Возрост</th>
                <th>Телефон</th>
                <th>Стоимость обучения</th>

            </tr>
             <#if users??>
            <#list users as user>
                <tr>
                    <input type="hidden" value="${user.id}" name="id">
                    <td>${user.fio}</td>
                    <td  id="result">${((.now?date?long - user.birthDate?date?long)/(1000*60*60*24*365))?int}</td>
                    <td>${user.phone}</td>
                    <td>${user.amount}</td>

                </tr>
            </#list>
                 <#else >
                 gtby5
             </#if>
        </table>

    </table>


</@main.renderWith>