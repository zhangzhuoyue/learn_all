package 测试用例.b3互斥锁上解决原子性问题.互斥锁模拟多用户请求;

import java.util.concurrent.CountDownLatch;

public class ThreadData implements Runnable {

    private ShareDate shareDate;
    private int i;
    private CountDownLatch countDownLatch;
    public ThreadData(ShareDate data,int i,CountDownLatch countDownLatch){
        this.shareDate = data;
        this.i = i;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //shareDate.setA(i);
        //shareDate.get();
        int i = shareDate.setAndGetA(this.i);
        //System.out.println(Thread.currentThread().getName()+"  "+shareDate.get());
        System.out.println(Thread.currentThread().getName()+"  "+i);
    }
}
