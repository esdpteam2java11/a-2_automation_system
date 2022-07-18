<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <form action="/group/add" method="post">
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
                                <h3 class="mb-4 text-uppercase text-center">Регистрация новой группы</h3>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <label class="form-label required" for="name_input">Название группы</label>
                                            <input name="name" value="${(group.name)!''}" type="text" id="name_input"
                                                   class="form-control form-control-lg" placeholder="Название группы"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="price_input">Цена</label>
                                        <div class="form-outline">
                                            <input name="sum" value="${(group.sum)!''}" type="number"
                                                   id="price_input"
                                                   class="form-control form-control-lg" placeholder="Цена"
                                                   required="required"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="color_input">Цвет</label>
                                        <div class="form-outline">
                                            <input name="color" type="color"
                                                   id="color_input"
                                                   class="form-control form-control-lg" placeholder="Цвет"
                                                   value="#FFFFFF"
                                                   required onchange="checkIfColorExists(this.value)"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <label class="form-label required" for="trainer">Выберите тренера</label>
                                        <div class="form-outline">
                                            <select id="trainer" name="trainer" class="select-css">
                                                <#list trainers as trainer>
                                                    <option value="${trainer.id}">${trainer.name}</option>
                                                </#list>
                                            </select>
                                        </div>
                                    </div>
                                </div>



                                <#if errors?? >
                                    <p>Входные данные с ошибками!</p>
                                    <#list errors as fieldError>
                                        <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                                    </#list>
                                </#if>
                                <div class="button">
                                    <button type="submit" class="custom-btn btn-1">Зарегистрировать</button>
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
