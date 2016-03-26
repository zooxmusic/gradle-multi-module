package com.aeg.ims.transfer.batch.processor;

import com.aeg.ims.transfer.repository.EmployeeRepository;
import com.aeg.ims.transfer.repository.TimeEntryRepository;
import com.aeg.model.*;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CustomerInvoiceProcessor implements ItemProcessor<Customer, Invoice> {

    private StepExecution stepExecution;

    @Autowired
    private TimeEntryRepository timeEntryDao;

    @Autowired
    private EmployeeRepository employeeDao;

    @SuppressWarnings("unused")
    @BeforeStep
    private void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
    @Override
    public Invoice process(Customer customer) throws Exception {
        JobParameters jobParams = stepExecution.getJobParameters();
        Date fromDate = jobParams.getDate("fromDate");
        Date toDate = jobParams.getDate("toDate");

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setCustomerName(customer.getName());
        invoice.setCustomerEmail(customer.getEmail());

        List<TimeEntry> billedTimes =
                timeEntryDao.findCustomerTimeBilled(
                        customer.getCustomerId(), fromDate, toDate);
        int lineNum = 0;

        for (TimeEntry timeEntry : billedTimes) {
            InvoiceItem item = new InvoiceItem();
            Employee employee = employeeDao.findOne(timeEntry.getEmployeeId());
            item.setEmployeeFirstName(employee.getFirstName());
            item.setEmployeeLastName(employee.getLastName());
            item.setRate(employee.getHourlyRate());
            item.setHours(timeEntry.getHours());
            item.setDescription(timeEntry.getDescription());
            item.setLineItem(lineNum++);
            invoice.getInvoiceItems().add(item);
        }
        return invoice;
    }
}