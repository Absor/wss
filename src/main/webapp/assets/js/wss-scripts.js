$(document).ready(function() {
    // Calendar settings
    //    $("#calendar").fullCalendar({
    //        weekends: false,
    //        firstday: 1,
    //        center: "month",
    //        defaultView: "agendaWeek",
    //        allDaySlot: false,
    //        axisFormat: "HH:mm",
    //        dayNames: ['Sunnuntai', 'Maanantai', 'Tiistai', 'Keskiviikko', 'Torstai', 'Perjantai', 'Lauantai'],
    //        dayNamesShort: ['Su', 'Ma', 'Ti', 'Ke', 'To', 'Pe', 'La'],
    //        columnFormat: {week: "ddd d.M."}
    //    });

    $("#loginButton").click(function() {
        var username = $("#usernameField").val();
        var password = $("#passwordField").val();
        console.log(username + " " + password);
        $.ajax({
            type: "POST",
            url: "j_spring_security_check",
            data: {
                j_username: username, 
                j_password: password
            }
        }).done(function(message) {
            console.log(message);
        });
    });
});
