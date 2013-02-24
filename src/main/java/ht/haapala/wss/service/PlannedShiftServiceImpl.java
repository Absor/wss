package ht.haapala.wss.service;

import ht.haapala.wss.data.BareUser;
import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WorkShift;
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
        List<PlannedShift> findByShiftDate = plannedShiftRepository.findByShiftDate(date);
        for (PlannedShift shift : findByShiftDate) {
            shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
        }
        return findByShiftDate;
    }

    @Override
    public List<PlannedShift> findByWeek(int year, int weekNumber) {
        LocalDate thisMonday = LocalDate.now().withYear(year).withWeekOfWeekyear(weekNumber).withDayOfWeek(DateTimeConstants.MONDAY);
        LocalDate thisSunday = thisMonday.withDayOfWeek(DateTimeConstants.SUNDAY);
        List<PlannedShift> findByShiftDateBetween = plannedShiftRepository.findByShiftDateBetween(thisMonday.toDate(), thisSunday.toDate());
        for (PlannedShift shift : findByShiftDateBetween) {
            shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
        }
        return findByShiftDateBetween;
    }

    @Override
    public List<PlannedShift> findAll() {
        List<PlannedShift> findAll = plannedShiftRepository.findAll();
        for (PlannedShift shift : findAll) {
            shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
        }
        return findAll;
    }

    @Override
    public PlannedShift save(PlannedShift shift) {
        PlannedShift save = plannedShiftRepository.save(shift);
        save.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
        return save;
    }

    @Override
    public void delete(Long plannedShiftId) {
        plannedShiftRepository.delete(plannedShiftId);
    }

    @Override
    public PlannedShift findByDateAndShift(Date date, WorkShift shift) {
        return plannedShiftRepository.findByShiftDateAndShift(date, shift);
    }
}
