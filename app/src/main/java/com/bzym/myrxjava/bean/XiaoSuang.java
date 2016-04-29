package com.bzym.myrxjava.bean;

/**
 * Created by Administrator on 2016-04-01.
 */
public class XiaoSuang {
    private String name;
    private int age;
    private String address;

    public XiaoSuang() {
    }

    public XiaoSuang(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "XiaoSuang{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
