<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить спортсмена</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

<form action="/addTrainer" method="post">
    <#if _csrf??>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
    </#if>


    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">логин</p>
            <input type="text" name="login" value="${(dto.login)!''}">
        </label>
    </div>

    <div>

    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">пароль</p>
                <input type="text" class="form-control" name="password" value="${(dto.password)!''}" id="input-password">
            <button type="button" id="input-generate" class="btn btn-primary">Сгенерировать</button>
    </label>
    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">имя</p>
            <input type="text" name="name" value="${(dto.name)!''}">
        </label>
    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">фамиля</p>
            <input type="text" name="surname" value=""${(dto.surname)!''}"">
        </label>
    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">очество</p>
            <input type="text" name="patronymic" value=""${(dto.patronymic)!''}"">
        </label>
    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">адрес</p>
            <input type="text" name="address" value=""${(dto.address)!''}"">
        </label>
    </div>
    <div>
        <label class="flex flex-v-center">
            <p class="label-input-text">телефон</p>
            <input type="text" name="phone" value=""${(dto.phone)!''}"">
        </label>
    </div>

    <label class="flex flex-v-center">
        <p class="label-input-text">дата рождения</p>

    <input type="date"   name="birthDate"
           value="${(dto.birthDate)!''}"
          >
    </label>
    <input type="hidden" id="role" name="role" value="EMPLOYEE">

    <div>
        <button type="submit">добавить</button>
    </div>
    <div>
        <#if errors?? >
            <p>Входные данные с ошибками!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
    </div>
</form>
</body>

</html>

<script>
    $("#input-generate").click(function(){
        $("#input-password").val(generatePassword());
        return false;
    });

    function generatePassword(){
        var length = 8,
            charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@-#$";
        if(window.crypto && window.crypto.getRandomValues) {
            return Array(length)
                .fill(charset)
                .map(x => x[Math.floor(crypto.getRandomValues(new Uint32Array(1))[0] / (0xffffffff + 1) * (x.length + 1))])
                .join('');
        } else {
            res = '';
            for (var i = 0, n = charset.length; i < length; ++i) {
                res += charset.charAt(Math.floor(Math.random() * n));
            }
            return res;
        }
    }



</script>
</@main.renderWith>