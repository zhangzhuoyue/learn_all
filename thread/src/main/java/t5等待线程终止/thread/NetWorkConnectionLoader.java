package t5等待线程终止.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class NetWorkConnectionLoader extends Thread {

    @Override
    public void run() {
        System.out.println("开始初始化连接");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException in){

        }
        System.out.println("连接已经建立");
    }
}
