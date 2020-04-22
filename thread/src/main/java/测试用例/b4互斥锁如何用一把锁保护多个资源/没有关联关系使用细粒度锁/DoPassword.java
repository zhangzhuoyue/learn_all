package 测试用例.b4互斥锁如何用一把锁保护多个资源.没有关联关系使用细粒度锁;

import java.util.concurrent.CountDownLatch;

public class DoPassword implements Runnable {
    private CountDownLatch latch;
    private Account account;
    public DoPassword(CountDownLatch latch ,Account account){
        this.latch = latch;
        this.account = account;
    }

    @Override
    public void run() {
        try {
            latch.await();
            //account.withdraw();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
