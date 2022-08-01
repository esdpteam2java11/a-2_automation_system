<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <h1 class="text-center mt-3">Создать новость</h1>
    <form action="/news" class="row g-3" id="place" method="post" enctype="multipart/form-data">
        <div class="col-auto add-news">
            <div class="mb-3" style="display: inline-block;">
                <label for="recipient-title" class="col-form-label">Заголовок: </label>
                <input type="text" name="title" class="form-control text-center" id="recipient-title" required>
            </div>
            <div class="mb-3">
                <label for="message-text" class="col-form-label">Описание: </label>
                <textarea rows="10" name="description" class="form-control description " id="message-text"
                          required style="width:700px; height:450px;"></textarea>
            </div>
            <div class="mb-3">
                <label for="formFile" class="form-label">Фотография</label>
                <input class="form-control" name="file" type="file" id="formFile" required>
            </div>
            <button type="submit" class="custom-btn btn-1 text-center" id="button">Добавить новость</button>
        </div>
    </form>
</@main.renderWith>