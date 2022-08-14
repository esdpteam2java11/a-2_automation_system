<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container pb-2 mb-5" style="background-color: white; ">
            <div class="admin-search text-center">
                <form method="get">
                    <div class="mx-auto mt-5" style="width: 50%">
                        <label for="fio" class="col-form-label-lg">Поиск по ФИО</label>
                        <select class="js-selectize" name="userId" id="fio">
                            <option value=""></option>
                            <#list users as user>
                                <option value="${user.id}">${user.fio}</option>
                            </#list>
                        </select>
                    </div>
                    <button type="submit" class="bn30" style="margin-top: 20px">Искать</button>
                    <a href="/admin">
                        <button type="button" class="bn30">Назад</button>
                    </a>
                </form>
            </div>
            <table class="table table-dark table-striped">
                <thead>
                <tr>
                    <th>Рост</th>
                    <th>Вес</th>
                    <th>Дата</th>
                </tr>
                </thead>
                <tbody>
                <div>
                    <h4>История роста и веса</h4>
                </div>
                <#list sportsmanParams as sportsmanParam>
                <tr>
                    <td><a class="text-decoration-none text-reset">${sportsmanParam.height}</a></td>
                    <td><a class="text-decoration-none text-reset">${sportsmanParam.weight}</a></td>
                    <td><a class="text-decoration-none text-reset">${sportsmanParam.creationDate}</a></td>
                </tr>
                </tbody>
                </#list>
            </table>
            <table class="table table-dark table-striped mb-5">
                <thead>
                <tr>
                    <th>Тип операций</th>
                    <th>Сумма</th>
                    <th>Период оплаты</th>
                </tr>
                </thead>
                <tbody>
                <div>
                    <h4>История оплат и начислений</h4>
                </div>
                <#list sportsmanPayments as sportsmanPayment>
                <tr>
                    <td><a class="text-decoration-none text-reset">${sportsmanPayment.operationType.type}</a></td>
                    <td><a class="text-decoration-none text-reset">${sportsmanPayment.amount}</a></td>
                    <td><a class="text-decoration-none text-reset">${sportsmanPayment.date}</a></td>
                </tr>
                </tbody>
                </#list>
            </table>
        </div>
    </main>
    <script src="/js/script.js"></script>
</@main.renderWith>

