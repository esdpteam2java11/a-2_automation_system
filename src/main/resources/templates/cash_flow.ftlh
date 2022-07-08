<#import "partials/admin_main.ftlh" as main />
<@main.renderWith >
    <main class="main-color">
        <div class="container col-xxl-8" style="background-color: white;">
            <div class="container" style="background-color: white;">
                <h2 class="add-sportsman-header" style="color: black">Кассовые операции</h2>
                <form action="/cash" method="get">
                </form>
            </div>
        </div>

        <!-- SearchUserModal -->
        <div class="modal fade" id="searchContractorModal" tabindex="-1" aria-labelledby="searchUserModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="searchUserModalLabel">Поиск по ФИО</h5>
                        <button type="button" class="search-user-modal-close btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="" id="search-user-frm" method="get">
                            <label for="searchUser"></label>
                            <input type="text" id="searchUser" name="searchUser" class="w-auto"
                                   placeholder="Введите текст">
                            <button type="submit" id="search-user-btn" class="btn btn-secondary
                            py-0">Искать
                            </button>
                        </form>
                        <table id="search-user-table" class="table table-bordered mt-2">
                            <thead>
                            <tr>
                                <th scope="col" hidden>ID</th>
                                <th scope="col">Фамилия</th>
                                <th scope="col">Имя</th>
                                <th scope="col">Отчество</th>
                                <th scope="col">Телефон</th>
                                <th scope="col">Выбрать</th>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <script src="/js/script.js"></script>
</@main.renderWith>