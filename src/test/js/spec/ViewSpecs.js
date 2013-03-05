describe("Backbone view tests", function() {
    describe("LoginView", function() {
        beforeEach(function() {
            this.view = new wss.view.LoginView();
        });
    });

    describe("NavigationView", function() {
        beforeEach(function() {
            this.view = new wss.view.NavigationView();
        });
    });

    describe("WeekView", function() {
        beforeEach(function() {
            this.view = new wss.view.WeekView();
        });
    });

    describe("ShiftView", function() {
        beforeEach(function() {
            this.view = new wss.view.ShiftView();
        });
    });

    describe("UserView", function() {
        beforeEach(function() {
            this.view = new wss.view.UserView();
        });
    });
});