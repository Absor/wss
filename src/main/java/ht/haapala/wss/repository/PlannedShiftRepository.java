package ht.haapala.wss.repository;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WSSUser;
import ht.haapala.wss.data.WorkShift;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heikki Haapala
 */
@Repository
public interface PlannedShiftRepository extends JpaRepository<PlannedShift, Long> {

    List<PlannedShift> findByShiftDate(Date date);
    
    List<PlannedShift> findByShiftDateBetween(Date start, Date end);
    
    PlannedShift findByShiftDateAndShift(Date date, WorkShift shift);
    
    List<PlannedShift> findByEmployee(WSSUser employee);
    
    List<PlannedShift> findByShift(WorkShift shift);
}
