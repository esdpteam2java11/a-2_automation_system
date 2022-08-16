<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color-sportsman">
        <div class="container col-xxl-8 pb-2 mb-5" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="add-sportsman-header text-center mb-5 pt-3" style="color: black">Отчет по начислениям и
                    оплатам по всем ученикам</h2>
                <form action="/all_debts" method="get">
                    <div class="row mb-3">
                        <div class="col my-auto">
                            <p>Период отчета </p>
                        </div>
                        <div class="col">
                            <div class="d-flex m-2">
                                <label class="col-form-label mx-2" for="start-date">с: </label>
                                <input type="month" name="startDate" class="form-control"
                                       id="start-date" style="width: 40%" required>
                                <label class="col-form-label mx-2" for="end-date">по: </label>
                                <input type="month" name="endDate" class="form-control"
                                       id="end-date" max="${.now?date?iso_utc}" style="width: 40%" required>
                            </div>
                        </div>
                        <div class="col text-center">
                            <button type="submit" class="bn30 my-0" style="margin-top: 20px">Сформировать отчет</button>
                        </div>
                    </div>
                </form>
                <div class="row mb-3">
                    <div class="col">
                        <div class="table-responsive">
                            <table id="report-table" class="table table-bordered align-middle">
                                <thead>
                                <tr>
                                    <th scope="col" hidden>ID</th>
                                    <th scope="col">Группы и спортсмены</th>
                                    <th scope="col">Сумма по тарифу группы за период</th>
                                    <th scope="col">Сумма по тарифу спортсмена за период</th>
                                    <th scope="col">Сумма скидки</th>
                                    <th scope="col">Оплаты за период</th>
                                    <th scope="col">Остаток долга на начало периода</th>
                                    <th scope="col">Остаток долга на конец периода</th>
                                </tr>
                                </thead>
                                <tbody class="table-body">
                                <tr>
                                    <th scope="col" hidden></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
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