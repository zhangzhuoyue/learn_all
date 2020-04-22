package 测试用例.b4互斥锁如何用一把锁保护多个资源.没有关联关系使用细粒度锁;

public class Account {

    //锁 保护账户余额
    private final Object balLock = new Object();
    //余额
    private Integer balance ;
    //锁 保护密码
    private final Object pwLock = new Object();
    //密码
    private String passWord;
    private Account(){}
    public Account(Integer balance ,String passWord){
        this.balance = balance;
        this.passWord = passWord;
    }

    //取款
    public void withdraw(Integer amt){
        synchronized (balLock){
            if (this.balance > amt){
                this.balance -= amt;
            }
        }
    }

    //查看余额
    public Integer getBalance(){
        synchronized (balLock){
            return this.balance;
        }
    }

    //更改密码
    public void updatePassWord(String pwd){
        synchronized (pwLock){
            this.passWord = pwd;
        }
    }

    //查看密码
    public String getPassWord(){
        synchronized (pwLock){
            return this.passWord;
        }
    }

}
