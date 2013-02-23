package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.repository.PlannedShiftRepository;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedShiftServiceImpl implements PlannedShiftService {

    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    @Override
    public List<PlannedShift> findByDate(Date date) {
        return plannedShiftRepository.findByShiftDate(date);
    }

    @Override
    public List<PlannedShift> findByWeek(int weekNumber) {
        LocalDate thisMonday = LocalDate.now().withWeekOfWeekyear(weekNumber).withDayOfWeek(DateTimeConstants.MONDAY);
        LocalDate thisSunday = thisMonday.withDayOfWeek(DateTimeConstants.SUNDAY);
        return plannedShiftRepository.findByShiftDateBetween(thisMonday.toDate(), thisSunday.toDate());
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
    public void delete(Long plannedShiftId) {
        plannedShiftRepository.delete(plannedShiftId);
    }
}
