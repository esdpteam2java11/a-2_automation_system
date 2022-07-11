<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container col-xxl-8 pb-2" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="cash-flow-header text-center" style="color: black">Кассовые операции</h2>
                <form action="/cash" method="get">
                    <div class="border border-secondary mb-3">
                        <div class="row my-3 mx-4">
                            <div class="col-sm-3 pl-4">
                                <label for="fio" class="col-form-label">Поиск по ФИО:</label>
                            </div>
                            <div class="col-sm-9 pl-0">
                                <select class="js-selectize" name="fio" id="fio">
                                    <option value=""></option>
                                    <#list users as user>
                                        <option value="${user.id}">${user.surname}</option>
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
                                            <option value="${typeOfFinance}">${typeOfFinance}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="operation-type">Вид операции:</label>
                                    <select class="form-control col-sm-6" name="operationType" id="operation-type">
                                        <option selected value> -- все --</option>
                                        <#list operationTypes as operationType>
                                            <option value="${operationType}">${operationType}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="period-start">Период с:</label>
                                    <input type="date" name="periodStart" class="form-control col-sm-6"
                                           id="period-start" value="${.now?date?iso_utc}">
                                </div>
                                <div class="row m-2">
                                    <label class="col-sm-6 col-form-label" for="period-end">Период по:</label>
                                    <input type="date" name="periodEnd" class="form-control col-sm-6"
                                           id="period-end" value="${.now?date?iso_utc}">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- SearchUserModal -->
        <div class="modal fade" id="searchContractorModal" tabindex="-1" aria-labelledby="searchUserModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="searchUserModalLabel">Поиск по ФИО</h5>
                        <button type="button" class="search-user-modal-close btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="" id="search-user-frm" method="get">
                            <label for="searchUser"></label>
                            <input type="text" id="searchUser" name="searchUser" class="w-auto"
                                   placeholder="Введите текст">
                            <button type="submit" id="search-user-btn" class="btn btn-secondary
                            py-0">Искать
                            </button>
                        </form>
                        <table id="search-user-table" class="table table-bordered mt-2">
                            <thead>
                            <tr>
                                <th scope="col" hidden>ID</th>
                                <th scope="col">Фамилия</th>
                                <th scope="col">Имя</th>
                                <th scope="col">Отчество</th>
                                <th scope="col">Телефон</th>
                                <th scope="col">Выбрать</th>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://snipp.ru/cdn/microplugin.js/src/microplugin.js"></script>
    <script src="https://snipp.ru/cdn/sifter.js/sifter.min.js"></script>
    <script src="https://snipp.ru/cdn/selectize.js/0.12.6/dist/js/selectize.min.js"></script>

    <script>
        $(document).ready(function () {
            $('.js-selectize').selectize();
        });
    </script>
    <script src="/js/script.js"></script>
</@main.renderWith>