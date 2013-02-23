/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 *
 * @author Heikki Haapala
 */
public class Week {
    
    private int weekNumber;
    private List<Date> days;

    public Week() {
        days = new ArrayList<Date>();
        LocalDate now = LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY);
        weekNumber = now.getWeekOfWeekyear();
        for (int i = 0; i < 5; i++) {
            days.add(now.toDate());
            now = now.plusDays(1);
        }
    }

    public Week(int weekNumber) {
        days = new ArrayList<Date>();
        LocalDate now = LocalDate.now().withWeekOfWeekyear(weekNumber).withDayOfWeek(DateTimeConstants.MONDAY);
        weekNumber = now.getWeekOfWeekyear();
        for (int i = 0; i < 5; i++) {
            days.add(now.toDate());
            now = now.plusDays(1);
        }
    }

    public List<Date> getDays() {
        return days;
    }

    public void setDays(List<Date> days) {
        this.days = days;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
}
