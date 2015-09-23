package com.jykj.SpringRestCrud.dao.daoImpl;

import com.jykj.SpringRestCrud.dao.EmployeeDao;
import com.jykj.SpringRestCrud.model.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by albert on 2015/9/18.
 */
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee, Long> implements EmployeeDao {
}
