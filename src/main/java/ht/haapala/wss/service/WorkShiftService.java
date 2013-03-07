package ht.haapala.wss.service;

import ht.haapala.wss.data.WorkShift;
import java.util.List;

/**
 * Interface for work shift service.
 * 
 * @author Heikki Haapala
 */
public interface WorkShiftService {

    WorkShift findOne(Long id);

    List<WorkShift> findAll();

    WorkShift save(WorkShift shift);

    void delete(Long id);
}
