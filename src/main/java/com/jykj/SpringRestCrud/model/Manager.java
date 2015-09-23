package com.jykj.SpringRestCrud.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by albert on 2015/9/17.
 */

@Entity
@Table(name = "manager")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Manager implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

//    @OneToMany
//    private List<Employee> employees;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void update(Map<String, Object> attributes) {
        for (String key : attributes.keySet()) {
            switch (key) {
                case "name":
                    this.name = (String)attributes.get(key);
                    break;
                case "phone":
                    this.phone = (String)attributes.get(key);
            }
        }
    }
}
