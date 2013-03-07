package ht.haapala.wss.service;

import ht.haapala.wss.data.BareUser;
import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WorkShift;
import ht.haapala.wss.repository.PlannedShiftRepository;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Planned shift service.
 * 
 * @author Heikki Haapala
 */
@Service
public class PlannedShiftServiceImpl implements PlannedShiftService {

    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    /**
     * Handles transactions for finding planned shifts by date.
     * 
     * @param date given date
     * @return a list of planned shifts for the given date
     */
    @Override
    @Transactional(readOnly=true)
    public List<PlannedShift> findByDate(Date date) {
        List<PlannedShift> findByShiftDate = plannedShiftRepository.findByShiftDate(date);
        if (findByShiftDate != null) {
            for (PlannedShift shift : findByShiftDate) {
                // hide user info
                shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
            }
        }
        return findByShiftDate;
    }

    /**
     * Handles transactions for finding planned shifts by given week.
     * 
     * @param year given year
     * @param weekNumber given week
     * @return a list of planned shifts for the given week
     */
    @Override
    @Transactional(readOnly=true)
    public List<PlannedShift> findByWeek(int year, int weekNumber) {
        LocalDate thisMonday = LocalDate.now().withYear(year).withWeekOfWeekyear(weekNumber).withDayOfWeek(DateTimeConstants.MONDAY);
        LocalDate thisSunday = thisMonday.withDayOfWeek(DateTimeConstants.SUNDAY);
        List<PlannedShift> findByShiftDateBetween = plannedShiftRepository.findByShiftDateBetween(thisMonday.toDate(), thisSunday.toDate());
        if (findByShiftDateBetween != null) {
            for (PlannedShift shift : findByShiftDateBetween) {
                // hide user info
                shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
            }
        }
        return findByShiftDateBetween;
    }

    /**
     * Handles transactions for finding all planned shifts.
     * 
     * @return a list of all planned shifts
     */
    @Override
    @Transactional(readOnly=true)
    public List<PlannedShift> findAll() {
        List<PlannedShift> findAll = plannedShiftRepository.findAll();
        if (findAll != null) {
            for (PlannedShift shift : findAll) {
                // hide user info
                shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
            }
        }
        return findAll;
    }

    /**
     * Handles transactions for saving a new planned shift.
     * 
     * @param shift the shift to be saved
     * @return the saved shift
     */
    @Override
    @Transactional(readOnly=false)
    public PlannedShift save(PlannedShift shift) {
        PlannedShift save = plannedShiftRepository.save(shift);
        if (save != null) {
            // hide user info
            save.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
        }
        return save;
    }

    /**
     * Handles transactions for deleting a planned shift.
     * 
     * @param plannedShiftId id of the shift to be deleted
     */
    @Override
    @Transactional(readOnly=false)
    public void delete(Long plannedShiftId) {
        plannedShiftRepository.delete(plannedShiftId);
    }

    /**
     * Handles transactions to find a planned shift by date and work shift.
     * 
     * @param date given date
     * @param shift given work shift
     * @return planned shift for the date and shift
     */
    @Override
    @Transactional(readOnly=true)
    public PlannedShift findByDateAndShift(Date date, WorkShift shift) {
        PlannedShift findByShiftDateAndShift = plannedShiftRepository.findByShiftDateAndShift(date, shift);
        if (findByShiftDateAndShift != null) {
            findByShiftDateAndShift.setBareEmployee(new BareUser(findByShiftDateAndShift.getEmployee().getUsername()));
        }
        return findByShiftDateAndShift;
    }
}
