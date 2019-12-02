package t2线程的中断.thread;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class Main {

    public static void main(String[] args) {
        Thread thread = new Core(45);
        thread.start();
        thread.interrupt(); //设置线程的中断状态。
    }
}
