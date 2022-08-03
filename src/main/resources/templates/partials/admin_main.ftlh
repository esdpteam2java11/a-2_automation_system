<#macro renderWith title="" scripts=[] styles=[]>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script src='https://www.google.com/recaptcha/api.js'></script>
        <title>${title}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/css/bootstrap/bootstrap.css">
        <link rel="stylesheet" href="/css/selectize.default.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
                integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"
                integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2"
                crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/css/addUserForm.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}">
        </#list>
        <#list scripts as scriptSource>
            <script type="text/javascript" src="${scriptSource}"></script>
        </#list>
    </head>
    <body>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <img src="/images/log.JPG" alt="" width="30" height="24">
            <a class="nav-link" href="/">Главная</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/group/add">Добавить группу</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/create">Регистрация спортсмена</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/add/trainer">Добавить тренера</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/group/all">Группы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/group/all/calendar">График тренировок</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/cash">Касса</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/news">Создать новость</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/sportsman_story">История спортсмена</a>
                    </li>


                </ul>
            </div>
            <div class="text-right mr-5">
                <form class="flex box-128" action="/logout" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <button class="bn30">Выйти</button>
                </form>
            </div>
        </div>
    </nav>

    <#nested>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://snipp.ru/cdn/microplugin.js/src/microplugin.js"></script>
    <script src="https://snipp.ru/cdn/sifter.js/sifter.min.js"></script>
    <script src="https://snipp.ru/cdn/selectize.js/0.12.6/dist/js/selectize.min.js"></script>
    </body>
    </html>

</#macro>