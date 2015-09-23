package com.jykj.SpringRestCrud.controller;

import com.jykj.SpringRestCrud.model.Employee;
import com.jykj.SpringRestCrud.model.Manager;
import com.jykj.SpringRestCrud.model.Results;
import com.jykj.SpringRestCrud.services.EmployeeServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by albert on 2015/9/16.
 * @Description employee ��Դcontroller
 *
 */

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeServices employeeServices;

    static final Logger logger = Logger.getLogger(EmployeeController.class);

    /**
     * @Description ����һ��Employee
     * @Url http://localhost:8080/SpringRestCrud/Employees
     * @Method POST
     * @JSONBody {"firstName": xxx, "lastName": xxx, "email": xxx, "phone": xxx, "managerId": xxx}
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Results create(@RequestBody Employee employee) {
        try {
            employeeServices.save(employee);
            List<Object> results = new ArrayList<>();
            results.add(employee);
            return new Results(200, "Employee added successfully!", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }
    }

    /**
     * @Description ��ȡһ��Employee��Ϣ
     * @Url http://localhost:8080/SpringRestCrud/Employees/:id
     * @Method GET
     * @JSONBody null
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Results show(@PathVariable("id") long id) {
        List<Object> results = new ArrayList<>();
        try {
            Employee employee = employeeServices.findById(id);
            results.add(employee);

            // ����manager �� employee�Ĺ�ϵ
            Manager manager = employeeServices.getManager(employee.getManagerId());
            results.add(manager);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }
        return new Results(200, "Success", results);
    }

    /**
     * @Description ����һ��Manager��Ϣ
     * @Url http://localhost:8080/SpringRestCrud/managers/:id
     * @Method PUT
     * @JSONBody {"name": xxx, "phone": xxx} ���ֻ��Ҫ����ĳ���ض������ԣ�ֻ��Ҫ�Ѹ�����ͨ�������ʽ���ͼ���
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Results update(@PathVariable("id") long id, @RequestBody Map manager) {
        try {
            final Employee employeeOld = employeeServices.findById(id);
            employeeOld.update(manager);
            employeeServices.update(employeeOld);
            List<Object> results = new ArrayList<Object>() {{add(employeeOld);}};
            return new Results(200, "Employee updated successfully!", results);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }
    }

    /**
     * @Description ����pageNo �� pageSize ��ȡ����Manager��Ϣ
     * @Url http://localhost:8080/SpringRestCrud/managers?pageNo=1&pageSize=100
     * @Method GET
     * @JSONBody null
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Results index(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        List<Object> results = new ArrayList<>();

        try {
            for (Object o : employeeServices.findAll(pageNo, pageSize)) {
                results.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }
        return new Results(200, "Success", results);
    }

    /**
     * @Description ����ĳ��Idɾ��employee
     * @Url http://localhost:8080/SpringRestCrud/employees/:id
     * @Method DELETE
     * @JSONBody null
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Results destroy(@PathVariable("id") long id) {
        try {
            employeeServices.destroy(id);
            return  new Results(200, "Employee deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return  new Results(500, e.toString());
        }
    }
}
