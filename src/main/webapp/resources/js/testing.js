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

    $("#logoutButton").click(function() {
        // make a request
        $.ajax({
            type: "GET",
            url: "logout",
            success: function(data) {
                console.log(data);
            }
        });
    });