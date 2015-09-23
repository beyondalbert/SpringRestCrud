package com.jykj.SpringRestCrud.services;

import com.jykj.SpringRestCrud.model.Employee;
import com.jykj.SpringRestCrud.model.Manager;

import java.util.List;

/**
 * Created by albert on 2015/9/22.
 */
public interface ManagerServices {
    void save(Manager manager) throws Exception;
    void update(Manager manager) throws Exception;
    Manager findById(Long id) throws Exception;
    List<Manager> findAll(Integer pageNo, Integer pageSize) throws Exception;
    void destroy(Long id) throws Exception;
    List<Employee> getEmployees(Long managerId) throws Exception;
}
