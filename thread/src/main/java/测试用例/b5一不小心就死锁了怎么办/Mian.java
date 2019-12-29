package 测试用例.b5一不小心就死锁了怎么办;

import java.util.concurrent.CountDownLatch;

public class Mian {
    public static void main(String[] args) {
        Account a = new Account(1000);
        Account b = new Account(900);
        Account c = new Account(900);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0 ;i < 500 ;i++){
            new Thread(()->{
                try {
                    latch.await();
                    a.transfer(b,200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    latch.await();
                    b.transfer(a,200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    latch.await();
                    c.transfer(b,200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    latch.await();
                    c.transfer(a,200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("66666");
        }
        latch.countDown();

    }
}
