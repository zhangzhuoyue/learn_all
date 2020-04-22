package 测试用例.b3互斥锁上解决原子性问题.volatile无法保证共享变量原子性;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        VolatileTest v = new VolatileTest(latch);
        v.test();
        //TimeUnit.SECONDS.sleep(2);
        //latch.countDown();

    }
}
