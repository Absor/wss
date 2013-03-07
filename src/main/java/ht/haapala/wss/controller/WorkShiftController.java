package ht.haapala.wss.controller;

import ht.haapala.wss.data.WorkShift;
import ht.haapala.wss.service.WorkShiftService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for work shift data. Users JSON for input and output.
 * 
 * @author Heikki Haapala
 */
@Controller
@RequestMapping(value = "shifts")
public class WorkShiftController {

    @Autowired
    private WorkShiftService shiftService;

    /**
     * Handles POST requests to "shifts". Creates a new work shift with given
     * data.
     * 
     * @param shift given data from request body
     * @return saved work shift
     */
    @RequestMapping(value = "", method = RequestMethod.POST,
    consumes = "application/json", produces = "application/json")
    @ResponseBody
    @PreAuthorize("hasRole('employer')")
    public WorkShift create(@Valid @RequestBody WorkShift shift) {
        return shiftService.save(shift);
    }
    
    /**
     * Handles GET requests to "shifts". Returns a list of all work shifts.
     * 
     * @return a list of all work shifts
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @PreAuthorize("permitAll")
    public List<WorkShift> list() {
        return shiftService.findAll();
    }
    
    /**
     * Handles DELETE requests to "shifts/id". Deletes the shift with the given
     * id.
     * 
     * @param shiftId id from path
     */
    @RequestMapping(value = "{shiftId}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasRole('employer')")
    public void delete(@PathVariable Long shiftId) {
        shiftService.delete(shiftId);
    }
}
