package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WorkShift;
import ht.haapala.wss.repository.PlannedShiftRepository;
import ht.haapala.wss.repository.WorkShiftRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for work shifts.
 * 
 * @author Heikki Haapala
 */
@Service
public class WorkShiftServiceImpl implements WorkShiftService {

    @Autowired
    WorkShiftRepository shiftRepository;
    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    /**
     * Handles transactions for finding a work shift by id.
     * 
     * @param id given id
     * @return workshift of the id
     */
    @Override
    @Transactional(readOnly = true)
    public WorkShift findOne(Long id) {
        return shiftRepository.findOne(id);
    }

    /**
     * Handles transactions to find all work shifts.
     * 
     * @return a list of all work shifts
     */
    @Override
    @Transactional(readOnly = true)
    public List<WorkShift> findAll() {
        return shiftRepository.findAll();
    }

    /**
     * Handles transactions to save a new work shift by given data.
     *
     * @param shift shift to be saved
     * @return saved work shift
     */
    @Override
    @Transactional(readOnly = false)
    public WorkShift save(WorkShift shift) {
        return shiftRepository.save(shift);
    }

    /**
     * Handles transactions to delete work shift by its id.
     * 
     * @param id given id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        WorkShift shift = shiftRepository.findOne(id);
        // deletes also planned shifts that have the work shift
        if (shift != null) {
            List<PlannedShift> shifts = plannedShiftRepository.findByShift(shift);
            if (shifts != null) {
                for (PlannedShift plannedShift : shifts) {
                    plannedShiftRepository.delete(plannedShift.getId());
                }
            }
            shiftRepository.delete(id);
        }
    }
}
