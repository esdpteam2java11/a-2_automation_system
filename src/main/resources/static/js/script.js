'use strict'

$(document).ready(function () {
    $('.js-selectize').selectize();
});

const generatePassBtn = document.querySelector("#input-generate")

if (generatePassBtn != null) {
    generatePassBtn.addEventListener("click", function () {
        document.querySelector("#input-password").value = (generatePassword());
    })
}

if (generatePassBtn != null) {
    generatePassBtn.addEventListener("click", function () {
        const inputPassword = document.querySelector("#input-password")
        inputPassword.value = (generatePassword())
        inputPassword.removeAttribute("type")
        inputPassword.setAttribute("type", "text")
    })
}

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
    const listAddedParents = Array.from(document.getElementsByName("p_id")).map(inputElement => inputElement.value)
    let isExists = false

    if (listAddedParents.length > 0) {
        for (let i = 0; i < listAddedParents.length; i++) {
            if (listAddedParents[i] === parent.id) {
                alert("Данная запись уже занесена в таблицу!")
                isExists = true
            }
        }
    }
    if (!isExists) {
        let newTrTag = document.createElement("tr")
        newTrTag.innerHTML = `<td class="p-0" hidden><input type="hidden" name="p_id" value="${parent.id}">${parent.id}</input></td>
                                  <td class="p-0 m-0"><input class="form-control" type="hidden" name="p_kinship" value="${parent.kinshipName}">${parent.kinship}</input></td>
                                  <td class="p-0 m-0"><input class="form-control" type="hidden" name="p_surname" value="${parent.surname}">${parent.surname}</input></td>
                                  <td class="p-0 m-0"><input class="form-control" type="hidden" name="p_name" value="${parent.name}">${parent.name}</input></td>
                                  <td class="p-0 m-0"><input class="form-control" type="hidden" name="p_patronymic" value="${parent.patronymic != null ? parent.patronymic : ""}">${parent.patronymic != null ? parent.patronymic : ""}</input></td>
                                  <td class="p-0 m-0"><input class="form-control" type="hidden" name="p_phone" value="${parent.phone}">${parent.phone}</input></td>
                                  <td class="p-0 m-0"<input class="form-control" type="hidden" name="p_whatsapp" value="${parent.whatsapp != null ? parent.whatsapp : ""}">${parent.whatsapp != null ? parent.whatsapp : ""}</input></td>
                                  <td class="p-0 m-0"><input class="form-control" type="hidden" name="p_telegram" value="${parent.telegram != null ? parent.telegram : ""}">${parent.telegram != null ? parent.telegram : ""}</input></td>
                                  <td class="text-center p-0 m-0">
                                        <button type="button" class="btn btn-outline-danger py-1 px-2" onclick='deleteSelectedRow(this)'>
                                            <i class="bi bi-x-square"></i>
                                        </button>
                                  </td>`
        relativesTableBody.appendChild(newTrTag)
    }
}

function deleteSelectedRow(button) {
    button.closest('tr').remove()
}

const addNewParentBtn = document.getElementById("add-new-parent-button")

if (addNewParentBtn != null) {
    addNewParentBtn.addEventListener("click", function () {
        const newTrTag = document.createElement("tr")
        newTrTag.innerHTML = `<td class="p-0" hidden><input type="hidden" name="p_id" value="0"/></td>
                          <td class="p-0 m-0">
                                <select class="form-control" name="p_kinship" required>
                                    <option value="FATHER">Отец</option>
                                    <option value="MOTHER">Мать</option>
                                    <option value="GRANDMOTHER">Бабушка</option>
                                    <option value="GRANDFATHER">Дедушка</option>
                                    <option value="SISTER">Сестра</option>
                                    <option value="BROTHER">Брат</option>
                                    <option value="GUARDIAN">Опекун</option>
                                </select>
                          </td>
                          <td class="p-0 m-0"><input class="form-control" type="text" name="p_surname" required/></td>
                          <td class="p-0 m-0"><input class="form-control" type="text" name="p_patronymic" value=" "/></td>
                          <td class="p-0 m-0"><input class="form-control" type="text" name="p_name" required/></td>
                          <td class="p-0 m-0"><input class="form-control" type="text" name="p_phone" required/></td>
                          <td class="p-0 m-0"><input class="form-control" type="text" name="p_whatsapp" value=" "/></td>
                          <td class="p-0 m-0"><input class="form-control" type="text" name="p_telegram" value=" "/></td>
                          <td class="text-center p-0 m-0">
                                <button type="button" class="btn btn-outline-danger py-1 px-2" onclick='deleteSelectedRow(this)'>
                                     <i class="bi bi-x-square"></i>
                                </button>
                          </td>`
        relativesTableBody.appendChild(newTrTag)
    })
}

if (searchRelativeForm != null) {
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
                newTrTag.innerHTML = `<td class="text-center" hidden>${data[i].id}</td>
                                  <td class="text-center">${data[i].kinship}</td>
                                  <td class="text-center">${data[i].surname}</td>
                                  <td class="text-center">${data[i].name}</td>
                                  <td class="text-center">${data[i].patronymic != null ? data[i].patronymic : ""}</td>
                                  <td class="text-center">
                                        <input type="checkbox" onclick='addSelectedParent(${parent})'>
                                        <label class="form-check-label" for="inlineCheckbox"></label>
                                  </td>`
                tbody.appendChild(newTrTag)
            }
        } finally {
            searchRelativeForm.reset()
            searchRelativeBtn.removeAttribute("disabled")

        }
    })
}

const searchRelativeModalClose = document.getElementsByClassName("search-relative-modal-close")
if (searchRelativeModalClose.length > 0) {
    searchRelativeModalClose[0].addEventListener("click", function () {
        const searchResult = document.querySelector('#search-relative-table>.table-body')
        while (searchResult.firstElementChild) {
            searchResult.firstElementChild.remove()
        }
    })
}

async function checkIfColorExists(color) {
    let substring = '%23' + color.substring(1, 8);

    const myHeaders = new Headers()
    const requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    }
    const result = await fetch('/group/color?color=' + substring, requestOptions)
    const isColorExists = await result.json()

    if (isColorExists) {
        alert("Данный цвет уже присвоен другой группе.\nСмените пожалуйста цвет.")
    }
}

async function getGroupPrice(id) {
    const myHeaders = new Headers()
    const requestOptions = {
        method: 'GET',
        headers: myHeaders
    }
    let sum = 0;
    if (id !== undefined) {
        await fetch(`/group/price/${id}`, requestOptions)
            .then(response => response.text())
            .then(result => {
                sum = result;
            })
            .catch(error => console.log('error', error))
    }
    document.getElementById('recipient-sum').value = sum
}


//-----------------------------------------------------------------------------------------
const sportsmanPaymentDetailsTableBody = document.querySelector('#payment-period>.table-body')

function showDetails(value) {
    const sportsmanPaymentDetails = document.getElementById('sportsman-payment-details')
    const incomeSum = document.getElementById('income-sum')

    if (value === "SPORTSMAN_PAYMENT") {
        sportsmanPaymentDetails.style.display = 'block'
        incomeSum.setAttribute('readonly', 'readonly')
    } else {
        sportsmanPaymentDetails.style.display = 'none'
        incomeSum.removeAttribute('readonly')
        incomeSum.value = 0
        while (sportsmanPaymentDetailsTableBody.firstChild) {
            sportsmanPaymentDetailsTableBody.firstElementChild.remove()
        }
    }
}

function addNewPaymentDetail() {
    const newTrTag = document.createElement("tr")
    newTrTag.innerHTML = `<td ><input class="form-control" type="date" name="dateSportsman" required/></td>
                          <td >
                                <input class="form-control sum" type="number" 
                                name="amountSportsman" min="1" required/>
                          </td>
                          <td>
                                <button type="button" class="btn btn-outline-danger py-1 px-2" 
                                    onclick='deleteSelectedRow(this); setTotalSum()'>
                                        <i class="bi bi-x-square"></i>
                                </button>
                          </td>`
    sportsmanPaymentDetailsTableBody.appendChild(newTrTag)
}

function setTotalSum() {
    let incomeSum = document.getElementById('income-sum')
    let tableSums = document.getElementsByClassName('sum')
    let total = 0
    for (let i = 0; i < tableSums.length; i++) {
        total += Number(tableSums[i].value)
    }
    incomeSum.value = total
}

