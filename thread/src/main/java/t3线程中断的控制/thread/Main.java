package t3线程中断的控制.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class Main {

    /**
     *主要的思想：
     * 1.定义任务超时时间，在主线程等待10s后，任务没有完成，则结束任务
     * 2.在子线程中，找到指定的文件后，直接结束任务。
     * 上面两个功能的的实现，使用中断完成功能。
     * @param args
     */
    public static void main(String[] args) {

        FileSearch fileSearch = new FileSearch("E:\\ZKR\\tools1\\factory-repository","antlr-2.7.2.pom");
        fileSearch.start();

        try {
            TimeUnit.SECONDS.sleep(1011);  //主线程休眠10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1111111");
        //休眠结束后结束线程
        fileSearch.interrupt();
        System.out.println("2222222");
    }
}
