package t1线程信息获取和设置_AND_io流.thread;

/**
 * @author zhangyue
 * @create 2019/11/28
 */
public class MyUncaughtExceptionHandlerTest {

    public static void main(String[] args) {
        Thread.currentThread().setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        getA();
    }

    public static void getA(){
        getB();
    }

    public static void getB(){
        getC();
    }

    public static void getC(){
        int i = 0;
        int k = 1;
        int j = k/i;
    }
}
