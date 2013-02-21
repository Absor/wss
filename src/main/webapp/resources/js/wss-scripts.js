$(document).ready(function() {
    // logged in user
    wss.loggedUser = new wss.model.LoggedUser({});
    // add navigation view
    new wss.view.NavigationView();
    // add login/logout view
    new wss.view.LoginView();
    // main view
    new wss.view.WeekView();

    // update logged in user information
    wss.updateUser();
});

// base container
var wss = {
    view: {},
    model: {},
    loggedUser: null,
    updateUser: function() {
        $.ajax({
            type: "GET",
            url: "wss/users/loggedin",
            success: function(data) {
                // if we get logged user info we set it to model, otherwise unset
                if (data) {
                    wss.loggedUser.set(data);
                } else {
                    wss.loggedUser.unset("username");
                }
            }
        });
    }
};

// VIEWS

// upper right corner login/logout form
wss.view.LoginView = Backbone.View.extend({
    el: $("#login-view"),
    initialize: function() {
        // show login or logout screen
        this.render();
        // add listener to user changes
        this.listenTo(wss.loggedUser, "change", this.render);
    },
    events: {
        "click #login-button": "login",
        "click #logout-button": "logout"
    },
    login: function(eventInfo) {
        // prevent default submit action
        eventInfo.preventDefault();
        // get values
        var username = $("#username-field").val();
        var password = $("#password-field").val();
        // make a request
        $.ajax({
            type: "POST",
            url: "login",
            data: {
                j_username: username,
                j_password: password
            },
            success: this.checkLogin
        });
    },
    checkLogin: function(data) {
        if (data === "login_success") {
            $("#alert-view").html("");
            wss.updateUser();
        } else {
            $("#alert-view").html($("#login-alert-template").html());
        }
    },
    logout: function(eventInfo) {
        // prevent default submit action
        eventInfo.preventDefault();
        // make a request
        $.ajax({
            type: "GET",
            url: "logout",
            success: wss.updateUser
        });
    },
    render: function() {
        // if user is logged in show logout, otherwise show login
        if (wss.loggedUser.has("username")) {
            // put template through handlebars so we get username into view
            var source = $("#logout-view-template").html();
            var template = Handlebars.compile(source);
            var html = template({username: wss.loggedUser.get("username")});
            this.$el.html(html);
        } else {
            this.$el.html($("#login-view-template").html());
        }
    }
});

// upper left corner navigation menu
wss.view.NavigationView = Backbone.View.extend({
    el: $("#navigation-view"),
    initialize: function() {
        // show navigation
        this.render();
        // add listener to user changes
        this.listenTo(wss.loggedUser, "change", this.render);
    },
    events: {
        "click #week-view-link": "weekView",
        "click #shift-view-link": "shiftView",
        "click #employee-view-link": "employeeView"
    },
    weekView: function(eventInfo) {
        eventInfo.preventDefault();
        new wss.view.WeekView();
    },
    shiftView: function(eventInfo) {
        eventInfo.preventDefault();
        new wss.view.ShiftView();
    },
    employeeView: function(eventInfo) {
        eventInfo.preventDefault();
        new wss.view.EmployeeView();
    },
    render: function() {
        // put template through handlebars
        var source = $("#navigation-view-template").html();
        var template = Handlebars.compile(source);
        var html;
        if (wss.loggedUser.has("username")) {
            if (wss.loggedUser.get("role") === "employee") {
                html = template({employee: true});
            }
            if (wss.loggedUser.get("role") === "employer") {
                html = template({employer: true});
            }
        } else {
            html = template({});
        }
        this.$el.html(html);
    }
});

// main week view
wss.view.WeekView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        this.$el.html($("#week-view-template").html());
    },
    events: {
    }
});

// shift view
wss.view.ShiftView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        this.$el.html($("#shift-view-template").html());
    },
    events: {
    }
});

// employee view
wss.view.EmployeeView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        this.$el.html($("#employee-view-template").html());
    },
    events: {
    }
});

// MODELS

wss.model.LoggedUser = Backbone.Model.extend({});