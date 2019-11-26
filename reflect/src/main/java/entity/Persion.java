package entity;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyue
 * @create 2019/11/26
 */
public class Persion {
    private String name;

    private Date date;

    private int age;

    private Man man;

    private List<Man> mans;

    public List<Man> getMans() {
        return mans;
    }

    public void setMans(List<Man> mans) {
        this.mans = mans;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", age=" + age +
                ", mans=" + mans +
                '}';
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
