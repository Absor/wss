describe("Backbone model tests", function() {
    describe("Model Week", function() {
        beforeEach(function() {
            this.week = new wss.model.Week();
        });

        it("should have the right default parameters", function() {
            expect(this.week.week).toBe(null);
            expect(this.week.year).toBe(null);
            expect(this.week.url).toEqual("wss/plannedshifts/weekinfo");
        });

        it("should make the correct server request", function() {
            // Spy on jQuery's ajax method
            var spy = sinon.spy(jQuery, "ajax");

            // "fill" the model
            this.week.fetch();

            // Spy was called?
            expect(spy.called).toBe(true);

            // Check url property of first argument
            expect(spy.getCall(0).args[0].url).toEqual("wss/plannedshifts/weekinfo");

            // Restore jQuery.ajax to normal
            jQuery.ajax.restore();
        });

        it("should handle the server response", function() {
            // fake server
            var server = sinon.fakeServer.create();

            var callback = sinon.spy();

            server.respondWith("GET", "wss/plannedshifts/weekinfo",
                    [200, {"Content-Type": "application/json"},
                        '{"year":2013,"weekNumber":10,"days":[1362348000000,1362434400000,1362520800000,1362607200000,1362693600000]}']);

            // Bind to the change event on the model
            this.week.bind('change', callback);

            // makes an ajax request to the server
            this.week.fetch();

            // Fake server responds to the request
            server.respond();

            expect(callback.called).toBeTruthy();
            expect(callback.getCall(0).args[0].attributes)
                    .toEqual({
                year: 2013,
                weekNumber: 10,
                days: [1362348000000, 1362434400000, 1362520800000, 1362607200000, 1362693600000]
            });

            // parameters should be set now
            expect(this.week.week).toBe(10);
            expect(this.week.year).toBe(2013);

            // restore to normal
            server.restore();
        });

        it("should handle week changes", function() {
            // basic set
            this.week.set({weekNumber: 10, year: 2013});
            expect(this.week.week).toBe(10);
            expect(this.week.year).toBe(2013);

            // change week +1
            this.week.next();
            expect(this.week.url).toEqual("wss/plannedshifts/weekinfo/2013/11");
            expect(this.week.week).toBe(11);
            expect(this.week.year).toBe(2013);

            // change week -2
            this.week.last();
            this.week.last();
            expect(this.week.url).toEqual("wss/plannedshifts/weekinfo/2013/9");
            expect(this.week.week).toBe(9);
            expect(this.week.year).toBe(2013);

            // change to this week
            this.week.thisWeek();
            expect(this.week.url).toEqual("wss/plannedshifts/weekinfo");
            expect(this.week.week).toBe(null);
            expect(this.week.year).toBe(null);
        });
    });

    describe("Model LoggedUser", function() {
        beforeEach(function() {
            this.user = new wss.model.LoggedUser();
        });

        it("should make the correct server request", function() {
            // Spy on jQuery's ajax method
            var spy = sinon.spy(jQuery, "ajax");

            // "fill" the model
            this.user.fetch();

            // Spy was called?
            expect(spy.called).toBe(true);

            // Check url property of first argument
            expect(spy.getCall(0).args[0].url).toEqual("wss/users/loggedin");

            // Restore jQuery.ajax to normal
            jQuery.ajax.restore();
        });
    });

    describe("Model WorkShift", function() {
        beforeEach(function() {
            this.shift = new wss.model.WorkShift();
        });

        it("should validate attributes", function() {
            this.shift.set({shiftName: "te"});
            var error = this.shift.validate();
            // error should be in shiftName
            expect(error.field).toEqual("shiftName");

            this.shift.set({shiftName: "testi"});
            var error = this.shift.validate();
            // error should be in startTime (not existing)
            expect(error.field).toEqual("startTime");

            this.shift.set({shiftName: "testi", startTime: 1223});
            var error = this.shift.validate();
            // error should be in endTime (not existing)
            expect(error.field).toEqual("endTime");

            this.shift.set({shiftName: "testi", startTime: 1223, endTime: 1223});
            var error = this.shift.validate();
            // should be no error
            expect(error).toBe(undefined);
        });
    });

    describe("Model ShiftList", function() {
        beforeEach(function() {
            this.shifts = new wss.model.ShiftList();
        });

        it("should have the right url", function() {
            expect(this.shifts.url).toEqual("wss/shifts");
        });

        it("should arrange the shifts by start time", function() {
            this.shifts.add({startTime: 1});
            this.shifts.add({startTime: 2});
            this.shifts.add({startTime: 3});
            this.shifts.add({startTime: 4});

            var shiftsJSON = this.shifts.toJSON();

            expect(shiftsJSON[0].startTime).toBe(1);
            expect(shiftsJSON[1].startTime).toBe(2);
            expect(shiftsJSON[2].startTime).toBe(3);
            expect(shiftsJSON[3].startTime).toBe(4);
        });
    });

    describe("Model PlannedShiftList", function() {
        beforeEach(function() {
            this.shifts = new wss.model.PlannedShiftList();
        });

        it("should have the right url", function() {
            expect(this.shifts.url).toEqual("wss/plannedshifts");
        });

        it("should make the correct server request when fetching", function() {
            // Spy on jQuery's ajax method
            var spy = sinon.spy(jQuery, "ajax");

            // "fill" the model
            this.shifts.fetch();

            // Spy was called?
            expect(spy.called).toBe(true);

            // Check url property of first argument
            expect(spy.getCall(0).args[0].url).toEqual("wss/plannedshifts");

            // Restore jQuery.ajax to normal
            jQuery.ajax.restore();
        });

        it("should make the correct server request when saving", function() {
            // Spy on jQuery's ajax method
            var spy = sinon.spy(jQuery, "ajax");

            // create new
            this.shifts.create({bareEmployee: "testi", shift: "tasti"});

            // Spy was called?
            expect(spy.called).toBe(true);

            // Check url property of first argument
            expect(spy.getCall(0).args[0].url).toEqual("wss/plannedshifts/testi/tasti");

            // Restore jQuery.ajax to normal
            jQuery.ajax.restore();
        });

        it("should make the correct server request when deleting", function() {
            // create new
            this.shifts.create({bareEmployee: "testi", shift: "tasti"});
            this.shifts.models[0].id = 12345;

            // Spy on jQuery's ajax method
            var spy = sinon.spy(jQuery, "ajax");

            // delete item
            this.shifts.models[0].destroy();

            // Spy was called?
            expect(spy.called).toBe(true);

            // Check url property of first argument
            expect(spy.getCall(0).args[0].url).toEqual("wss/plannedshifts/12345");

            // Restore jQuery.ajax to normal
            jQuery.ajax.restore();
        });
    });

    describe("Model User", function() {
        beforeEach(function() {
            this.user = new wss.model.User();
        });

        it("should validate attributes", function() {
            wss.emailRegExp = new RegExp("[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}", "i");

            this.user.set({username: "te"});
            var error = this.user.validate();
            // error should be in username (too short)
            expect(error.field).toEqual("username");

            this.user.set({username: "testi"});
            var error = this.user.validate();
            // error should be in password (not existing)
            expect(error.field).toEqual("password");

            this.user.set({shiftName: "testi", barePassword: "testi"});
            var error = this.user.validate();
            // error should be in password (too short)
            expect(error.field).toEqual("password");

            this.user.set({shiftName: "testi", barePassword: "testitesti"});
            var error = this.user.validate();
            // error should be in email (not defined)
            expect(error.field).toBe("email");

            this.user.set({shiftName: "testi", barePassword: "testitesti", email: "tes"});
            var error = this.user.validate();
            // error should be in email (wrong format)
            expect(error.field).toBe("email");

            this.user.set({shiftName: "testi", barePassword: "testitesti", email: "tes@tes.com"});
            var error = this.user.validate();
            // error should be in role (not defined)
            expect(error.field).toBe("role");

            this.user.set({shiftName: "testi", barePassword: "testitesti", email: "tes@tes.com", role: "employee"});
            var error = this.user.validate();
            // error should be in enabled (not defined)
            expect(error.field).toBe("enabled");

            this.user.set({shiftName: "testi", barePassword: "testitesti", email: "tes@tes.com", role: "employee", enabled: true});
            var error = this.user.validate();
            // should be no errors
            expect(error).toBe(undefined);
        });
    });

    describe("Model UserList", function() {
        beforeEach(function() {
            this.users = new wss.model.UserList();
        });

        it("should make the correct server request", function() {
            // Spy on jQuery's ajax method
            var spy = sinon.spy(jQuery, "ajax");

            // "fill" the model
            this.users.fetch();

            // Spy was called?
            expect(spy.called).toBe(true);

            // Check url property of first argument
            expect(spy.getCall(0).args[0].url).toEqual("wss/users");

            // Restore jQuery.ajax to normal
            jQuery.ajax.restore();
        });
    });
});