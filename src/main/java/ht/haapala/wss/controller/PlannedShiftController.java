package ht.haapala.wss.controller;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.Week;
import ht.haapala.wss.service.PlannedShiftService;
import ht.haapala.wss.service.WSSUserService;
import ht.haapala.wss.service.WorkShiftService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTimeConstants;
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
        PlannedShift newShift = new PlannedShift();
        newShift.setEmployee(userService.findOne(username));
        newShift.setShift(shiftService.findOne(shiftId));
        newShift.setShiftDate(date);
        return plannedShiftService.save(newShift);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public List<PlannedShift> list() {
        LocalDate now = LocalDate.now();
        return plannedShiftService.findByWeek(now.getWeekOfWeekyear());
    }
    
    @RequestMapping(value = "{weekNumber}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public List<PlannedShift> listByWeek(@PathVariable int weekNumber) {
        return plannedShiftService.findByWeek(weekNumber);
    }
    
    @RequestMapping(value = "weekinfo", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public Week weekInfo() {
        return new Week();
    }
    @RequestMapping(value = "weekinfo/{weekNumber}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public Week weekInfoByNumber(@PathVariable int weekNumber) {
        return new Week(weekNumber);
    }

    @RequestMapping(value = "{plannedShiftId}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasRole('employer')")
    public void delete(@PathVariable Long plannedShiftId) {
        plannedShiftService.delete(plannedShiftId);
    }
}
