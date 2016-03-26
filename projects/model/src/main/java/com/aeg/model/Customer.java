package com.aeg.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "CUSTOMER" )
public class Customer implements Serializable{
    private int customerId;
    private String firstName;
    private String lastName;
    private double hourlyRate;
    private String email;

    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
