<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color-sportsman">

        <div class="container col-xxl-8" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h3 class="mb-5 pt-3 text-center">Приход денег</h3>
                <form action="/cash/INCOME" id="income" method="post">
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div>
                                <label class="col-form-label required"
                                       for="money-operation-type">Вид операции:</label>
                                <select class="form-control" name="moneyOperationType"
                                        id="money-operation-type"
                                        onchange="showDetails(this.value)">
                                    <option hidden disabled selected value> -- выберите операцию --</option>
                                    <#list operationTypes as operationType>
                                        <option value="${operationType}">${operationType.rusValue}</option>
                                    </#list>
                                </select>
                            </div>
                            <div>
                                <label class="col-form-label required"
                                       for="income-date">Дата</label>
                                <input name="date" type="date"
                                       class="form-control" placeholder="Дата"
                                       id="income-date" required="required"/>
                            </div>
                            <div>
                                <label class="col-form-label required"
                                       for="income-sum">Сумма</label>
                                <input name="amount" type="number" id="income-sum"
                                       class="form-control" placeholder="Сумма"
                                       required="required"/>
                            </div>
                        </div>
                        <div class="col-md-8 mb-4">
                            <div>
                                <label class="col-form-label required"
                                       for="counterparty">Контрагент</label>
                                <select class="js-selectize" name="counterparty" id="counterparty">
                                    <option value=""></option>
                                    <#list users as user>
                                        <option value="${user.id}">${user.fio}</option>
                                    </#list>
                                </select>
                            </div>
                            <div>
                                <label class="col-form-label required"
                                       for="purpose">Комментарий</label>
                                <textarea name="purpose" type="text" id="purpose"
                                          class="form-control"
                                          required="required"></textarea>
                            </div>
                        </div>
                    </div>

                    <div id="sportsman-payment-details" style="display: none">
                        <div class="d-flex flex-column">
                            <div class="mb-3 d-flex justify-content-end">
                                <button type="button" class="bn30 mx-2"
                                        id="add-sportsman-payment"
                                        onclick="addNewPaymentDetail()">Добавить период оплаты
                                </button>
                            </div>
                            <div class="mb-3 d-flex justify-content-end">
                                <div class="col col-lg-8 px-2">
                                    <table id="payment-period"
                                           class="table table-bordered align-middle text-center">
                                        <thead>
                                        <tr>
                                            <th scope="col" style="width: 35%">Дата</th>
                                            <th scope="col" style="width: 35%">Сумма</th>
                                            <th scope="col" style="width: 30%">Удалить запись</th>
                                        </tr>
                                        </thead>
                                        <tbody class="table-body" onchange="setTotalSum()">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <#if errors?? >
                            <p>Входные данные с ошибками!</p>
                            <#list errors as fieldError>
                                <p>${fieldError.field}
                                    - ${fieldError.defaultMessage!'-- no message --'}</p>
                            </#list>
                        </#if>

                    </div>
                    <div class="button">
                        <button type="submit" id="create-income-btn" class="custom-btn btn-1">Сохранить</button>
                        <a href="/cash" class="bn5 ml-5">Отменить</a>
                    </div>
                </form>
            </div>
        </div>

    </main>
    <script src="/js/script.js"></script>
</@main.renderWith>