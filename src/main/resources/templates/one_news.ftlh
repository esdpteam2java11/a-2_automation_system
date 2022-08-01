<#import "partials/index_navbar.ftlh" as main />
<#import "partials/footer.ftlh" as footer />
<@main.renderWith >
    <div class="container">
        <div class="text-center">
            <h1 class="mb-3 mt-5">${news.title}</h1>
        </div>
        <div>
            <p class="card-text mb-3"><small class="text-muted">Дата
                    создания: ${news.date?datetime("yyyy-MM-dd")?string("dd MMMM yyyy")}</small></p>
        </div>
        <div class="card mb-3">
            <img src="/upload/${news.image}" class="card-img-top" alt="">
            <div class="card-body">
                <p class="card-text">${news.description}</p>
            </div>
        </div>

        <#if edit??>
            <div class="text-right">
                <a href="/news/edit/${news.id}">
                    <button type="button" class="bn30 mb-2">Редактировать</button>
                </a>
                <form action="/news/delete/${news.id}" method="post">
                    <button type="submit" class="bn30 ">Удалить</button>
                </form>
            </div>
        </#if>
        <div>
            <a href="/news/all">
                <button type="button" class="button-85">Вернуться к новостям</button>
            </a>
        </div>
    </div>


    <@footer.renderWith>
    </@footer.renderWith>


</@main.renderWith>