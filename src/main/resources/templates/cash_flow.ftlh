<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color main-color-sportsman">
        <div class="container col-xxl-8 pb-2 mb-5" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="cash-flow-header text-center mb-4 py-4" style="color: black">Кассовые операции</h2>
                <form action="/cash" method="get">
                    <div class="border border-secondary mb-3">
                        <div class="row my-3 mx-4">
                            <div class="col-sm-3 pl-4">
                                <label for="fio" class="col-form-label">Поиск по ФИО:</label>
                            </div>
                            <div class="col-sm-9 pl-0">
                                <select class="js-selectize" name="userId" id="fio">
                                    <option value=""></option>
                                    <#list users as user>
                                        <option value="${user.id}">${user.fio}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="row m-3">
                            <div class="col-lg-6">
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="type-of-finance">Тип движения:</label>
                                    <select class="form-control col-sm-6" name="typeOfFinance" id="type-of-finance">
                                        <option selected value> -- все --</option>
                                        <#list typesOfFinance as typeOfFinance>
                                            <option value="${typeOfFinance}">${typeOfFinance.rusValue}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="operation-type">Вид операции:</label>
                                    <select class="form-control col-sm-6" name="operationType" id="operation-type">
                                        <option selected value> -- все --</option>
                                        <#list operationTypes as operationType>
                                            <option value="${operationType}">${operationType.rusValue}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="period-start">Период с:</label>
                                    <input type="date" name="periodStart" class="form-control col-sm-6"
                                           id="period-start" value="${(.now?long - (86400000*6))
                                    ?number_to_date?iso_utc}" max="${
                                    .now?date?iso_utc}" required>
                                </div>
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="period-end">Период по:</label>
                                    <input type="date" name="periodEnd" class="form-control col-sm-6"
                                           id="period-end" value="${.now?date?iso_utc}" required>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-4 text-center">
                                <button type="submit" class="bn30" style="margin-top: 20px">Применить фильтры</button>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="/cash/INCOME">
                                    <button type="button" class="bn30" style="margin-top: 20px">Приход
                                        денег
                                    </button>
                                </a>
                            </div>
                            <div class="col-sm-4 text-center">
                                <a href="/cash/DISCHARGE">
                                    <button type="button" class="bn30" style="margin-top: 20px">Расход денег</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </form>

                <div class="table-responsive">
                    <table id="cash-flow-table" class="table table-striped table-hover align-middle">
                        <thead>
                        <tr>
                            <th scope="col" hidden>ID</th>
                            <th scope="col">Дата</th>
                            <th scope="col">ФИО</th>
                            <th scope="col">Тип движения</th>
                            <th scope="col">Вид операции</th>
                            <th scope="col">Сумма</th>
                            <th scope="col">Комментарий</th>
                        </tr>
                        </thead>
                        <tbody class="table-body">
                        <#if moneyMovements?size!=0>
                            <#list moneyMovements as moneyMovement>
                                <tr>
                                    <#assign link = "/edit/${moneyMovement.id}">
                                    <td hidden>${moneyMovement.id}</td>
                                    <td><a class="text-decoration-none text-reset" href="${link}">
                                            ${moneyMovement.date}</a></td>
                                    <td><a class="text-decoration-none text-reset"
                                           href="${link}">${moneyMovement.counterpartyFIO}</a></td>
                                    <td><a class="text-decoration-none text-reset"
                                           href="${link}">${moneyMovement.typeOfFinance.rusValue}</a></td>
                                    <td><a class="text-decoration-none text-reset"
                                           href="${link}">${moneyMovement.moneyOperationType.rusValue}</a></td>
                                    <td><a class="text-decoration-none text-reset"
                                           href="${link}">${moneyMovement.amount}</a></td>
                                    <td><a class="text-decoration-none text-reset"
                                           href="${link}">${moneyMovement.purpose}</a></td>
                                </tr>
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </main>
    <script src="/js/script.js"></script>
</@main.renderWith>