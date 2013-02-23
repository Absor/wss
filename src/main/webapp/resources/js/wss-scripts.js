$(document).ready(function() {
    // logged in user
    wss.loggedUser = new wss.model.LoggedUser({});
    // add navigation view
    new wss.view.NavigationView();
    // add login/logout view
    new wss.view.LoginView();
    // start with week view
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
            url: "/wss/users/loggedin",
            success: function(data) {
                // if we get logged user info we set it to model, otherwise unset
                if (data) {
                    wss.loggedUser.set(data);
                } else {
                    wss.loggedUser.unset("username");
                }
            }
        });
    },
    alertHtml: function(alertText) {
        var source = $("#alert-template").html();
        var template = Handlebars.compile(source);
        return template({alert: alertText});
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
            url: "/login",
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
            $("#alert-view").html(wss.alertHtml("Wrong username or password."));
        }
    },
    logout: function(eventInfo) {
        // prevent default submit action
        eventInfo.preventDefault();
        // make a request
        $.ajax({
            type: "GET",
            url: "/logout",
            success: wss.updateUser
        });
    },
    render: function() {
        // put template through handlebars
        var source = $("#login-view-template").html();
        var template = Handlebars.compile(source);
        var html;
        if (wss.loggedUser.has("username")) {
            html = template({username: wss.loggedUser.get("username")});
        } else {
            html = template({});
        }
        this.$el.html(html);
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
        // unbind clicks from view, otherwise they keep on stacking!
        this.$el.unbind("click");
        this.$el.html($("#week-view-template").html());
    },
    events: {
    }
});

// shift view
wss.view.ShiftView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        // set and update model
        this.model = new wss.model.ShiftList();
        this.model.on("all", this.render, this);
        this.model.fetch({add: true});
        // unbind clicks from view, otherwise they keep on stacking!
        this.$el.unbind("click");
        // show this view
        this.render();
    },
    events: {
        "click #show-modal-button": "showModal",
        "click #close-modal-button": "closeModal",
        "click #add-shift-button": "addShift",
        "click tr td button": "deleteShift"
    },
    showModal: function(eventInfo) {
        eventInfo.preventDefault();
        $('#shift-add-modal').modal({
            backdrop: "static",
            keyboard: false
        });
    },
    closeModal: function(eventInfo) {
        eventInfo.preventDefault();
        $('#shift-add-modal').modal('hide');
    },
    addShift: function(eventInfo) {
        eventInfo.preventDefault();
        // remove errors if any
        $("#shift-name-control").removeClass("error");
        $("#start-time-control").removeClass("error");
        $("#end-time-control").removeClass("error");
        // get form contents and check for errors
        var shiftName = $("#shift-name-field").val();
        // change times to dates
        var startTime = this.timeStringToDateValue($("#start-time-field").val());
        var endTime = this.timeStringToDateValue($("#end-time-field").val());
        // new model
        var shift = new wss.model.WorkShift({
            shiftName: shiftName,
            startTime: startTime,
            endTime: endTime
        });
        // check for validity
        var error = shift.validate();
        if (error) {
            if (error.field === "shiftName") {
                $("#shift-name-control").addClass("error");
            } else if (error.field === "startTime") {
                $("#start-time-control").addClass("error");
            } else if (error.field === "endTime") {
                $("#end-time-control").addClass("error");
            }
            return;
        }
        // close modal
        $('#shift-add-modal').modal('hide');
        // add to model
        this.model.create(shift);
    },
    timeStringToDateValue: function(timeString) {
        var time = new Date();
        time.setHours(parseInt(timeString.substring(0, 2), 10));
        time.setMinutes(parseInt(timeString.substring(3, 5), 10));
        time.setSeconds(0);
        return time.valueOf();
    },
    deleteShift: function(eventInfo) {
        eventInfo.preventDefault();
        var id = $(eventInfo.target).data("id");
        if (id) {
            this.model.find(function(item) {
                return item.id === id;
            }).destroy();
        }
    },
    render: function() {
        // put template through handlebars
        var source = $("#shift-view-template").html();
        var template = Handlebars.compile(source);
        var jsonData = this.model.toJSON();
        // format dates
        $.each(jsonData, function(index, item) {
            item.startTime = new Date(item.startTime);
            item.endTime = new Date(item.endTime);
        });
        var html = template({shift: jsonData});
        this.$el.html(html);
        // set up timepickers
        var pickerSettings = {
            format: "hh:ii",
            startView: "day",
            minView: "hour",
            maxView: "day",
            pickerPosition: "bottom-left",
            autoclose: true
        };
        $("#start-timepicker").datetimepicker(pickerSettings);
        $("#end-timepicker").datetimepicker(pickerSettings);
    }
});

// employee view
wss.view.EmployeeView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        // unbind clicks from view, otherwise they keep on stacking!
        this.$el.unbind("click");
        this.$el.html($("#employee-view-template").html());
    },
    events: {
    }
});

// MODELS

wss.model.LoggedUser = Backbone.Model.extend({});

wss.model.WorkShift = Backbone.Model.extend({
    idAttribute: "id",
    validate: function(attrs) {
        var attrsToUse = attrs;
        if (!attrsToUse) {
            attrsToUse = this.attributes;
        }
        if (!attrsToUse.shiftName) {
            return {field: "shiftName", error: "Invalid name."};
        }
        if (attrsToUse.shiftName && attrsToUse.shiftName.length < 3) {
            return {field: "shiftName", error: "Name too short."};
        }
        if (!attrsToUse.startTime) {
            return {field: "startTime", error: "Invalid start time."};
        }
        if (!attrsToUse.endTime) {
            return {field: "endTime", error: "Invalid end time."};
        }
    }
});

wss.model.ShiftList = Backbone.Collection.extend({
    model: wss.model.WorkShift,
    url: "/wss/shifts"
});