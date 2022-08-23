<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container" style="background-color: white; ">
            <div class="admin-search text-center">
                <form method="get" action="/admin" id="admin" class="pt-3">
                    <label for="role" class="mx-2">Роль:</label>
                    <select id="role" name="role" class="select-css">
                        <option value="" selected></option>
                        <#list roles as role>
                            <#if role.role != 'Администратор'>
                                <option value="${role}">${role.role}</option>
                            </#if>
                        </#list>
                    </select>
                    <label for="blocked" class="mx-2">Заблокирован:</label>
                    <select name="isActive" id="blocked" class="select-css">
                        <option value="" selected></option>
                        <option value="true">Нет</option>
                        <option value="false">Да</option>
                    </select>
                    <label for="search" class="mx-2">Поиск по ФИО</label>
                    <input id="search" type="text" name="search" class="search-in-admin" \>
                    <button type="submit" class="bn30 mx-2" style="margin-top: 20px">Искать</button>
                </form>
                <div class="buttons-in-admin">
                    <a href="/add/trainer">
                        <button class="bn30 mx-2">Создать тренера</button>
                    </a>
                    <a href="/create">
                        <button class="bn30 mx-2">Создать спортсмена</button>
                    </a>
                </div>

            </div>
            <table class="table table-dark table-striped">
                <thead>
                <tr>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Телефон</th>
                    <th>Роль</th>
                    <th>Логин</th>
                    <th>Статус</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                <tr>
                    <#assign link = "/edit/${user.id}">
                    <td><a class="text-decoration-none text-reset" href="${link}">${user.surname}</a></td>
                    <td><a class="text-decoration-none text-reset" href="${link}">${user.name}</a></td>
                    <td><a class="text-decoration-none text-reset" href="${link}">${user.phone}</a></td>
                    <td><a class="text-decoration-none text-reset" href="${link}">${user.role.role}</a></td>
                    <td><a class="text-decoration-none text-reset" href="${link}">${(user.login)!}</a></td>
                    <form id="status">
                        <td>
                            <input class="isActive" type="hidden" name="isActive" value="${user.isActive?string}"/>
                            <input hidden class="role-admin" name="role" value="${user.role.role}">
                            <button id="${user.id}" class="block btn btn-danger" type="submit">Заблокировать</button>
                        </td>
                    </form>
                </tr>
                </tbody>
                </#list>
            </table>
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

        const buttons = document.querySelectorAll('.block');
        const form = document.getElementById("status");
        const isActive = document.querySelectorAll('.isActive')
        const admin = document.querySelectorAll('.role-admin')

        for (let i = 0; i < admin.length; i++) {
            if (admin[i].value === 'Администратор')
                buttons[i].remove();
        }

        async function handleClick(e) {
            e.preventDefault()
            const data = new FormData(form)
            const options = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: data
            };

            await fetch("/admin/status/" + e.target.id, options)
        }

        buttons.forEach(b => {
            b.addEventListener('click', function () {
                if (b.innerHTML === 'Заблокировать') {
                    b.classList.remove('block', 'btn', 'btn-danger')
                    b.classList.add('block', 'btn', 'btn-primary')
                    b.innerHTML = 'Разблокировать'
                } else {
                    b.classList.remove('block', 'btn', 'btn-primary')
                    b.classList.add('block', 'btn', 'btn-danger')
                    b.innerHTML = 'Заблокировать'
                }
            })
        })

        changeButton()

        function changeButton() {
            for (let i = 0; i < isActive.length; i++) {
                if (isActive[i].value === 'true') {
                    buttons[i].classList.remove('block', 'btn', 'btn-primary')
                    buttons[i].classList.add('block', 'btn', 'btn-danger')
                    buttons[i].innerHTML = 'Заблокировать'
                } else {
                    buttons[i].classList.remove('block', 'btn', 'btn-danger')
                    buttons[i].classList.add('block', 'btn', 'btn-primary')
                    buttons[i].innerHTML = 'Разблокировать'
                }
            }
        }

        buttons.forEach(btn => {
            btn.addEventListener('click', handleClick)
        })

    </script>

</@main.renderWith>

