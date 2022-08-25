<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <form action="/edit/trainer/${dto.id}" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <section class="h-100 add-trainer">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card card-registration my-4">
                            <div class="card-body p-md-5 text-black">
                                <h3 class="mb-4 text-uppercase text-center">Изменения тренера</h3>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <label class="form-label required" for="login_input">Логин</label>
                                            <input name="login" value="${(dto.login)!''}" type="text" id="login_input"
                                                   class="form-control form-control-lg" placeholder="Логин"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>
                                <#if check??>
                                    <p style="color:red">${check.login} такой логин уже существует! Пожалуйста
                                        выберите другой</p>
                                </#if>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="password_input">Пароль</label>
                                        <div class="form-outline">
                                            <input name="password" value="${(dto.password)!}" type="password"
                                                   id="input-password" class="form-control form-control-lg"
                                                   required="required" onfocus="clearInput()"/>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <button type="button" id="input-generate" class="button-85 button-user-create">
                                            Сгенерировать пароль
                                        </button>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="name_input">Имя</label>
                                        <div class="form-outline">
                                            <input name="name" value="${(dto.name)!''}" type="text" id="name_input"
                                                   class="form-control form-control-lg" placeholder="Имя"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="surname_input">Фамилия</label>
                                        <div class="form-outline">
                                            <input name="surname" value="${(dto.surname)!''}" type="text"
                                                   id="surname_input"
                                                   class="form-control form-control-lg" placeholder="Фамилия"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label" for="patronymic_input">Отчество</label>
                                        <div class="form-outline">
                                            <input name="patronymic" value="${(dto.patronymic)!''}" type="text"
                                                   id="patronymic_input"
                                                   class="form-control form-control-lg" placeholder="Отчество"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-outline mb-4">
                                    <label class="form-label required" for="address_input">Адрес</label>
                                    <input type="text" name="address" value="${(dto.address)!''}" id="address_input"
                                           class="form-control form-control-lg"
                                           placeholder="Адрес" required="required"/>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="phone_input">Телефон</label>
                                        <div class="form-outline">
                                            <input name="phone" value="${(dto.phone)!''}" type="text" id="phone_input"
                                                   class="form-control form-control-lg" placeholder="Телефон"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="birth_input">Дата рождения</label>
                                        <div class="form-outline">
                                            <input name="birthDate" type="date" id="birth_input"
                                                   class="form-control form-control-lg"
                                                   required="required" value="${(dto.birthDate?date?iso_local)!''}"/>
                                        </div>
                                    </div>
                                </div>
                                <#if errors?? >
                                    <p style="color: red">Входные данные с ошибками!</p>
                                    <#list errors as fieldError>
                                        <p style="color: red">${fieldError.field}
                                            - ${fieldError.defaultMessage!'-- no message --'}</p>
                                    </#list>
                                </#if>
                                <input type="hidden" id="role" name="role" value="EMPLOYEE">
                                <div class="button">
                                    <button type="submit" class="custom-btn btn-1">Редактировать данные</button>
                                    <a href="/admin">
                                        <button type="button" class="custom-btn btn-1">Отменить</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>
    <script src="/js/script.js"></script>
</@main.renderWith>