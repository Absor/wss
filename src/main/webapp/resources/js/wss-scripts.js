$(document).ready(function() {
    $("#loginButton").click(function() {
        // get values
        var username = $("#usernameField").val();
        var password = $("#passwordField").val();
        // make a request
        $.ajax({
            type: "POST",
            url: "login",
            data: {
                j_username: username,
                j_password: password
            },
            success: function(data) {
                console.log(data);
            }
        });
    });
});
