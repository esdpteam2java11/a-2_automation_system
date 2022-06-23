'use strict'

document.querySelector("#input-generate").addEventListener("click", function () {
    document.querySelector("#input-password").value = (generatePassword());
})

function generatePassword() {
    let length = 8,
        charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@-#$";
    if (window.crypto && window.crypto.getRandomValues) {
        return Array(length)
            .fill(charset)
            .map(x => x[Math.floor(crypto.getRandomValues(new Uint32Array(1))[0] / (0xffffffff + 1) * (x.length + 1))])
            .join('');
    } else {
        let res = '';
        for (let i = 0, n = charset.length; i < length; ++i) {
            res += charset.charAt(Math.floor(Math.random() * n));
        }
        return res;
    }
}

const searchRelativeForm = document.getElementById("search-relative-frm")
const searchRelativeBtn = document.getElementById("search-relative-btn")
const relativesTableBody = document.querySelector('#relative-table>.table-body')

function addSelectedParent(parent) {
    let newTrTag = document.createElement("tr")
    newTrTag.innerHTML = `<td hidden><input type="hidden" name="p_id" value="${parent.id}">${parent.id}</input></td>
                                  <td><input type="hidden" name="p_kinship" value="${parent.kinship}">${parent.kinship}</input></td>
                                  <td><input type="hidden" name="p_surname" value="${parent.surname}">${parent.surname}</input></td>
                                  <td><input type="hidden" name="p_name" value="${parent.name}">${parent.name}</input></td>
                                  <td><input type="hidden" name="p_patronymic" value="${parent.patronymic}">${parent.patronymic}</input></td>
                                  <td><input type="hidden" name="p_phone" value="${parent.phone}">${parent.phone}</input></td>
                                  <td><input type="hidden" name="p_whatsapp" value="${parent.whatsapp}">${parent.whatsapp}</input></td>
                                  <td><input type="hidden" name="p_telegram" value="${parent.telegram}">${parent.telegram}</input></td>
                                  <td><input type="hidden" name="p_login" value="${parent.login}">${parent.login}</input></td>`
    relativesTableBody.appendChild(newTrTag)
}

const addNewParentBtn = document.getElementById("add-new-parent-button")

addNewParentBtn.addEventListener("click", function () {
    const newTrTag = document.createElement("tr")
    newTrTag.innerHTML = `<td class="p-0" hidden><input type="hidden" name="p_id">null</input></td>
                                  <td class="p-0">
                                      <select name="p_kinship" required>
                                        <option value="FATHER">Отец</option>
                                        <option value="MOTHER">Мать</option>
                                        <option value="GRANDMOTHER">Бабушка</option>
                                        <option value="GRANDFATHER">Дедушка</option>
                                        <option value="SISTER">Сестра</option>
                                        <option value="BROTHER">Брат</option>
                                        <option value="GUARDIAN">Опекун</option>
                                      </select>
                                  </td>
                                  <td class="p-0"><input type="text" name="p_surname" required/></td>
                                  <td class="p-0"><input type="text" name="p_name" required/></td>
                                  <td class="p-0"><input type="text" name="p_patronymic"/></td>
                                  <td class="p-0"><input type="text" name="p_phone" required/></td>
                                  <td class="p-0"><input type="text" name="p_whatsapp"/></td>
                                  <td class="p-0"><input type="text" name="p_telegram"/></td>
                                  <td class="p-0"><input type="text" name="p_login"/></td>`
    relativesTableBody.appendChild(newTrTag)
})

searchRelativeForm.addEventListener("submit", async function (e) {
    e.preventDefault()
    searchRelativeBtn.setAttribute("disabled", 'true')
    try {
        const myHeaders = new Headers()
        const requestOptions = {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow'
        }
        const result = await fetch(`/create/parentSearch/` + document.getElementById("surnameSearch").value, requestOptions)
        const data = await result.json()
        let tbody = document.querySelector('#search-relative-table>.table-body')
        for (let i = 0; i < data.length; i++) {
            var parent = JSON.stringify(data[i]);
            let newTrTag = document.createElement("tr")
            newTrTag.innerHTML = `<td hidden>${data[i].id}</td>
                                  <td>${data[i].kinship}</td>
                                  <td>${data[i].surname}</td>
                                  <td>${data[i].name}</td>
                                  <td>${data[i].patronymic}</td>
                                  <td>
                                        <input class="form-check-input" type="checkbox" onclick='addSelectedParent(${parent})'>
                                        <label class="form-check-label" for="inlineCheckbox"></label>
                                  </td>`
            tbody.appendChild(newTrTag)
        }
    } finally {
        searchRelativeForm.reset()
        searchRelativeBtn.removeAttribute("disabled")

    }
})

document.getElementsByClassName("search-relative-modal-close")[0].addEventListener("click", function () {
    const searchResult = document.querySelector('#search-relative-table>.table-body')
    while (searchResult.firstElementChild) {
        searchResult.firstElementChild.remove()
    }
})