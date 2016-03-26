package com.aeg.ims.transfer.repository;

import com.aeg.model.TimeEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by bszucs on 3/26/2016.
 */
public interface TimeEntryRepository extends CrudRepository<TimeEntry, Integer> {
    public List<TimeEntry> findCustomerTimeBilled(int id, Date starat, Date end);
}
