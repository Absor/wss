package ht.haapala.wss.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Heikki Haapala
 */
@Entity
@Table(name = "PlannedShift")
public class PlannedShift implements Serializable {

    @Id
    @Column(name = "PlannedShiftId")
    private Long id;
    @Column(name = "ShiftDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date shiftDate;
    @OneToOne
    @JoinColumn(name = "EmployeeID")
    private WSSUser employee;
    @OneToOne
    @JoinColumn(name = "ShiftID", nullable = false)
    @NotNull
    private WorkShift shift;

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public WSSUser getEmployee() {
        return employee;
    }

    public void setEmployee(WSSUser employee) {
        this.employee = employee;
    }

    public WorkShift getShift() {
        return shift;
    }

    public void setShift(WorkShift shift) {
        this.shift = shift;
    }
}
