package 测试用例.b3互斥锁上解决原子性问题.volatile无法保证共享变量原子性;

import java.util.concurrent.CountDownLatch;

public class VolatileTest {

    private volatile int a;
    private CountDownLatch latch;
    private VolatileTest(){}
    public VolatileTest(CountDownLatch latch){
        this.latch = latch;

    }
    public void add10k()   {
        try {
            //latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        int  add = 0;
        if (++add < 100){
            a++;
            System.out.println(Thread.currentThread().getName() +"   a= "+a);
        }
    }

    public void test()   {
        try {
            for (int i = 0 ;i < 100 ; i++ ){
                Thread thread1 = new Thread(()->{
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    add10k();
                });
                Thread thread2 = new Thread(()->{
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    add10k();
                });
                thread1.start();
                thread2.start();
                //thread1.join();//这个方法在使用了栅栏的地方使用，使用的位置不对，造成栅栏的countDown无法执行。
                //thread2.join();//这个方法是在主线程调用的，但是countDown在该方法之后调用，使得线程永远的阻塞。
            }
            latch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
