package t1线程信息获取和设置.core;

/**
 * @author zhangyue
 * @create 2019/11/27
 */
public class Calculator implements Runnable{

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    public void run() {
        for (int i = 1; i <= 10 ;i++){
            System.out.printf("当前线程: %s   %d * %d = %d \n",Thread.currentThread().getName() ,i,number,i * number);
        }
    }
}
