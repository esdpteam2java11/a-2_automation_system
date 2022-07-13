<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container" style="background-color: white; ">
            <div class="admin-search text-center">
                <form method="get" action="/admin" id="admin">
                    <label for="role">Роль:</label>
                    <select id="role" name="role" class="select-css">
                        <option value="all">Все</option>
                        <#list roles as role>
                            <option value="${role}">${role.role}</option>
                        </#list>
                    </select>
                    <label for="blocked">Заблокирован</label>
                    <select name="isActive" id="blocked" class="select-css">
                        <option value="true">Нет</option>
                        <option value="false">Да</option>
                    </select>
                    <label for="search">Поиск по ФИО</label>
                    <input id="search" type="text" name="search" class="search-in-admin" \>
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
                    buttons[i].innerHTML = 'Заблокировать'
                } else {
                    buttons[i].innerHTML = 'Разблокировать'
                }
            }
        }

        buttons.forEach(btn => {
            btn.addEventListener('click', handleClick)
        })


    </script>

</@main.renderWith>

