<#import "partials/index_navbar.ftlh" as main />
<#import "partials/footer.ftlh" as footer />
<#import "partials/pager.ftlh" as p>
<@main.renderWith >
    <h1 class="mb-5 mt-3 text-center">Новости</h1>
    <div class="container">
    <#list news.content as new>
        <div class="card mb-5" style="max-width: 840px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="/upload/${new.image}" class="img-fluid rounded-start" alt="">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${new.title}</h5>
                        <p class="card-text">${new.description}</p>
                        <p class="card-text"><small class="text-muted">Дата создания: ${new.date}</small></p>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    </div>
    <@p.pager url news />


<#--    <div id="carousel" class="carousel slide" data-ride="carousel">-->
<#--        <div class="carousel-inner">-->
<#--            <div class="carousel-item active">-->
<#--                <img class="img-fluid" src="/upload/IoC.PNG" alt="...">-->
<#--            </div>-->
<#--            <div class="carousel-item">-->
<#--                <img class="img-fluid" src="/upload/IoC.PNG" alt="...">-->
<#--            </div>-->
<#--            <div class="carousel-item">-->
<#--                <img class="img-fluid" src="/upload/IoC.PNG" alt="...">-->
<#--            </div>-->
<#--        </div>-->
<#--        <!-- Элементы управления &ndash;&gt;-->
<#--        <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">-->
<#--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
<#--            <span class="sr-only">Предыдущий</span>-->
<#--        </a>-->
<#--        <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">-->
<#--            <span class="carousel-control-next-icon" aria-hidden="true"></span>-->
<#--            <span class="sr-only">Следующий</span>-->
<#--        </a>-->
<#--    </div>-->
    <@footer.renderWith>
    </@footer.renderWith>
</@main.renderWith>