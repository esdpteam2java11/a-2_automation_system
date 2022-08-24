<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color main-color-sportsman">
        <div class="container col-xxl-8 pb-2 mb-5" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h3 class="mb-5 pt-3 text-center">Расход денег</h3>
                <h6 class="mb-5 pt-3 text-center">Выдал деньги: ${moneyMovement.cashierFIO}</h6>
                <form action="/cash/view/${moneyMovement.id}" id="income" method="post">
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div>
                                <label class="col-form-label"
                                       for="money-operation-type">Вид операции:</label>
                                <input type="text" class="form-control"
                                       name="moneyOperationType" id="money-operation-type"
                                       value="${moneyMovement.moneyOperationType}" readonly/>
                            </div>
                            <div>
                                <label class="col-form-label" for="payment-date">Дата</label>
                                <input name="date" type="date" class="form-control"
                                       id="payment-date" value="${moneyMovement.date}" readonly/>
                            </div>
                            <div>
                                <label class="col-form-label" for="payment-sum">Сумма</label>
                                <input name="amount" type="number" id="payment-sum"
                                       class="form-control" value="${moneyMovement.amount?c}" readonly/>
                            </div>
                        </div>
                        <div class="col-md-8 mb-4">
                            <div>
                                <label class="col-form-label" for="counterparty">Контрагент</label>
                                <input type="text" class="form-control"
                                       name="counterparty" id="counterparty"
                                       value="${moneyMovement.counterpartyFIO}" readonly/>
                            </div>
                            <div>
                                <label class="col-form-label" for="purpose">Комментарий</label>
                                <textarea name="purpose" type="text"
                                          id="purpose" class="form-control"
                                          readonly>${moneyMovement.purpose}</textarea>
                            </div>
                        </div>
                    </div>

                    <#if moneyMovement.sportsmanPaymentDTOList?size!=0>
                        <div id="sportsman-payment-details" style="display: inline">
                            <div class="d-flex flex-column">
                                <div class="mb-3 d-flex justify-content-end">
                                    <div class="col col-lg-8 px-2">
                                        <table id="payment-period"
                                               class="table table-bordered align-middle text-center"
                                               readonly="readonly">
                                            <caption style="caption-side: top">Детализация по периодам возврата</caption>
                                            <thead>
                                            <tr>
                                                <th scope="col" style="width: 50%">Дата</th>
                                                <th scope="col" style="width: 50%">Сумма</th>
                                            </tr>
                                            </thead>
                                            <tbody class="table-body">

                                            <#list moneyMovement.sportsmanPaymentDTOList as sportsmanPayment>
                                                <tr>
                                                    <td>${sportsmanPayment.date}</td>
                                                    <td>${sportsmanPayment.amount?c}</td>
                                                </tr>
                                            </#list>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>

                    <div class=" button">
                        <button type="submit" id="create-payment-btn"
                                class="custom-btn btn-1">Удалить платеж
                        </button>
                        <a href="/cash" class="bn5 ml-5">Вернуться в кассу</a>
                    </div>
                </form>
            </div>
        </div>

    </main>
    <script src="/js/script.js"></script>
</@main.renderWith>