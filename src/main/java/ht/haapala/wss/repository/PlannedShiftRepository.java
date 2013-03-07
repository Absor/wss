package ht.haapala.wss.repository;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.data.WorkShift;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for planned shifts.
 * 
 * @author Heikki Haapala
 */
@Repository
public interface PlannedShiftRepository extends JpaRepository<PlannedShift, Long> {

    /**
     * Query to find all shifts by given date.
     * 
     * @param date given date
     * @return list of planned shifts of that date
     */
    List<PlannedShift> findByShiftDate(Date date);
    
    /**
     * Query to find shifts between start and end dates.
     * 
     * @param start start date
     * @param end end date
     * @return list of planned shifts between the dates
     */
    List<PlannedShift> findByShiftDateBetween(Date start, Date end);
    
    /**
     * Query to find a planned shift by date and work shift.
     * 
     * @param date date
     * @param shift work shift
     * @return planned shift for the given parameters if any
     */
    PlannedShift findByShiftDateAndShift(Date date, WorkShift shift);
    
    /**
     * Query to find planned shifts by employee.
     * 
     * @param employee employee whose planned shifts to find
     * @return a list of employee's planned shifts
     */
    List<PlannedShift> findByEmployee(WSSUser employee);
    
    /**
     * Query to find planned shifts by work shift.
     * 
     * @param shift work shift which planned shifts to find
     * @return a list of work shift's planned shifts 
     */
    List<PlannedShift> findByShift(WorkShift shift);
}
