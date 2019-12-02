package t4线程的休眠和恢复.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class FileClock implements Runnable {

    public void run() {
        for (int i = 1 ;i <=10 ;i++){
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(10);     //阻塞方法响应中断
            }catch (InterruptedException in){
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println("响应中断");
                return;
            }
        }
    }
}
