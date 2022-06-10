<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript">
    </script>
    <title>Index</title>
</head>
<body>
<div>
    <form method="get" action="/admin">
        <label for="role_select">Роль:</label>
        <select name="role" id="role">
            <option value="all">Все</option>
            <#list roles as role>
                <option value="${role}">${role}</option>
            </#list>
        </select>
        <label for="blocked">Заблокирован</label>
        <select name="isActive" id="blocked">
            <option value="true">Нет</option>
            <option value="false">Да</option>
        </select>
        <input type="submit" value="Искать">
    </form>

    <form method="get" action="/create">
        <input type="submit" value="Создать пользователя">
    </form>

</div>
<div>

    <table>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Телефон</th>
            <th>Роль</th>
            <th>Логин</th>
            <th>Заблокирован</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.surname}</td>
                <td>${user.name}</td>
                <td>${user.phone}</td>
                <td>${user.role}</td>
                <td>${user.login}</td>
                <td><#if user.isActive == true>
                        <input type="checkbox" id="isActive" name="isActive" disabled>
                    <#else>
                        <input type="checkbox" id="isActive" name="isActive" checked disabled>
                    </#if> </td>
            </tr>
        </#list>
    </table>
</div>
<div>

        <#include "pagination.ftlh">
</div>


<script>
    // document.getElementById("role").selectedIndex = -1;
    document.addEventListener('DOMContentLoaded', function () {
        var input = document.getElementById('role');
        if (localStorage['role']) {
            input.value = localStorage['role'];
        }
        input.onchange = function () {
            localStorage['role'] = this.value;
        }
    });
</script>
</body>
</html>