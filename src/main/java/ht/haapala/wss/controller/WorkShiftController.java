package ht.haapala.wss.controller;

import ht.haapala.wss.data.WorkShift;
import ht.haapala.wss.service.WorkShiftService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "shifts")
public class WorkShiftController {

    @Autowired
    private WorkShiftService shiftService;

    @RequestMapping(value = "", method = RequestMethod.POST,
    consumes = "application/json", produces = "application/json")
    @ResponseBody
    public WorkShift create(@Valid @RequestBody WorkShift shift) {
        return shiftService.save(shift);
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<WorkShift> list() {
        return shiftService.findAll();
    }
    
    @RequestMapping(value = "{shiftId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long shiftId) {
        shiftService.delete(shiftId);
    }
}
