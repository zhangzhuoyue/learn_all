package t4线程的休眠和恢复.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的休眠和唤醒
 * 通过线程的中断装填对睡眠的线程进行唤醒。
 * 在响应中断后，对中断异常的处理。通过捕捉中断异常来停止线程。
 *
 */
public class Main {

    public static void main(String[] args)   {
        FileClock clock =new FileClock();
        Thread thread = new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException i){
            i.printStackTrace();
        }

        thread.interrupt();
    }
}
