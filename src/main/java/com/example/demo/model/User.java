package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String telephone;
    private Integer age;

    public User() {}

    public User(String userId, String name, String email, String telephone, Integer age) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }




}

