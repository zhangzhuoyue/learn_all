package com.zzzy.xstream;

/**
 * @author zhangyue
 * @create 2019/11/19
 */

public class Phone {
    private String phoneNumber;
    private int address;

    public Phone(String phoneNumber, int address) {
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Phone() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
