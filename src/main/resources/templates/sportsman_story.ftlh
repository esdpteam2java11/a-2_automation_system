<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container" style="background-color: white; ">
            <div class="admin-search text-center">
                <form method="get" >
                    <label for="search">Поиск по ФИО</label>
                    <select class="js-selectize" name="userId" id="fio">
                        <option value=""></option>
                        <#list users as user>
                            <option value="${user.id}">${user.fio}</option>
                        </#list>
                    </select>

                    <button type="submit" class="bn30" style="margin-top: 20px">Искать</button>
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
                <h3>История роста и веса</h3>
                <#list sportsmanParam as users>
                <tr>

                    <td><a class="text-decoration-none text-reset" >${users.height}</a></td>
                    <td><a class="text-decoration-none text-reset" >${users.weight}</a></td>
                    <td><a class="text-decoration-none text-reset" >${users.creationDate}</a></td>


                </tr>
                </tbody>
                </#list>


            </table>
            <table class="table table-dark table-striped">
                <thead>
                <tr>


                    <th>Тип опреций</th>
                    <th>Сумма</th>
                    <th>Дата</th>



                </tr>
                </thead>
                <tbody>
                <h3>История тарифа</h3>
                <#list sportsmanPayment as users>
                <tr>

                    <td><a class="text-decoration-none text-reset" >${users.operationType.type}</a></td>
                    <td><a class="text-decoration-none text-reset" >${users.amount}</a></td>
                    <td><a class="text-decoration-none text-reset" >${users.date}</a></td>


                </tr>
                </tbody>
                </#list>
            </table>



        </div>
    </main>
<script>
    $(document).ready(function () {
        $('.js-selectize').selectize();
    });
</script>

</@main.renderWith>

