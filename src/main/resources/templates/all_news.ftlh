<#import "partials/index_navbar.ftlh" as main />
<#import "partials/footer.ftlh" as footer />
<@main.renderWith >
    <h1 class="mb-5 mt-3 text-center">Новости</h1>
    <div class="container">
    <#list page.content as new>
        <div class="card mb-5">
            <div class="row g-0">
                <div class="col-md-4">
                    <a href="/news/${new.id}"><img src="/upload/${new.image}" class="img-fluid rounded-start" alt=""
                        style="height: 350px; width: 350px;"></a>
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <a href="/news/${new.id}">${new.title}</a>
                        <p class="card-text hide-text">${new.description}</p>
                        <p class="card-text"><small class="text-muted">Дата создания: ${new.date?datetime("yyyy-MM-dd")?string("dd.MM.yyyy")}</small></p>
                    </div>
                </div>
            </div>
        </div>
    </#list>
    </div>
    <#include "partials/pagination.ftlh">
    <@footer.renderWith>
    </@footer.renderWith>
</@main.renderWith>