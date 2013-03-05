// base container
var wss = {
    emailRegExp: null,
    loggedUser: null,
    week: null,
    shifts: null,
    view: {},
    model: {},
    // helper functions
    // adds alert text to alert area
    alertHtml: function(alertText) {
        var source = $("#alert-template").html();
        var template = Handlebars.compile(source);
        return template({alert: alertText});
    },
    // formats date ms value to hh:mm form
    timeString: function(msDate) {
        var date = new Date(msDate);
        var hours = date.getHours();
        var minutes = date.getMinutes();
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        return hours + ":" + minutes;
    },
    // returns a comparable int value of hhmm
    timeInt: function(msDate) {
        var date = new Date(msDate);
        var hours = date.getHours();
        var minutes = date.getMinutes();
        if (hours < 10) {
            hours = "0" + hours;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        return parseInt(hours + "" + minutes);
    },
    // formats ms date value to dd.mm.
    dateString: function(msDate) {
        var date = new Date(msDate);
        var day = date.getDate();
        var month = date.getMonth();
        // months start from 0
        month++;
        var year = date.getFullYear();
        return {
            date: day + "." + month + ".",
            old: msDate
        };
    }
};


$(document).ready(function() {
    // models that are used with many views
    wss.shifts = new wss.model.ShiftList();
    wss.shifts.fetch();
    // logged in user
    wss.loggedUser = new wss.model.LoggedUser();
    wss.loggedUser.fetch();
    // week info
    wss.week = new wss.model.Week();
    wss.week.fetch();
    // add navigation view
    new wss.view.NavigationView();
    // add login/logout view
    new wss.view.LoginView();
    // start with week view
    new wss.view.WeekView();

    // email checking regexp
    wss.emailRegExp = new RegExp("[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}", "i");
});
