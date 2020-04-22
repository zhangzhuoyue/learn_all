package 测试用例.b6用等待通知机制优化循环等待;

/**
 * 账户类
 */
public class Account {

    //余额
    private int balance = 0;

    public Account(int balance){
        this.balance = balance;
    }
    //转账操作
    public void transfer(Account target , int amt){
        //申请资源，如果资源没有申请到，线程会阻塞在这里
        Allocator.getInstance().apply(this,target);
        if (this.balance > amt){
            this.balance -= amt;
            target.setBalance(target.getBalance()+amt);
            //释放资源
            Allocator.getInstance().free(this,target);
            System.out.println("44444");
        }else {
            //如果余额不满足条件，就释放资源
            Allocator.getInstance().free(this,target);
            System.out.println("555555");
        }
    }

    //修改余额
    public void setBalance(int balance){
        this.balance = balance;
    }

    //获取余额
    public int getBalance(){
        return this.balance;
    }
}
