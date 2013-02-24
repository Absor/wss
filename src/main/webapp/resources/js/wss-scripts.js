$(document).ready(function() {
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

    // email checking
    wss.emailRegExp = new RegExp("[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}", "i");
});

// base container
var wss = {
    emailRegExp: null,
    loggedUser: null,
    week: null,
    view: {},
    model: {},
    // helper functions
    alertHtml: function(alertText) {
        var source = $("#alert-template").html();
        var template = Handlebars.compile(source);
        return template({alert: alertText});
    },
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
            wss.loggedUser.fetch();
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
            url: "logout",
            success: this.logoutSuccess
        });
    },
    logoutSuccess: function() {
        // on successful logout clear user info
        wss.loggedUser.clear();
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
        new wss.view.UserView();
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
        // set and update model
        this.model = {
            shifts: new wss.model.ShiftList(),
            plannedShifts: new wss.model.PlannedShiftList(),
            users: new wss.model.UserList()
        };
        // listen to login to activate planning functionality
        this.listenTo(wss.loggedUser, "change", this.render);
        this.listenTo(wss.loggedUser, "change", this.checkLogin);
        // listen to week changes
        this.listenTo(wss.week, "change", this.render);
        // listen to all model changes
        this.model.shifts.on("all", this.render, this);
        this.model.plannedShifts.on("all", this.render, this);
        // fill models
        this.model.shifts.fetch();
        this.model.plannedShifts.fetch();
        // unbind clicks from view, otherwise they keep on stacking!
        this.$el.unbind("click");
        // show this view
        this.render();
        this.checkLogin();
    },
    events: {
        "click #minus-week-button": "minusWeek",
        "click #today-button": "today",
        "click #plus-week-button": "plusWeek"
    },
    minusWeek: function(eventInfo) {
        eventInfo.preventDefault();
        wss.week.last();
    },
    today: function(eventInfo) {
        eventInfo.preventDefault();
        wss.week.thisWeek();
    },
    plusWeek: function(eventInfo) {
        eventInfo.preventDefault();
        wss.week.next();
    },
    checkLogin: function() {
        if (wss.loggedUser.get("role") === "employer") {
            this.model.users.on("all", this.render, this);
            this.model.users.fetch();
        } else {
            this.model.users.off("all");
            this.model.users.reset();
        }
    },
    render: function() {
        // put template through handlebars
        var source = $("#week-view-template").html();
        var template = Handlebars.compile(source);

        var fullShiftView = {};

        var shiftsJSON = this.model.shifts.toJSON();

        // format dates
        $.each(shiftsJSON, function(index, item) {
            item.startTime = wss.timeString(item.startTime);
            item.endTime = wss.timeString(item.endTime);
        });

        var plannedShiftsJSON = this.model.plannedShifts.toJSON();

        // format week days for view
        var weekdaysJSON = wss.week.toJSON();
        // if we get the week info
        if (weekdaysJSON.weekNumber) {
            // make fullshift view contain each work day and each work day to
            // contain shift infos and dates
            var weekDays = this.formatWeek(weekdaysJSON);

            // add to shifts too
            var i = 0;
            $.each(weekDays, function(day, item) {
                if (day === "weekNumber") {
                    fullShiftView[day] = item;
                    return;
                }
                fullShiftView[day] = {
                    date: item,
                    shift: []
                };
                i++;
            });

            $.each(fullShiftView, function(day, item) {
                if (day === "weekNumber") {
                    return;
                }
                $.each(shiftsJSON, function(i, shift) {
                    item.shift[i] = {
                        id: shift.id
                    };
                });
            });

            // for each planned shift add them to view in their right place
            $.each(plannedShiftsJSON, function(i, planned) {
                planned.shiftDate = wss.dateString(planned.shiftDate);

                $.each(fullShiftView, function(i, day) {
                    // can be undefined (weeknumber)
                    if (day.date) {
                        if (day.date.date === planned.shiftDate.date) {
                            // if date matches, match shift
                            return $.each(day.shift, function(i, shift) {
                                if (shift.id === planned.shift.id) {
                                    // attach to found shift
                                    shift.planned = planned;
                                    return false;
                                }
                            });
                        }
                    }
                });
            });
        }

        var html = template({shift: shiftsJSON, week: fullShiftView});
        this.$el.html(html);

        // check for login and activate planning if needed
        this.addAdminElements();
    },
    addAdminElements: function() {
        $(".shift-block").unbind("click");
        // add planning events if logged in
        if (wss.loggedUser.get("role") === "employer") {
            // activate popovers
            var source = $("#user-selector-template").html();
            var template = Handlebars.compile(source);
            var employees = [];
            // show only employees
            var users = this.model.users.toJSON();
            $.each(users, function(i, user) {
                if (user.role === "employee") {
                    employees.push(user);
                }
            });
            var html = template({user: employees});

            $(".shift-block").popover({
                html: true,
                placement: "bottom",
                content: html,
                trigger: "manual"
            });

            // can't call with this. inside jQuery functions
            var pSModel = this.model.plannedShifts;

            // click on block
            $(".shift-block").click(function(eventInfo) {
                var clicked = eventInfo.target;
                // for every other, hide
                $(".shift-block").each(function(index, item) {
                    if (item === clicked) {
                        return;
                    }
                    $(item).popover("hide");
                });
                // toggle clicked
                var element = $(clicked);
                element.popover("toggle");

                // block info
                var shiftId = element.data("shift-id");
                var date = element.parent().parent().data("date");
                var plannedId = element.data("planned-id");

                // add listener for popover buttons
                $("#delete-planned-shift-button").click(function(eventInfo) {
                    eventInfo.preventDefault();
                    if (plannedId !== "") {
                        // destroy the planned shift with matching id
                        pSModel.find(function(shift) {
                            return shift.id === plannedId;
                        }).destroy();
                    }
                });
                $(".username-button").click(function(eventInfo) {
                    eventInfo.preventDefault();
                    var username = $(eventInfo.target).data("username");
                    pSModel.create({bareEmployee: username, shift: shiftId, shiftDate: date});
                });
            });
        }
    },
    formatWeek: function(content) {
        return {
            weekNumber: content.weekNumber,
            monday: wss.dateString(content.days[0]),
            tuesday: wss.dateString(content.days[1]),
            wednesday: wss.dateString(content.days[2]),
            thursday: wss.dateString(content.days[3]),
            friday: wss.dateString(content.days[4])
        };
    }
});

// shift view
wss.view.ShiftView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        // set and update model
        this.model = new wss.model.ShiftList();
        this.model.on("all", this.render, this);
        this.model.fetch();
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
        $('#shift-add-modal').modal("show");
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
                return item.get("id") === id;
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
            item.startTime = wss.timeString(item.startTime);
            item.endTime = wss.timeString(item.endTime);
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
        // set up modal
        $('#shift-add-modal').modal({
            backdrop: "static",
            keyboard: false,
            show: false
        });
    }
});

// user view
wss.view.UserView = Backbone.View.extend({
    el: $("#main-view"),
    initialize: function() {
        // set and update model
        this.model = new wss.model.UserList();
        this.model.on("all", this.render, this);
        this.model.fetch();
        // unbind clicks from view, otherwise they keep on stacking!
        this.$el.unbind("click");
        // show this view
        this.render();
    },
    events: {
        "click #show-modal-button": "showModal",
        "click #close-modal-button": "closeModal",
        "click #add-user-button": "addUser",
        "click tr td button": "deleteUser"
    },
    showModal: function(eventInfo) {
        eventInfo.preventDefault();
        $('#user-add-modal').modal("show");
    },
    closeModal: function(eventInfo) {
        eventInfo.preventDefault();
        $('#user-add-modal').modal('hide');
    },
    addUser: function(eventInfo) {
        eventInfo.preventDefault();
        // remove errors if any
        $("#user-name-control").removeClass("error");
        $("#password-control").removeClass("error");
        $("#email-control").removeClass("error");
        $("#role-control").removeClass("error");
        $("#enabled-control").removeClass("error");
        // get form contents and check for errors
        var username = $("#user-name-field").val();
        var password = $("#password-field").val();
        var email = $("#email-field").val();
        var role = $("#role-select").val();
        var enabled = $("#enabled-checkbox").is(':checked');
        // new model
        var user = new wss.model.User({
            username: username,
            barePassword: password,
            email: email,
            role: role.toLowerCase(),
            enabled: enabled
        });
        // check for validity
        var error = user.validate();
        if (error) {
            if (error.field === "username") {
                $("#user-name-control").addClass("error");
            } else if (error.field === "password") {
                $("#password-control").addClass("error");
            } else if (error.field === "email") {
                $("#email-control").addClass("error");
            } else if (error.field === "role") {
                $("#role-control").addClass("error");
            } else if (error.field === "enabled") {
                $("#enabled-control").addClass("error");
            }
            return;
        }
        // close modal
        $('#user-add-modal').modal('hide');
        // add to model
        this.model.create(user);
    },
    deleteUser: function(eventInfo) {
        eventInfo.preventDefault();
        var username = $(eventInfo.target).data("username");
        if (username) {
            this.model.find(function(item) {
                return item.get("username") === username;
            }).destroy();
        }
    },
    render: function() {
        // put template through handlebars
        var source = $("#user-view-template").html();
        var template = Handlebars.compile(source);
        var data = this.model.toJSON();
        // filter away admin
        var filtered = _.filter(data, function(user) {
            return user.username !== "admin";
        });
        // group by role
        var grouped = _.groupBy(filtered, function(user) {
            return user.role;
        });
        var html = template(grouped);
        this.$el.html(html);

        // set up modal
        $('#user-add-modal').modal({
            backdrop: "static",
            keyboard: false,
            show: false
        });
    }
});

// MODELS

wss.model.Week = Backbone.Model.extend({
    initialize: function() {
        this.on("change", this.checkYearAndWeek, this);
    },
    week: null,
    year: null,
    url: "wss/plannedshifts/weekinfo",
    last: function() {
        this.checkYearAndWeek();
        if (this.week && this.year) {
            this.week--;
            if (this.week < 1) {
                this.week = 52;
                this.year--;
            }
            this.setUrlandFetch();
        }
    },
    thisWeek: function() {
        this.week = null;
        this.year = null;
        this.url = "wss/plannedshifts/weekinfo";
        this.fetch();
    },
    next: function() {
        this.checkYearAndWeek();
        if (this.week && this.year) {
            this.week++;
            if (this.week > 52) {
                this.week = 1;
                this.year++;
            }
            this.setUrlandFetch();
        }
    },
    checkYearAndWeek: function() {
        this.week = this.attributes.weekNumber;
        this.year = this.attributes.year;
    },
    setUrlandFetch: function() {
        this.url = "wss/plannedshifts/weekinfo/" + this.year + "/" + this.week;
        this.fetch();
    }
});

wss.model.LoggedUser = Backbone.Model.extend({
    url: "wss/users/loggedin"
});

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
    url: "wss/shifts",
    comparator: function(shift1, shift2) {
        // sorts by start time
        var value = wss.timeInt(shift1.get("startTime")) - wss.timeInt(shift2.get("startTime"));
        if (value < 0) {
            return -1;
        }
        if (value === 0) {
            return 0;
        }
        if (value > 0) {
            return 1;
        }
    }
});

wss.model.PlannedShift = Backbone.Model.extend({
    idAttribute: "id",
    // custom create and delete functionality for planned shifts
    sync: function(method, model, options) {
        if (method === "create") {
            options.url = "wss/plannedshifts/" + model.get("bareEmployee") + "/" + model.get("shift");
            options.data = JSON.stringify(model.get("shiftDate"));
            options.contentType = "application/json";
        }
        if (method === "delete") {
            options.url = "wss/plannedshifts/" + model.id;
        }
        return Backbone.sync(method, model, options);
    }
});

wss.model.PlannedShiftList = Backbone.Collection.extend({
    initialize: function() {
        // on week changes set url
        this.listenTo(wss.week, "change", this.setUrl);
    },
    setUrl: function() {
        if (wss.week.year && wss.week.week) {
            this.url = "wss/plannedshifts/" + wss.week.year + "/" + wss.week.week;
        } else {
            this.url = "wss/plannedshifts";
        }
        this.fetch();
    },
    model: wss.model.PlannedShift,
    url: "wss/plannedshifts"
});

wss.model.User = Backbone.Model.extend({
    idAttribute: "id",
    validate: function(attrs) {
        var attrsToUse = attrs;
        if (!attrsToUse) {
            attrsToUse = this.attributes;
        }
        // username
        if (!attrsToUse.username) {
            return {field: "username", error: "Invalid username."};
        }
        if (attrsToUse.username && attrsToUse.username.length < 3) {
            return {field: "username", error: "Username too short."};
        }
        // password
        if (!attrsToUse.barePassword) {
            return {field: "password", error: "Invalid password."};
        }
        if (attrsToUse.barePassword && attrsToUse.barePassword.length < 8) {
            return {field: "password", error: "Password too short."};
        }
        // email
        if (!attrsToUse.email) {
            return {field: "email", error: "Invalid email."};
        }
        if (!wss.emailRegExp.test(attrsToUse.email)) {
            return {field: "email", error: "Email is not valid."};
        }
        // role
        if (!attrsToUse.role || (attrsToUse.role !== "employee" && attrsToUse.role !== "employer")) {
            return {field: "role", error: "Invalid role."};
        }
        // role
        if (attrsToUse.enabled !== true && attrsToUse.enabled !== false) {
            return {field: "enabled", error: "Invalid role."};
        }
    }
});

wss.model.UserList = Backbone.Collection.extend({
    model: wss.model.User,
    url: "wss/users"
});
