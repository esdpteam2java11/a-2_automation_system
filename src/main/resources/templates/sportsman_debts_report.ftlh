<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color-sportsman">
        <div class="container col-xxl-8 pb-2 mb-5" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="text-center mb-5 pt-3" style="color: #000000">Отчет об оплате и начислениях по
                    спортсмену</h2>
                <form action="/sportsman_debts" method="get" id="sportsman_debts-report">
                    <div class="row mb-3">
                        <div class="col-sm-3">
                            <label for="fio" class="col-form-label required">Поиск по ФИО:</label>
                        </div>
                        <div class="col-sm-9">
                            <select class="js-selectize" name="userId" id="fio" onchange="getGroupName(this.value);
                            clearReport()" required>
                                <option value=""></option>
                                <#list users as user>
                                    <option value="${user.id}">${user.fio}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col-sm-3 mt-2">
                            <label class="col-form-label" for="group-name">Группа:</label>
                        </div>
                        <div class="col mt-2">
                            <input type="text" name="group" class="form-control"
                                   id="group-name" readonly="readonly">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-2">
                            <label class="col-form-label">Период отчета </label>
                        </div>
                        <label class="col-form-label required" for="start-date">с: </label>
                        <div class="col">
                            <input type="month" name="startDate" class="form-control"
                                   id="start-date" onchange="clearReport()" required>
                        </div>
                        <label class="col-form-label required" for="end-date">по: </label>
                        <div class="col">
                            <input type="month" name="endDate" class="form-control"
                                   id="end-date" max="${.now?date?iso_utc}" onchange="clearReport()" required>
                        </div>
                        <div class="col text-center">
                            <button type="submit" class="bn30 my-0">Сформировать отчет</button>
                        </div>
                    </div>
                </form>
                <div class="row mb-3">
                    <div class="col">
                        <div class="table-responsive">
                            <table id="report-table" class="table table-bordered align-middle">
                                <caption id="report-caption" style="caption-side: top">${(title)!}</caption>
                                <thead>
                                <tr>
                                    <th scope="col" class="align-middle" style="width: 28%">Месяц-год
                                    </th>
                                    <th scope="col" class="align-middle" style="width: 12%">Тариф группы за месяц
                                    </th>
                                    <th scope="col" class="align-middle" style="width: 12%">Тариф спортсмена
                                        за месяц
                                    </th>
                                    <th scope="col" class="align-middle" style="width: 12%">Сумма скидки</th>
                                    <th scope="col" class="align-middle" style="width: 12%">Оплаты за месяц</th>
                                    <th scope="col" class="align-middle" style="width: 12%">Остаток долга на начало
                                        месяца
                                    </th>
                                    <th scope="col" class="align-middle" style="width: 12%">Остаток долга на конец
                                        месяца
                                    </th>
                                </tr>
                                </thead>
                                <tbody class="table-body">
                                <#assign groupPriceTotal=0 amountAccrued=0 discount=0 amountPaid=0>
                                <#list (sportsmenPayments)! as sportsmanPayment>
                                    <tr>
                                        <td>${sportsmanPayment.yearMonth}</td>
                                        <td>${sportsmanPayment.groupPrice}</td>
                                        <#assign groupPriceTotal+=sportsmanPayment.groupPrice>
                                        <td>${(sportsmanPayment.amountAccrued)!0}</td>
                                        <#assign amountAccrued+=sportsmanPayment.amountAccrued>
                                        <td>${sportsmanPayment.groupPrice - sportsmanPayment.amountAccrued}</td>
                                        <#assign discount+=(sportsmanPayment.groupPrice -
                                        sportsmanPayment.amountAccrued)>
                                        <td>${(sportsmanPayment.amountPaid)!0}</td>
                                        <#assign amountPaid+=sportsmanPayment.amountPaid>
                                        <td>${(sportsmanPayment.startBalance)!0}</td>
                                        <td>${(sportsmanPayment.endBalance)!0}</td>
                                    </tr>
                                </#list>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td><b>Итого за период</b></td>
                                    <td class="total"><b>${groupPriceTotal}</b></td>
                                    <td class="total"><b>${amountAccrued}</b></td>
                                    <td class="total"><b>${discount}</b></td>
                                    <td class="total"><b>${amountPaid}</b></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="text-center m-5">
                    <a href="/admin" class="bn5 ml-5">Назад</a>
                </div>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="/js/script.js"></script>
</@main.renderWith>