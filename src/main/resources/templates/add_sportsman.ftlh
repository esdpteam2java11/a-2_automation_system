<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color-sportsman">
        <div class="container col-xxl-8" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="add-sportsman-header text-center mb-5 pt-3" style="color: black">Регистрация спортсмена</h2>
                <form action="/create" id="add-sportsman" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <div class="row border border-secondary mb-3">

                        <div class="col-lg-4 mb-2">
                            <div class="mb-3">
                                <label for="recipient-surname required" class="col-form-label required">Фамилия:</label>
                                <input type="text" value="${(dto.surname)!''}"  name="surname" class="form-control" id="recipient-surname"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-name" class="col-form-label required">Имя:</label>
                                <input type="text" value="${(dto.name)!''}" name="name" class="form-control" id="recipient-name" required>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-patronymic" class="col-form-label">Отчество:</label>
                                <input type="text" value="${(dto.patronymic)!''}" name="patronymic" class="form-control" id="recipient-patronymic">
                            </div>
                        </div>
                        <div class="col-lg-4 mb-2">
                            <div class="mb-3">
                                <label for="recipient-birthDate" class="col-form-label required">Дата рождения:</label>
                                <input type="date"  name="birthDate" class="form-control" id="recipient-birthDate"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-growth" class="col-form-label required">Рост:</label>
                                <input type="number" step="0.01" min="0" name="height" class="form-control"
                                       id="recipient-growth"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-weight" class="col-form-label required">Вес:</label>
                                <input type="number" step="0.01" min="0" name="weight" class="form-control"
                                       id="recipient-weight"
                                       required>
                            </div>
                        </div>
                        <div class="col-lg-4 mb-2">
                            <div class="mb-3">
                                <label for="recipient-phone" class="col-form-label required">Номер телефона:</label>
                                <input type="number" value="${(dto.phone)!''}" name="phone" class="form-control" id="recipient-phone" required>

                            </div>
                            <div class="mb-3">
                                <label for="recipient-whatsapp" class="col-form-label">WhatsApp:</label>
                                <input type="number" value="${(dto.whatsapp)!''}" name="whatsapp" class="form-control" id="recipient-whatsapp">

                            </div>
                            <div class="mb-3">
                                <label for="recipient-telegram" class="col-form-label">Telegram:</label>
                                <input type="text" value="${(dto.telegram)!''}" name="telegram" class="form-control" id="recipient-telegram">
                            </div>
                        </div>
                    </div>
                    <div class="row border border-secondary mb-3">
                        <div class="col-lg-4 mb-2">
                            <div class="mb-3">
                                <label for="recipient-address" class="col-form-label required">Адрес проживания:</label>
                                <textarea type="text"   name="address" class="form-control" id="recipient-address"
                                          rows="1"
                                         required>${(dto.address)!''}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-school" class="col-form-label">Учебное заведение:</label>
                                <textarea type="text"  name="school" class="form-control" id="recipient-school"
                                          rows="1">${(dto.school)!''}</textarea>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-channels" class="col-form-label">Каналы информации о
                                    клубе:</label>
                                <textarea type="text"  name="channels" class="form-control" id="recipient-channels"
                                          rows="1">${(dto.channels)!''}</textarea>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="mb-3">
                                <label class="col-form-label required" for="group">Группа:</label>
                                <select class="form-control" value="${(dto.groupId)!''}"    name="groupId" id="group"
                                        onchange="getGroupPrice(this.value)" required>
                                    <option hidden disabled selected value> -- выберите группу --</option>
                                    <#list groups as group>
                                        <option name="${group.name}" value="${group.id}"
                                        >${group.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="recipient-dateOfAdmission" class="col-form-label required">Дата зачисления в
                                    группу:</label>
                                <input type="date" name="dateOfAdmission" class="form-control"
                                       id="recipient-dateOfAdmission" required>
                            </div>
                            <div class="mb-3 row">
                                <div class="col-lg-6">
                                    <label for="recipient-sum" class="col-form-label-sm required">Стоимость
                                        занятий:</label>
                                    <input type="number" name="amount" min="0" class="form-control"
                                           id="recipient-sum" required>
                                </div>
                                <div class="col-lg-6">
                                    <label for="recipient-sum-date" class="col-form-label-sm required">Дата начала
                                        тарифа:</label>
                                    <input type="date" name="date" class="form-control" id="recipient-sum-date"
                                           required>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="role" name="role" value="CLIENT">
                        <div class="col-lg-4">
                            <div class="mb-3">

                                <label for="recipient-login" class="col-form-label required">Логин:</label>
                                <input type="text"  value="${(dto.login)!''}" name="login" class="form-control"
                                       id="recipient-login" required>
                                <#if dto.login??>
                                    <p style="color:red">${dto.login} такой логин уже существует! Пожалуйста
                                        выберите другой</p>
                                </#if>
                            </div>
                            <#if errors?? >
                                <p style="color: red">Входные данные с ошибками!</p>
                                <#list errors as fieldError>
                                    <p style="color: red">${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                                </#list>
                            </#if>
                            <div class="mb-3">
                                <label for="input-password" class="col-form-label required">Пароль:</label>
                                <input type="text" value="${(dto.password)!''}" name="password" class="form-control"
                                       id="input-password" required>
                            </div>
                            <div class="mb-3">
                                <button type="button" id="input-generate" class="button-85">Сгенерировать
                                    пароль
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row border border-secondary my-3">
                        <div class="col-lg-6 my-2">
                            <button type="button" class="bn30" data-bs-toggle="modal"
                                    data-bs-target="#searchRelativeModal">Выбрать родственника из списка
                            </button>
                        </div>
                        <div class="col-lg-6 my-2 text-end">
                            <button type="button" class="bn30" id="add-new-parent-button">Добавить нового родственника
                            </button>
                        </div>
                        <div class="col">
                            <div class="table-responsive">
                                <table id="relative-table" class="table table-bordered align-middle">
                                    <thead>
                                    <tr>
                                        <th scope="col" hidden>ID</th>
                                        <th scope="col">Родство</th>
                                        <th scope="col">Фамилия</th>
                                        <th scope="col">Имя</th>
                                        <th scope="col">Отчество</th>
                                        <th scope="col">Телефон</th>
                                        <th scope="col">WhatsApp</th>
                                        <th scope="col">Telegram</th>
                                        <th scope="col">Удалить</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-body">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="text-center mb-5">
                        <button type="submit" class="custom-btn btn-1" id="button">Добавить спортсмена</button>
                        <a href="/admin" class="bn5 ml-5">Назад</a>
                    </div>
                </form>
            </div>
        </div>

        <!-- SearchModal -->
        <div class="modal fade" id="searchRelativeModal" tabindex="-1" aria-labelledby="searchRelativeModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="searchRelativeModalLabel">Поиск родственника</h5>
                        <button type="button" class="btn btn-secondary search-relative-modal-close" data-bs-dismiss="modal">Закрыть</button>
                    </div>
                    <div class="modal-body">
                        <form action="" id="search-relative-frm" method="get">
                            <div class="row mx-0">
                                <label for="surnameSearch"></label>
                                <input type="text" id="surnameSearch" name="surnameSearch" class="form-control w-auto"
                                       placeholder="Введите фамилию">
                                <button type="submit" id="search-relative-btn" class="btn btn-secondary
                            py-0 mx-3">Искать
                                </button>
                            </div>
                        </form>
                        <table id="search-relative-table" class="table table-bordered mt-2">
                            <thead>
                            <tr>
                                <th scope="col" hidden>ID</th>
                                <th scope="col">Родство</th>
                                <th scope="col">Фамилия</th>
                                <th scope="col">Имя</th>
                                <th scope="col">Отчество</th>
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
    <script src="/js/script.js"></script>
</@main.renderWith>