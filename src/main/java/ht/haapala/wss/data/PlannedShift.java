package ht.haapala.wss.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Heikki Haapala
 */
@Entity
@Table(name = "PlannedShift")
public class PlannedShift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PlannedShiftId")
    private Long id;
    @Column(name = "ShiftDate", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @NotNull
    private Date shiftDate;
    @OneToOne
    @JoinColumn(name = "EmployeeID")
    @JsonIgnore
    private WSSUser employee;
    @OneToOne
    @JoinColumn(name = "ShiftID", nullable = false)
    @NotNull
    private WorkShift shift;
    @Transient
    private BareUser bareEmployee;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BareUser getBareEmployee() {
        return bareEmployee;
    }

    public void setBareEmployee(BareUser bareEmployee) {
        this.bareEmployee = bareEmployee;
    }
}
