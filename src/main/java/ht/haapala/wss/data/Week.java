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
    
    private int year;
    private int weekNumber;
    private List<Date> days;

    public Week() {
        this.days = new ArrayList<Date>();
        LocalDate now = LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY);
        this.weekNumber = now.getWeekOfWeekyear();
        this.year = now.getYear();
        for (int i = 0; i < 5; i++) {
            this.days.add(now.toDate());
            now = now.plusDays(1);
        }
    }

    public Week(int year, int weekNumber) {
        this.days = new ArrayList<Date>();
        LocalDate now = LocalDate.now().withYear(year).withWeekOfWeekyear(weekNumber).withDayOfWeek(DateTimeConstants.MONDAY);
        this.weekNumber = now.getWeekOfWeekyear();
        this.year = now.getYear();
        for (int i = 0; i < 5; i++) {
            this.days.add(now.toDate());
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
