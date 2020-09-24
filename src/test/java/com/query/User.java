package com.query;

import java.util.Date;

public class User {
    private String name;
    private int age;
    private String location;
    private Date createDate;

    public User(String name, int age) {
        this(name, age, null);
    }

    public User(String name, int age, String location) {
        this(name, age, location, new Date());
    }

    public User(String name, int age, String location, Date createDate) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
