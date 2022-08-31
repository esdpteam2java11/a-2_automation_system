<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <form action="password" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <main class="main-color">
            <section class="h-100">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col">
                            <div class="card card-registration my-4">
                                <div class="card-body p-md-5 text-black">
                                    <h3 class="mb-4 text-uppercase text-center">Поменять пароль ${user}</h3>

                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label required" for="new-password">Новый пароль</label>
                                                <input name="newPassword"  type="password" id="new-password"
                                                       class="form-control form-control-lg" placeholder="Новый пароль"
                                                       required="required"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label required" for="confirm-password">Подтверждение пароля</label>
                                                <input name="confirmPassword"  type="password" id="new-password"
                                                       class="form-control form-control-lg" placeholder="Подтверждение пароля"
                                                       required="required"/>
                                            </div>
                                        </div>
                                    </div>

                                    <#if errors?? >
                                        <p>Входные данные с ошибками!</p>
                                        <#list errors as fieldError>
                                            <p style="color:red">${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                                        </#list>
                                    </#if>
                                    <#if passwordConfirm?? >
                                        <p style="color:red">${passwordConfirm}</p>
                                    </#if>
                                    <#if passwordChange??>
                                        <p style="color:red">${passwordChange}</p>
                                    </#if>
                                    <div class="button">
                                        <button type="submit" class="custom-btn btn-1">Поменять</button>
                                        <a href="/admin" class="ml-5 bn5">Назад</a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

    </form>


</@main.renderWith>
