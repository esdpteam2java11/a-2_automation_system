<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
<form action="http://localhost:8080/group/edit" method="post" modelAttribute="group">
    <p>Группы</p>
    <div>
        <select name="id" >
            <label class="flex flex-v-center">
                <p class="label-input-text">Группа</p>
                <option value="${group.id}">${group.name}</option>
            </label>
        </select>
    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">имя</p>
            <input type="text" name="name" value="${(group.name)!''}">
        </label>
    </div>

    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">Цена</p>
            <input type="number" name="sum" value="${(group.sum)!''}">
        </label>
    </div>
    <p>Трейнеры</p>
    <div>
        <select name="trainer" id="trainer">
            <label class="flex flex-v-center">
            <p class="label-input-text">Трейнеры</p>
            <#list trainers as trainer>
                <option value="${trainer.id}">${trainer.name}</option>
            </#list>
            </label>
        </select>
    </div>
    <div>
        <button type="submit">Изменить</button>
    </div>


</form>
</@main.renderWith>