package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WorkShift;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Heikki Haapala
 */
public interface PlannedShiftService {
    
    PlannedShift findByDateAndShift(Date date, WorkShift shift);
    
    List<PlannedShift> findByDate(Date date);
    
    List<PlannedShift> findByWeek(int weekNumber);

    List<PlannedShift> findAll();

    PlannedShift save(PlannedShift shift);

    void delete(Long plannedShiftId);
}