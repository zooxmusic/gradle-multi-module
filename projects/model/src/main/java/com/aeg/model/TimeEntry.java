package com.aeg.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "TIME_ENTRY")
@EqualsAndHashCode(callSuper = false)
public class TimeEntry  extends LayerSupertype {
    private int customerId;
    private int employeeId;
    private Date entryDate;
    private double hours;
    private String description;
}
