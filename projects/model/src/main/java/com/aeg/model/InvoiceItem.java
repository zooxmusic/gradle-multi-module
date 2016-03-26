package com.aeg.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "INVOICED_ITEM")
public class InvoiceItem implements Serializable {

    private String employeeFirstName;
    private String employeeLastName;
    private double rate;
    private double hours;
    private String description;
    private int lineItem;

}
