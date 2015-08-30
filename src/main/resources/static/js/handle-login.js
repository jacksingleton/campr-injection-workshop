$(function() {
    $('form').submit(function(e) {
        e.preventDefault();

        var vendor = $('[name=vendor]');
        var password = $('[name=password]');

        var loginData = {
            vendor: vendor.val(),
            password : password.val()
        };

        $.post("/session", loginData, function(result) {
            alert(result);
        });

        vendor.val("");
        password.val('');
    });
});
