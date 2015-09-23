package com.jykj.SpringRestCrud.services;

import com.jykj.SpringRestCrud.model.Employee;
import com.jykj.SpringRestCrud.model.Manager;

import java.util.List;

/**
 * Created by albert on 2015/9/17.
 */
public interface EmployeeServices {
    void save(Employee employee) throws Exception;
    Employee findById(Long id) throws Exception;
    void update(Employee employee) throws Exception;
    List<Employee> findAll() throws Exception;
    List<Employee> findAll(Integer pageNo, Integer pageSize);
    void destroy(Long id) throws Exception;
    Manager getManager(Long managerId) throws Exception;
}
