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
 * @Description employee 资源controller
 *
 */

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeServices employeeServices;

    static final Logger logger = Logger.getLogger(EmployeeController.class);

    /**
     * @Description 创建一个Employee
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
     * @Description 获取一个Employee信息
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

            // 测试manager 和 employee的关系
            Manager manager = employeeServices.getManager(employee.getManagerId());
            results.add(manager);
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }
        return new Results(200, "Success", results);
    }

    /**
     * @Description 更新一个Manager信息
     * @Url http://localhost:8080/SpringRestCrud/managers/:id
     * @Method PUT
     * @JSONBody {"name": xxx, "phone": xxx} 如果只需要更新某个特定的属性，只需要把该属性通过这个格式发送即可
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
     * @Description 根据pageNo 和 pageSize 获取所有Manager信息
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
     * @Description 根据某个Id删除employee
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
