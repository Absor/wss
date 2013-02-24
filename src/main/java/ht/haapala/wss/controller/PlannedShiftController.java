package ht.haapala.wss.controller;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.data.Week;
import ht.haapala.wss.data.WorkShift;
import ht.haapala.wss.service.PlannedShiftService;
import ht.haapala.wss.service.WSSUserService;
import ht.haapala.wss.service.WorkShiftService;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Heikki Haapala
 */
@Controller
@RequestMapping(value = "plannedshifts")
public class PlannedShiftController {

    @Autowired
    PlannedShiftService plannedShiftService;
    @Autowired
    WorkShiftService shiftService;
    @Autowired
    WSSUserService userService;

    @RequestMapping(value = "{username}/{shiftId}", method = RequestMethod.POST,
    consumes = "application/json", produces = "application/json")
    @ResponseBody
    @PreAuthorize("hasRole('employer')")
    public PlannedShift create(@RequestBody Date date,
            @PathVariable String username, @PathVariable Long shiftId) {
        // only allow employees
        WSSUser employee = userService.findOne(username);
        if (employee.getRole().equals("employer")) {
            return null;
        }

        WorkShift workShift = shiftService.findOne(shiftId);
        // if there is a planned with same day and shift id, just update
        PlannedShift plannedShift = plannedShiftService.findByDateAndShift(date, workShift);
        // otherwise create new
        if (plannedShift == null) {
            plannedShift = new PlannedShift();
            plannedShift.setShift(workShift);
            plannedShift.setShiftDate(date);
        }
        plannedShift.setEmployee(employee);
        return plannedShiftService.save(plannedShift);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public List<PlannedShift> list() {
        LocalDate now = LocalDate.now();
        return plannedShiftService.findByWeek(now.getYear(), now.getWeekOfWeekyear());
    }

    @RequestMapping(value = "{year}/{weekNumber}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public List<PlannedShift> listByWeek(@PathVariable int year, @PathVariable int weekNumber) {
        return plannedShiftService.findByWeek(year, weekNumber);
    }

    @RequestMapping(value = "{plannedShiftId}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasRole('employer')")
    public void delete(@PathVariable Long plannedShiftId) {
        plannedShiftService.delete(plannedShiftId);
    }
    
    // Week info
    @RequestMapping(value = "weekinfo", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public Week weekInfo() {
        return new Week();
    }

    @RequestMapping(value = "weekinfo/{year}/{weekNumber}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public Week weekInfoByNumber(@PathVariable int year, @PathVariable int weekNumber) {
        return new Week(year, weekNumber);
    }
}
