<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container" style="background-color: white; ">
            <div class="admin-search text-center">
                <form method="get" action="/admin">
                    <label for="role">Роль:</label>
                    <select id="role" name="role" class="select-css">
                        <option value="all">Все</option>
                        <#list roles as role>
                            <option value="${role}">${role}</option>
                        </#list>
                    </select>
                    <label for="blocked">Заблокирован</label>
                    <select name="isActive" id="blocked" class="select-css">
                        <option value="true">Нет</option>
                        <option value="false">Да</option>
                    </select>
                    <label for="search">Поиск по ФИО</label>
                    <input id="search" type="text" name="search"  class="search-in-admin" \>
                    <button type="submit" class="bn30" style="margin-top: 20px">Искать</button>
                </form>
                <div class="buttons-in-admin">
                    <a href="/add/trainer">
                        <button class="bn30">Создать тренера</button>
                    </a>
                    <a href="/create">
                        <button class="bn30">Создать спортсмена</button>
                    </a>
                </div>

            </div>
            <div>

                <table class="table table-dark table-striped">
                    <thead>
                    <tr>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Телефон</th>
                        <th>Роль</th>
                        <th>Логин</th>
                        <th>Заблокирован</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list users as user>
                    <tr>
                        <#assign link = "/edit/${user.id}">
                        <td><a class="text-decoration-none text-reset" href="${link}">${user.surname}</a></td>
                        <td><a class="text-decoration-none text-reset" href="${link}">${user.name}</a></td>
                        <td><a class="text-decoration-none text-reset" href="${link}">${user.phone}</a></td>
                        <td><a class="text-decoration-none text-reset" href="${link}">${user.role}</a></td>
                        <td><a class="text-decoration-none text-reset" href="${link}">${(user.login)!}</a></td>
                        <td><#if user??>
                                <label for="isActive"></label><input type="checkbox" id="isActive" name="isActive"
                                                                     disabled>
                            <#else>
                                <input type="checkbox" id="isActive" name="isActive" checked disabled>
                            </#if> </td>
                    </tr>
                    </tbody>
                    </#list>
                </table>
            </div>
            <div>
            </div>

            <#include "partials/pagination.ftlh">
        </div>
    </main>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            let input = document.getElementById('role');
            if (localStorage['role']) {
                input.value = localStorage['role'];
            }
            input.onchange = function () {
                localStorage['role'] = this.value;
            }
        });
    </script>

</@main.renderWith>

