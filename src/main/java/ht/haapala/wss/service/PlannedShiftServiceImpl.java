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
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlannedShiftServiceImpl implements PlannedShiftService {

    @Autowired
    PlannedShiftRepository plannedShiftRepository;

    @Override
    @Transactional(readOnly=true)
    public List<PlannedShift> findByDate(Date date) {
        List<PlannedShift> findByShiftDate = plannedShiftRepository.findByShiftDate(date);
        if (findByShiftDate != null) {
            for (PlannedShift shift : findByShiftDate) {
                shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
            }
        }
        return findByShiftDate;
    }

    @Override
    @Transactional(readOnly=true)
    public List<PlannedShift> findByWeek(int year, int weekNumber) {
        LocalDate thisMonday = LocalDate.now().withYear(year).withWeekOfWeekyear(weekNumber).withDayOfWeek(DateTimeConstants.MONDAY);
        LocalDate thisSunday = thisMonday.withDayOfWeek(DateTimeConstants.SUNDAY);
        List<PlannedShift> findByShiftDateBetween = plannedShiftRepository.findByShiftDateBetween(thisMonday.toDate(), thisSunday.toDate());
        if (findByShiftDateBetween != null) {
            for (PlannedShift shift : findByShiftDateBetween) {
                shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
            }
        }
        return findByShiftDateBetween;
    }

    @Override
    @Transactional(readOnly=true)
    public List<PlannedShift> findAll() {
        List<PlannedShift> findAll = plannedShiftRepository.findAll();
        if (findAll != null) {
            for (PlannedShift shift : findAll) {
                shift.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
            }
        }
        return findAll;
    }

    @Override
    @Transactional(readOnly=false)
    public PlannedShift save(PlannedShift shift) {
        PlannedShift save = plannedShiftRepository.save(shift);
        if (save != null) {
            save.setBareEmployee(new BareUser(shift.getEmployee().getUsername()));
        }
        return save;
    }

    @Override
    @Transactional(readOnly=false)
    public void delete(Long plannedShiftId) {
        plannedShiftRepository.delete(plannedShiftId);
    }

    @Override
    @Transactional(readOnly=true)
    public PlannedShift findByDateAndShift(Date date, WorkShift shift) {
        PlannedShift findByShiftDateAndShift = plannedShiftRepository.findByShiftDateAndShift(date, shift);
        if (findByShiftDateAndShift != null) {
            findByShiftDateAndShift.setBareEmployee(new BareUser(findByShiftDateAndShift.getEmployee().getUsername()));
        }
        return findByShiftDateAndShift;
    }
}
