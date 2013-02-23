package ht.haapala.wss.repository;

import ht.haapala.wss.data.PlannedShift;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heikki Haapala
 */
@Repository
public interface PlannedShiftRepository extends JpaRepository<PlannedShift, Long> {

    List<PlannedShift> findByShiftDate(Date date);
    
    List<PlannedShift> findByShiftDateBetween(Date start, Date end);
}
