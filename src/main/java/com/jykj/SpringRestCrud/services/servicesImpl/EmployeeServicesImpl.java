package com.jykj.SpringRestCrud.services.servicesImpl;

import com.jykj.SpringRestCrud.dao.EmployeeDao;
import com.jykj.SpringRestCrud.dao.ManagerDao;
import com.jykj.SpringRestCrud.model.Employee;
import com.jykj.SpringRestCrud.model.Manager;
import com.jykj.SpringRestCrud.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by albert on 2015/9/17.
 */
@Service
public class EmployeeServicesImpl implements EmployeeServices {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ManagerDao managerDao;

    @Override
    public void save(Employee employee) throws Exception {
        employeeDao.save(employee);
    }

    @Override
    public Employee findById(Long id) throws Exception {
        return employeeDao.findById(id);
    }

    @Override
    public void update(Employee employee) throws Exception {
        employeeDao.update(employee);
    }

    @Override
    public List<Employee> findAll() throws Exception {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findAll(Integer pageNo, Integer pageSize) {
        if (pageNo != null) {
            return employeeDao.findAll(pageNo, pageSize);
        } else {
            return employeeDao.findAll();
        }
    }

    @Override
    public void destroy(Long id) throws Exception {
        employeeDao.delete(id);
    }

    @Override
    public Manager getManager(Long managerId) throws Exception {
        return managerDao.findById(managerId);
    }
}
