<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color-sportsman">


                <form action="/cash/INCOME" id="add-sportsman" method="post">
                    <section class="h-100 add-trainer">
                        <div class="container py-5 h-100">
                            <div class="row d-flex justify-content-center align-items-center h-100">

                                <div class="col">
                                    <div class="card card-registration my-4">
                                        <div class="card-body p-md-5 text-black">
                                            <h3 class="mb-4 text-uppercase text-center">Приход денег</h3>
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="col-sm-6 col-form-label" for="operation-type">Вид операции:</label>
                                                    <select class="form-control col-sm-6" name="manyOperationType" id="operation-types">
                                                        <option selected value> -- все --</option>
                                                        <#list operationTypes as operationType>
                                                            <option value="${operationType}">${operationType.rusValue}</option>
                                                        </#list>
                                                    </select>

                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <label class="form-label required" for="login_input">Дата</label>
                                                        <input name="date"  type="date"
                                                               class="form-control form-control-lg" placeholder="Дата"
                                                               required="required"/>
                                                    </div>

                                                </div>
                                            </div>


                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label required" for="name_input">Сумма</label>
                                                    <div class="form-outline">
                                                        <input name="amount"  type="number" id="recipient-name sum"
                                                               class="form-control form-control-lg" placeholder="Сумма"
                                                               required="required"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label required" for="name_input">Контрагент</label>
                                                    <select class="js-selectize"  name="counterparty" id="fio">
                                                        <option value=""></option>
                                                        <#list users as user>
                                                            <option value="${user.id}">${user.fio}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label required" for="name_input">Коментари</label>
                                                    <div class="form-outline">
                                                        <input name="purpose"  type="textarea" id="recipient-name sum"
                                                               class="form-control form-control-lg" placeholder="Коментари"
                                                               required="required"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="button">
                                                <button type="submit" id="button" class="custom-btn btn-1">Сохранить</button>
                                            </div>

                                            <div id="all" style="display: none">
                                                <div class="text-center mb-5">

                                                    <button type="button" class="custom-btn btn-1" id="add-new">Добавить новую запись</button>
                                                </div>
                                                <div class="col">
                                                    <div class="col-lg-4">
                                                        <div class="mb-3">

                                                            <table id="relative-table" class="table table-bordered align-middle">
                                                                <thead>
                                                                <tr>

                                                                    <th scope="col">Дата</th>
                                                                    <th scope="col">Сумма</th>
                                                                    <th scope="col">Удалить</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody class="table-body">
                                                                </tbody>
                                                            </table>
                                                        </div>

                                                    </div>
                                                </div>





                                            <#if errors?? >
                                                <p>Входные данные с ошибками!</p>
                                                <#list errors as fieldError>
                                                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                                                </#list>
                                            </#if>


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>


                </form>




    </main>


    <script>


        $(document).ready(function () {
            $('.js-selectize').selectize();
        });

        const select = document.getElementById('operation-types')
       select.addEventListener('change',shows)
       function shows() {
           const show = document.getElementById('operation-types').value
           console.log(show)
           let isShow = document.getElementById('all');
           if (show == "SPORTSMAN_PAYMENT") {

               isShow.style.display = 'block';




           const save = document.getElementById("button")
               const mainSum = document.getElementById("recipient-name sum");
               save.addEventListener("click",f)
               function f() {
                   const s = document.querySelectorAll('input[id="s"]')


                    let allSum = 0;
                   for (let i = 0; i < s.length; ++i) {
                      allSum += parseInt(s[i].value)

                       mainSum.value = allSum

                   }


               }


           } else {
               isShow.style.display = 'none';


           }
       }

        const relativesTableBody = document.querySelector('#relative-table>.table-body')


        function deleteSelectedRow(button) {
            button.closest('tr').remove()
        }

        const addNewParentBtn = document.getElementById("add-new")

        addNewParentBtn.addEventListener("click", function () {
            const newTrTag = document.createElement("tr")
            newTrTag.innerHTML = `<input type="hidden" value="PAID" name="sportsmanOperationType" required/>

                          <td><input type="date" name="dateSportsman" required/></td>
                          <td><input type="number" class="sums" id="s" name="amountSportsman" required/></td>



                          <td>
                                <button type="button" class="btn btn-outline-danger py-1 px-2" onclick='deleteSelectedRow(this)'>
                                     <i class="bi bi-x-square"></i>
                                </button>
                          </td>`
            relativesTableBody.appendChild(newTrTag)
        })



        document.getElementsByClassName("search-relative-modal-close")[0].addEventListener("click", function () {
            const searchResult = document.querySelector('#search-relative-table>.table-body')
            while (searchResult.firstElementChild) {
                searchResult.firstElementChild.remove()
            }
        })







    </script>
</@main.renderWith>