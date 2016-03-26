package com.aeg.ims.transfer.repository;

import com.aeg.model.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
