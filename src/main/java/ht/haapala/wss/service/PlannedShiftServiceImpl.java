package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.repository.PlannedShiftRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedShiftServiceImpl implements PlannedShiftService {

    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    @Override
    public List<PlannedShift> findByDate(Date date) {
        return plannedShiftRepository.findAll();
//        return plannedShiftRepository.findByShiftDate(date);
    }

    @Override
    public List<PlannedShift> findAll() {
        return plannedShiftRepository.findAll();
    }

    @Override
    public PlannedShift save(PlannedShift shift) {
        return plannedShiftRepository.save(shift);
    }

    @Override
    public void delete(Date date, Long shiftId) {
//        plannedShiftRepository.deleteByDateAndShiftID(date, shiftId);
    }
}
