$("#input-generate").click(function () {
    $("#input-password").val(generatePassword());
    return false;
});

function generatePassword() {
    var length = 8,
        charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@-#$";
    if (window.crypto && window.crypto.getRandomValues) {
        return Array(length)
            .fill(charset)
            .map(x => x[Math.floor(crypto.getRandomValues(new Uint32Array(1))[0] / (0xffffffff + 1) * (x.length + 1))])
            .join('');
    } else {
        res = '';
        for (var i = 0, n = charset.length; i < length; ++i) {
            res += charset.charAt(Math.floor(Math.random() * n));
        }
        return res;
    }
}