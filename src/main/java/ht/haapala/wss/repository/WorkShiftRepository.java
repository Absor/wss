package ht.haapala.wss.repository;

import ht.haapala.wss.data.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heikki Haapala
 */
@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {
}
