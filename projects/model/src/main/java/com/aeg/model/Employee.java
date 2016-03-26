package com.aeg.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "EMPLOYEE")
@EqualsAndHashCode(callSuper = false)
public class Employee implements Serializable{

    private int employeeId;
    private String firstName;
    private String lastName;
    private double hourlyRate;


}
