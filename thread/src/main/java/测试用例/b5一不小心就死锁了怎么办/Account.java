package 测试用例.b5一不小心就死锁了怎么办;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    //获取Allocator的单例模式
    public Allocator getInstance() {
        return Allocator.getInstance();
    }

    //转账   使用多个锁，因为一次申请了所有的资源，所以不存在死锁问题
    void transfer(Account target, int amt) {
        //转账，一次申请所有的资源
        //使用自旋的方式判断是否获取了锁，使用while不断检测资源。和等待-通知机制的while方式是一样，在await()在唤醒的时候需要再次进行检测，判断条件是否满足
        //原因是，在notify通知时间的状态和线程重新获取锁不在同一个时间点，满足线程的状态可能已经发生变化。所以需要在等待的线程获取到锁后，需要重新判断条件是否满足。
        while (!getInstance().apply(this, target)) ;
        try {
            //锁定转出账户
            synchronized (this) {
                //锁定转入账户
                synchronized (target) {
                    System.out.println(Thread.currentThread().getName());
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                        System.out.println(Thread.currentThread().getName() + "  from  : " + this.balance + " , to  : " + target.balance);
                    }
                }
            }
        } finally {
            getInstance().free(this, target);
        }
    }
}
