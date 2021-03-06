package ht.haapala.wss.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Database relational class for work shifts.
 * 
 * @author Heikki Haapala
 */
@Entity
@Table(name = "WorkShift")
public class WorkShift implements Serializable {

    @Id
    @Column(name = "ShiftId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "ShiftName", nullable = false)
    @NotBlank
    @Length(min = 3)
    private String shiftName;
    @Temporal(javax.persistence.TemporalType.TIME)
    @Column(name = "StartTime", nullable = false)
    private Date startTime;
    @Temporal(javax.persistence.TemporalType.TIME)
    @Column(name = "EndTime", nullable = false)
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
