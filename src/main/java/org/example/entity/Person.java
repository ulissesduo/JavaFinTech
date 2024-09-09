package org.example.entity;

import java.util.Date;

public class Person {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private Date birthdate;

    public Person(Long id, String name, String lastName, Integer age, Date birthdate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
