<#import "partials/index_navbar.ftlh" as main />
<#import "partials/footer.ftlh" as footer />
<@main.renderWith >
    <div class="container">
        <h1 class="text-center mt-3">Редактировать новость ${news.title}</h1>

        <form action="/news/edit/${news.id}" id="edit-news" enctype="multipart/form-data" method="post">
            <div class="col-auto text-center">
                <div class="mb-3">
                    <label for="recipient-title" class="col-form-label">Заголовок: </label>
                    <input type="text" name="title" class="form-control text-center" id="recipient-title" required
                           value="${news.title}"
                           style="width: 300px; display: block; margin:auto;">

                </div>
                <div class="mb-3">
                    <label for="message-text" class="col-form-label">Описание: </label>
                    <textarea rows="10" name="description" class="form-control description " id="message-text"
                              required style="width:700px; height:450px; display: block; margin:auto;">
                            ${news.description}
                        </textarea>
                </div>
                <div class="mb-3">
                    <label for="formFile" class="form-label">Фотография: </label>
                    <input class="form-control" name="file" type="file" id="formFile" required
                           style="width: 400px; display: block; margin:auto;">
                </div>
                <button type="submit" class="custom-btn btn-1 text-center" id="button">Изменить новость</button>
            </div>
        </form>
        <a href="/news/all">
            <button type="button" class="button-85">Вернуться к новостям</button>
        </a>
    </div>

    <@footer.renderWith>
    </@footer.renderWith>
</@main.renderWith>