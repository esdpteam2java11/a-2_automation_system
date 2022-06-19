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

function addSelectedParent(parent) {
    let tbody = document.querySelector('#relative-table>.table-body')
    let newTrTag = document.createElement("tr")
    newTrTag.innerHTML = `<td hidden><input type="hidden" name="id">${parent.id}</input></td>
                                  <td><input type="hidden" name="kinship">${parent.kinship}</input></td>
                                  <td><input type="hidden" name="surname">${parent.surname}</input></td>
                                  <td><input type="hidden" name="name">${parent.name}</input></td>
                                  <td><input type="hidden" name="patronymic">${parent.patronymic}</input></td>
                                  <td><input type="hidden" name="phone">${parent.phone}</input></td>
                                  <td><input type="hidden" name="whatsapp">${parent.whatsapp}</input></td>
                                  <td><input type="hidden" name="telegram">${parent.telegram}</input></td>
                                  <td><input type="hidden" name="login">${parent.login}</input></td>`
    tbody.appendChild(newTrTag)
}

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

document.getElementsByClassName("search-relative-modal-close")[0].addEventListener("click", function (){
    const searchResult = document.querySelector('#search-relative-table>.table-body')
    while (searchResult.firstElementChild){
        searchResult.firstElementChild.remove()
    }
})