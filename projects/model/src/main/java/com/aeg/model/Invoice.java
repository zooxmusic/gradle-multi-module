package com.aeg.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "INVOICE")
@EqualsAndHashCode(callSuper = false)
public class Invoice extends LayerSupertype{

    private int customerId;
    private String customerName;
    private String customerEmail;
    private Set<InvoiceItem> invoiceItems;
}
