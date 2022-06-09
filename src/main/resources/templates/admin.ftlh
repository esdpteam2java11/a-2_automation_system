<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
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

<#--                <td><#if user.isActive == true> -->
<#--                    <#else> <input type="radio" id="isActive" name="is" value="${user.isActive}" checked></#if></td>-->
            </tr>
        </#list>
    </table>
</div>
</body>
</html>