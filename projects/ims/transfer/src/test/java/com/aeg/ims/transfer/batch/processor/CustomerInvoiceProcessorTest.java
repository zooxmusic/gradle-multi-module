package com.aeg.ims.transfer.batch.processor;


import com.aeg.ims.transfer.repository.EmployeeRepository;
import com.aeg.ims.transfer.repository.TimeEntryRepository;
import com.aeg.model.Customer;
import com.aeg.model.Employee;
import com.aeg.model.Invoice;
import com.aeg.model.TimeEntry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;

import org.joda.time.LocalDate;
import org.springframework.util.Assert;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

public class CustomerInvoiceProcessorTest {

    private Customer customer;

    @Mock
    private TimeEntryRepository timeEntryRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    protected StepExecution stepExecution;

    @Mock
    protected JobParameters jobParams;

    @InjectMocks
    private CustomerInvoiceProcessor customerInvoiceProcessor = new CustomerInvoiceProcessor();


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        customer = createMockCustomer();

        LocalDate fromDate = LocalDate.parse("2012-01-01");
        LocalDate toDate = LocalDate.parse("2012-01-31");

        // stub in dao calls

        when(this.stepExecution.getJobParameters()).thenReturn(this.jobParams);
        when(this.jobParams.getDate("fromDate")).thenReturn(fromDate.toDate());
        when(this.jobParams.getDate("toDate")).thenReturn(toDate.toDate());

        when(this.timeEntryRepository.findCustomerTimeBilled(
                1, fromDate.toDate(), toDate.toDate()))
                .thenReturn(createMockTimeEntries());

        when(this.employeeRepository.findOne(1))
                .thenReturn(createMockEmployee(1,"John","Doe"));
        when(this.employeeRepository.findOne(2))
                .thenReturn(createMockEmployee(2,"Jane","Doe"));
    }
    private Customer createMockCustomer() {
        Customer c = new Customer();
        c.setCustomerId(1);
        c.setEmail("jdoe@acmeco.com");
        c.setFirstName("Acme Product Co.");
        return c;
    }
    private List createMockTimeEntries() {
        List entries = new ArrayList();
        entries.add(createMockTimeEntry(
                1,1,LocalDate.parse("2012-01-03").toDate(),8));
        entries.add(createMockTimeEntry(
                1,2,LocalDate.parse("2012-01-05").toDate(),9.5));
        return entries;
    }
    private TimeEntry createMockTimeEntry(int customerId,
                                          int employeeId, Date entryDate, double hours) {
        TimeEntry t = new TimeEntry();
        t.setCustomerId(customerId);
        t.setEmployeeId(employeeId);
        t.setEntryDate(entryDate);
        t.setHours(hours);
        t.setDescription("Invoice batch processing job and unit tests.");
        return t;
    }
    private Employee createMockEmployee(int i, String firstName,
                                        String lastName) {
        Employee e = new Employee();
        e.setEmployeeId(i);
        e.setFirstName(firstName);
        e.setLastName(lastName);
        e.setHourlyRate(10.00);
        return e;
    }
    @Test
    public void testProcess() throws Exception {
        Invoice invoice = customerInvoiceProcessor.process(customer);
        Assert.notNull(invoice);
        Assert.notEmpty(invoice.getInvoiceItems());
        // any necessary assertions to test processor logic
    }

}