package com.jykj.SpringRestCrud.services.servicesImpl;

import com.jykj.SpringRestCrud.dao.EmployeeDao;
import com.jykj.SpringRestCrud.dao.ManagerDao;
import com.jykj.SpringRestCrud.model.Employee;
import com.jykj.SpringRestCrud.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by albert on 2015/9/22.
 */
@Service
public class ManagerServicesImpl implements com.jykj.SpringRestCrud.services.ManagerServices {
    @Autowired
    ManagerDao managerDao;

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void save(Manager manager) throws Exception {
        managerDao.save(manager);
    }

    @Override
    public Manager findById(Long id) throws Exception {
        return managerDao.findById(id);
    }

    @Override
    public void update(Manager manager) throws Exception {
        managerDao.update(manager);
    }

    @Override
    public List<Manager> findAll(Integer pageNo, Integer pageSize) throws Exception {
        if (pageNo != null)
            return managerDao.findAll(pageNo, pageSize);
        else
            return managerDao.findAll();
    }

    @Override
    public void destroy(Long id) throws Exception {
        managerDao.delete(id);
    }

    @Override
    public List<Employee> getEmployees(final Long managerId) throws Exception {
        Map<String, Object> attributes = new HashMap<String, Object>() {
            {put("manageId", managerId);}
        };
        return employeeDao.findByStr("from Employee where manager_id = :manageId",attributes, -1, 0);
    }
}