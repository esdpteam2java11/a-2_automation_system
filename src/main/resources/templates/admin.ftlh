<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript">
    </script>
    <script src=/scripts/search.js"></script>
    <title>Index</title>
</head>
<body>
<div>
       <label for="role_select">Роль:</label>
        <select name="Role_name" id="role_select" >
            <#list roles as role>
                <option value="${role}" name="role">${role}</option>
            </#list>
        </select>
        <label for="blocked">Заблокированный</label>
        <select name="blocked" id="blocked" >
            <option value="false" name="isActive">Заблокированый</option>
            <option value="true" name="isActive" selected>Активный</option>
        </select>
    <button type="button" id="search">Фильтровать</button>

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
    <#if hasPrev>
        <a id="loadPrev" href="${prevPageLink!''}"> <<< Previous page</a>
    <#else>
        <span id="loadPrev">Previous page</span>
    </#if>

    <#if hasNext>
        <a id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!2}">Next page&gt;&gt;&gt;</a>
    <#else>
        <span>Next page</span>
    </#if>
</div>

</body>
</html>