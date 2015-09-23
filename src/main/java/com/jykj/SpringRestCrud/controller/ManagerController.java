package com.jykj.SpringRestCrud.controller;

import com.jykj.SpringRestCrud.model.Employee;
import com.jykj.SpringRestCrud.model.Manager;
import com.jykj.SpringRestCrud.model.Results;
import com.jykj.SpringRestCrud.services.ManagerServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by albert on 2015/9/22.
 */

@Controller
@RequestMapping("/managers")
public class ManagerController {
    @Autowired
    ManagerServices managerServices;

    static final Logger logger = Logger.getLogger(EmployeeController.class);

    /**
     * @Description 创建一个Manager
     * @Url http://localhost:8080/SpringRestCrud/managers
     * @Method POST
     * @JSONBody {"name": "name", "phone": "1234567890"}
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Results create(@RequestBody Manager manager) {
        try {
            managerServices.save(manager);
            List<Object> results = new ArrayList<>();
            results.add(manager);
            return new Results(200, "Manager added successfully!", results);
        } catch (Exception e) {
            return new Results(500, e.toString());
        }
    }

    /**
     * @Description 获取一个Manager信息
     * @Url http://localhost:8080/SpringRestCrud/managers/:id
     * @Method GET
     * @JSONBody null
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Results show(@PathVariable("id") long id) {
        List<Object> results = new ArrayList<>();

        try {
            Manager manager = managerServices.findById(id);
            results.add(manager);

            // test employees 和 manager的关联
//            List<Employee> employees = managerServices.getEmployees(manager.getId());
//            results.add(employees);
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
            final Manager managerOld = managerServices.findById(id);
            managerOld.update(manager);
            managerServices.update(managerOld);
            List<Object> results = new ArrayList<Object>() {{add(managerOld);}};
            return new Results(200, "Success", results);
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
    Results index(@RequestParam("pageNo") Integer pageNo, @RequestParam Integer pageSize) {
        List<Object> managers = new ArrayList<>();

        try {
            for (Manager m : managerServices.findAll(pageNo, pageSize)) {
                managers.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }

        return new Results(200, "Success", managers);
    }

    /**
     * @Description 根据某个Id删除manager
     * @Url http://localhost:8080/SpringRestCrud/managers/:id
     * @Method DELETE
     * @JSONBody null
     * @return {"code": xxx, "msg": xxx, "results": []}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Results destroy(@PathVariable("id") long id) {
        try {
            managerServices.destroy(id);
            return new Results(200, "Delete Manager Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Results(500, e.toString());
        }
    }
}
