package 测试用例.b6用等待通知机制优化循环等待;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Account from  = new Account(1000);
        Account to  = new Account(1000);
        CountDownLatch latch = new CountDownLatch(999);
        for (int i = 0; i < 999 ;i++){
            new Thread(()->{
                //latch.await();
                latch.countDown();
                System.out.println("2222");
                from.transfer(to,123);
            }).start();
        }
        //在计数器没有清零之前，会阻塞线程
        latch.await();
        // latch.countDown();
        System.out.println("from "+ from.getBalance());
        System.out.println("to "+ to.getBalance());


    }
}
