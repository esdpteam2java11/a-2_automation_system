<#macro pager url news>
    <#if news.getTotalPages() gt 7>
        <#assign
        totalPages = news.getTotalPages()
        pageNumber = news.getNumber() + 1
        head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
        tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
        bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
        bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])
        body = head + bodyBefore + (pageNumber > 3 && pageNumber > totalPages - 2)?then([pageNumber], []) +
        bodyAfter + tail>
    <#else>
        <#assign body = 1..news.getTotalPages()>

    </#if>
    <div class="container mt-3">
        <div class="row">
            <ul class="pagination col justify-content">
                <li class="page-item disabled">
                    <a class="page-link" href="#">Страницы
                    </a>
                </li>
                <#list body as p>
                    <#if (p - 1) == news.getNumber()>
                        <li class="page-item active">
                            <a class="page-link" href="#">${p}
                            </a>
                        </li>
                    <#elseif p  == -1>
                        <li class="page-item disabled">
                            <a class="page-link" href="#">...
                            </a>
                        </li>>
                    <#else>
                        <li class="page-item">
                            <a class="page-link" href="${url}?page=${p - 1}&size=${news.getSize()}">${p}
                            </a>
                        </li>
                    </#if>
                </#list>
            </ul>
            <ul class="pagination col justify-content">
                <li class="page-item disabled">
                    <a class="page-link" href="#">Элементов на странице
                    </a>
                </li>
                <#list [5, 10, 25, 50] as c>
                    <#if c == news.getSize()>
                        <li class="page-item active">
                            <a class="page-link" href="#">${c}
                            </a>
                        </li>
                    <#else>
                        <li class="page-item">
                            <a class="page-link" href="${url}?page=${news.getNumber()}&size=${c}">${c}
                            </a>
                        </li>
                    </#if>
                </#list>
            </ul>
        </div>
    </div>
</#macro>