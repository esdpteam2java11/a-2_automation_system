<#import "partials/index_navbar.ftlh" as main />
<#import "partials/footer.ftlh" as footer />
<@main.renderWith >
    <link rel="stylesheet" href="/css/trainer.css">

    <div class="row">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1>Беккулов Актилек</h1>
                </div>
            </div>
        </div><div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
            <div class="div">
                <p>Является тренером спортивного клуба «А-2» с 2020 года.
                    Имеет специализированное образование в области физической культуры и спорта.
                    Также является победителем/призером многих республиканских и международных турниров.
                </p>
            </div>
        </div>
        <div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
            <img class="person" src="/images/person_2.jpg">
        </div>
    </div>
    <@footer.renderWith>

    </@footer.renderWith>
</@main.renderWith>
