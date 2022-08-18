<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color-sportsman">
        <div class="container col-xxl-8 pb-2 mb-5" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="text-center mb-5 pt-3" style="color: black">Отчет по долгам</h2>
                <form action="/all_debts" method="get" id="debts-report">
                    <div class="row mb-3">
                        <div class="col-sm-2">
                            <label class="col-form-label required" for="group">Группа:</label>
                        </div>
                        <div class="col">
                            <select class="form-control" name="groupId" id="group"
                                    onchange="getGroupPrice(this.value); clearReport()"
                                    required>
                                <option hidden disabled selected value> -- выберите группу --</option>
                                <#list groups as group>
                                    <option name="${group.name}" value="${group.id}"
                                    >${group.name}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label class="col-form-label required" for="recipient-sum">Цена за 1 месяц:</label>
                        </div>
                        <div class="col">
                            <input type="number" name="amount" class="form-control"
                                   id="recipient-sum" readonly="readonly">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-2">
                            <label class="col-form-label">Период отчета </label>
                        </div>
                        <label class="col-form-label required" for="start-date">с: </label>
                        <div class="col">
                            <input type="month" name="startDate" class="form-control"
                                   id="start-date" required>
                        </div>
                        <label class="col-form-label required" for="end-date">по: </label>
                        <div class="col">
                            <input type="month" name="endDate" class="form-control"
                                   id="end-date" max="${.now?date?iso_utc}" required>
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
                                    <th scope="col" hidden>ID</th>
                                    <th scope="col">ФИО спортсмена</th>
                                    <th scope="col">Сумма по тарифу группы за период</th>
                                    <th scope="col">Сумма по тарифу спортсмена за период</th>
                                    <th scope="col">Сумма скидки</th>
                                    <th scope="col">Оплаты за период</th>
                                    <th scope="col">Остаток долга на начало периода</th>
                                    <th scope="col">Остаток долга на конец периода</th>
                                </tr>
                                </thead>
                                <tbody class="table-body">
                                <#list (sportsmenPayments)! as sportsmanPayment>
                                    <tr>
                                        <td hidden>${sportsmanPayment.sportsmanId}</td>
                                        <td>${sportsmanPayment.sportsmanFio}</td>
                                        <td>${sportsmanPayment.groupPrice}</td>
                                        <td>${(sportsmanPayment.amountAccrued)!0}</td>
                                        <td>${sportsmanPayment.groupPrice - sportsmanPayment.amountAccrued}</td>
                                        <td>${(sportsmanPayment.amountPaid)!0}</td>
                                        <td>${(sportsmanPayment.startBalance)!0}</td>
                                        <td>${(sportsmanPayment.endBalance)!0}</td>
                                    </tr>
                                </#list>
                                </tbody>
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