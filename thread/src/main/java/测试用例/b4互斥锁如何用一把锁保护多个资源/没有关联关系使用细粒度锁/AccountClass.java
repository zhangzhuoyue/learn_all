package 测试用例.b4互斥锁如何用一把锁保护多个资源.没有关联关系使用细粒度锁;

public class AccountClass {
    private Integer balance;
    private String password;
    public AccountClass(){}
    public AccountClass(Integer balance ,String password){
        this.balance = balance;
        this.password = password;
    }
    void transfer(AccountClass target ,int amt){
        synchronized (AccountClass.class){
            if (this.balance > amt){
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
