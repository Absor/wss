package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Heikki Haapala
 */
public interface PlannedShiftService {
    
    List<PlannedShift> findByDate(Date date);
    
    List<PlannedShift> findByWeek(int weekNumber);

    List<PlannedShift> findAll();

    PlannedShift save(PlannedShift shift);

    void delete(Long plannedShiftId);
}