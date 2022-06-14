<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавить спортсмена</title>
</head>
<body>

<div class="container">
    <h1 style="margin-top: 20px; margin-bottom: 20px; text-align: center">Добавить спортсмена:</h1>
    <div class="container col-xxl-8">
        <div class="row flex-lg-row-reverse align-items-center g-5">
            <div class="form-add-sportsmen">
                <form action="" class="row g-3" id="place" method="post">
                    <div class="col-lg-6 add-place">
                        <div class="mb-3">
                            <label for="recipient-surname" class="col-form-label">Фамилия: </label>
                            <input type="text" name="surname" class="form-control" id="recipient-surname" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-name" class="col-form-label">Имя: </label>
                            <input type="text" name="name" class="form-control" id="recipient-name" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-patronymic" class="col-form-label">Отчество: </label>
                            <input type="text" name="patronymic" class="form-control" id="recipient-patronymic" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-birthDate" class="col-form-label">Дата рождения: </label>
                            <input type="date" name="birthDate" class="form-control" id="recipient-birthDate" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-phone" class="col-form-label">Номер телефона: </label>
                            <input type="text" name="phone" class="form-control" id="recipient-phone" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-whatsapp" class="col-form-label">Вотсап: </label>
                            <input type="text" name="whatsapp" class="form-control" id="recipient-whatsapp" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-telegram" class="col-form-label">Телеграм: </label>
                            <input type="text" name="telegram" class="form-control" id="recipient-telegram" required>
                        </div>
                        <div class="mb-3">
                            <label for="group">Группа:</label>
                            <select name="group" id="group">
<#--                                <#list groups as group>-->
<#--                                    <option name="${group.name}" value="0">${group.name}</option>-->
<#--                                </#list>-->
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-dateOfAdmission" class="col-form-label">Дата зачисления в зал: </label>
                            <input type="date" name="dateOfAdmission" class="form-control" id="recipient-dateOfAdmission" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-address" class="col-form-label">Адрес проживания: </label>
                            <input type="text" name="address" class="form-control" id="recipient-address" required>
                        </div>

                        <div class="mb-3">
                            <label for="recipient-growth" class="col-form-label">Рост: </label>
                            <input type="text" name="growth" class="form-control" id="recipient-growth" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-weight" class="col-form-label">Вес: </label>
                            <input type="text" name="weight" class="form-control" id="recipient-weight" required>
                        </div>


                        <div class="mb-3">
                            <label for="recipient-school" class="col-form-label">Школа, где учится ребенок: </label>
                            <input type="text" name="school" class="form-control" id="recipient-school" required>
                        </div>
                        <div class="mb-3">
                            <label for="parent">Выберите родителя:</label>
                            <select name="parent" id="parent">
<#--                                <#list parents as parent>-->
<#--                                    <option name="${parent.kinship}" value="0">${parent.kinship}</option>-->
<#--                                </#list>-->
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-parentSurname" class="col-form-label">Фамилия родителя: </label>
                            <input type="text" name="parentSurname" class="form-control" id="recipient-parentSurname" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-parentName" class="col-form-label">Имя родителя: </label>
                            <input type="text" name="parentName" class="form-control" id="recipient-parentName" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-parentPatronymic" class="col-form-label">Отчество родителя: </label>
                            <input type="text" name="parentPatronymic" class="form-control" id="recipient-parentPatronymic" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-parentPhone" class="col-form-label">Номер телефона родителя: </label>
                            <input type="text" name="parentPhone" class="form-control" id="recipient-parentPhone" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-parentWhatsapp" class="col-form-label">Вотсап родителя: </label>
                            <input type="text" name="parentWhatsapp" class="form-control" id="recipient-parentWhatsapp" required>
                        </div>
                        <div class="mb-3">
                            <label for="recipient-parentTelegram" class="col-form-label">Телеграм родителя: </label>
                            <input type="text" name="parentTelegram" class="form-control" id="recipient-parentTelegram" required>
                        </div>

                        <button type="submit" class="btn btn-primary add bg-dark" id="button">Добавить спортсмена</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>