<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <form method="post">
        <div>
            <label for="name">Имя: </label>
            <input type="text" name="name" id="name">
        </div>
        <div>
            <label for="surname">Фамилия: </label>
            <input type="text" name="surname" id="surname">
        </div>
        <div>
            <label for="patronymic">Отчество: </label>
            <input type="text" name="patronymic" id="patronymic">
        </div>
        <div>
            <label for="birthDate">Дата рождения: </label>
            <input type="date" name="birthDate" id="birthDate"/>
        </div>
        <div>
            <label for="phone">Телефон: </label>
            <input type="text" name="phone" id="phone"/>
        </div>
        <div>
            <label for="whatsapp">Вотсап: </label>
            <input type="text" name="whatsapp" id="whatsapp"/>
        </div>
        <div>
            <label for="telegram">Телеграм: </label>
            <input type="text" name="telegram" id="telegram"/>
        </div>
        <div class="closed" onclick="showCloseItem(this)">
            <p>Родители</p>
            <div class="hiddenItem">
                <#list parents as parent>
                    <div class="container">
                        <input value="${parent.name}"/>
                        <input value="${parent.surname}"/>
                        <input value="${parent.patronymic}"/>
                        <input value="${parent.phone}"/>
                        <input value="${parent.kinship}"/>
                    </div>
                </#list>
            </div>
        </div>
        <div>
            <label for="role">Роль: </label>
            <input type="text" name="role" id="role"/>
        </div>
        <div>
            <label for="login">Логин: </label>
            <input type="text" name="login" id="login"/>
        </div>
        <div>
            <label for="password">Пароль: </label>
            <input type="password" name="password" id="password"/>
            <button>Генерация пароля</button>
        </div>
        <div>
            <label for="isActive">Заблокировать: </label>
            <select name="isActive" id="isActive">
                <option value="true">Нет</option>
                <option value="false">Да</option>
            </select>
        </div>
    </form>
    <script>
        function showCloseItem(item) {
            $(item).toggleClass('closed', 'opened');
        };
    </script>
</@main.renderWith>