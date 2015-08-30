$(function() {
    $('form').submit(function(e) {
        e.preventDefault();

        var username = $('[name=username]');
        var password = $('[name=password]');

        var loginData = {
            username : username.val(),
            password : password.val()
        };

        $.post("/session", loginData, function(result) {
            alert(result);
        });

        username.val('');
        password.val('');
    });
});
