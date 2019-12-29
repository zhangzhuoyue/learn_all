package 测试用例.b3互斥锁上解决原子性问题.互斥锁模拟多用户请求;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ShareDate shareDate = new ShareDate();
        final  CountDownLatch latch = new CountDownLatch(1);



        for (int i = 0 ;i < 50 ;i++){


            new Thread(new ThreadData(shareDate,i,latch)).start();
            System.out.println(i);

        }
        latch.countDown();
        System.out.println("latch.getCount() "+latch.getCount());
    }
}
