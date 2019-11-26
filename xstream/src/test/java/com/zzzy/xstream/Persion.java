package com.zzzy.xstream;

import org.springframework.stereotype.Component;

/**
 * @author zhangyue
 * @create 2019/11/19
 */
@Component
public class Persion {
    private String name;

    private String address;

    private int age;

    private Phone phone;


    public Persion(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public Persion() {
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                '}';
    }
}
