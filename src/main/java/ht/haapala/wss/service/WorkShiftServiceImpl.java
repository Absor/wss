/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WorkShift;
import ht.haapala.wss.repository.PlannedShiftRepository;
import ht.haapala.wss.repository.WorkShiftRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkShiftServiceImpl implements WorkShiftService {

    @Autowired
    WorkShiftRepository shiftRepository;
    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    @Override
    @Transactional(readOnly = true)
    public WorkShift findOne(Long id) {
        return shiftRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkShift> findAll() {
        return shiftRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public WorkShift save(WorkShift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        WorkShift shift = shiftRepository.findOne(id);
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
