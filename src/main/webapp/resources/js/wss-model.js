// Backbone models

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
            return {field: "enabled", error: "Invalid state."};
        }
    }
});

wss.model.UserList = Backbone.Collection.extend({
    model: wss.model.User,
    url: "wss/users"
});
