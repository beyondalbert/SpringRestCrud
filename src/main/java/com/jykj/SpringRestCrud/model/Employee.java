package com.jykj.SpringRestCrud.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by albert on 2015/9/16.
 */

@Entity
@Table(name = "employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private  long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "manager_id", nullable = false)
    private long managerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public void update(Map<String, Object> attributes) {
        for (String key : attributes.keySet()) {
            switch (key) {
                case "firstName":
                    this.firstName = (String)attributes.get(key);
                    break;
                case "lastName":
                    this.lastName = (String)attributes.get(key);
                    break;
                case "email":
                    this.email = (String)attributes.get(key);
                    break;
                case "phone":
                    this.phone = (String)attributes.get(key);
                    break;
                case "managerId":
                    this.managerId = (Long)attributes.get(key);
                    break;
            }
        }
    }
}
