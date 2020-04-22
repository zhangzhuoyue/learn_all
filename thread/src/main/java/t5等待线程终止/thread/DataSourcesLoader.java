package t5等待线程终止.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class DataSourcesLoader extends Thread{

    @Override
    public void run() {
        System.out.println("开始加载数据源");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException in){

        }
        System.out.println("数据源已经加载");
    }
}
