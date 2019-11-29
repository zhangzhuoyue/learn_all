package com.zzy.xstream;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyue
 * @create 2019/11/19
 */
public class PersionList {

    private String name;
    private List<Persion> persions;
    private Date date;

    @Override
    public String toString() {
        return "PersionList{" +
                "name='" + name + '\'' +
                ", persions=" + persions +
                ", date=" + date +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PersionList(String name) {
        this.name = name;
    }

    public PersionList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Persion> getPersions() {
        return persions;
    }

    public void setPersions(List<Persion> persions) {
        this.persions = persions;
    }

}
