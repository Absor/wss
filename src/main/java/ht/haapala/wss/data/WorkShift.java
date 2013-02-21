package ht.haapala.wss.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Heikki Haapala
 */
@Entity
@Table(name = "WorkShift")
public class WorkShift implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ShiftName")
    private String shiftName;
    @Temporal(javax.persistence.TemporalType.TIME)
    @Column(name = "StartTime")
    private Date startTime;
    @Temporal(javax.persistence.TemporalType.TIME)
    @Column(name = "EndTime")
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
