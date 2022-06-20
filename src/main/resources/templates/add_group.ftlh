<form action="/group/add" method="post">

    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">имя</p>
            <input type="text" name="name" value="${(group.name)!''}">
        </label>
    </div>
    <div>
        <select name="trainer" id="trainer">
            <#list trainers as trainer>
                <option value="${trainer.id}">${trainer.name}</option>
            </#list>
        </select>
    </div>
    <div>
        <button type="submit">добавить</button>
    </div>
    <#if errors?? >
        <p>Входные данные с ошибками!</p>
        <#list errors as fieldError>
            <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
        </#list>
    </#if>

</form>
