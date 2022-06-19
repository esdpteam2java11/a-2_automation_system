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

searchRelativeForm.addEventListener("submit", async function (){
    searchRelativeBtn.setAttribute("disabled", 'true')
    try {
        const myHeaders = new Headers()
        const formData = new FormData(this);
        const requestOptions = {
            method: 'GET',
            headers: myHeaders,
            body: formData,
            redirect: 'follow'
        }
        const result = await fetch(`/create/parentSearch`, requestOptions)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                console.log(data)
            })
            .catch(error => console.log('error', error))
    } finally {
        searchRelativeForm.reset()
        searchRelativeBtn.removeAttribute("disabled")
    }
})