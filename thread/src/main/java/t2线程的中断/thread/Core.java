package t2线程的中断.thread;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class Core  extends Thread{

    private long number = 1L;

    public Core(int number){
        this.number = number;
    }

    @Override
    public void run() {

        while (true){
            if (isPrime(number)){
                System.out.println("当前数字是质数");
            }

            //boolean u = Thread.currentThread().isInterrupted();
            boolean u = isInterrupted();
            if (u){
                System.out.println(u);
                System.out.println("响应中断");
                //Thread.currentThread().interrupt();
                interrupted();                      //清楚当前线程的中断状态
                //System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(isInterrupted());//判断线程是否是中断状态
                return;                             //return结束方法的标识，在无返回值的方法中，也默认调用return结束方法
            }
            number++;
        }

    }

    //判断质数的方法
    public boolean isPrime(long number){
        if (number <=2 ){
            return true;
        }

        for (int i = 2 ;i <= number;i++){
            if (i % 2 == 0){
                return false;
            }

        }
        return true;
    }
}
