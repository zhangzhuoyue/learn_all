package 测试用例.b6用等待通知机制优化循环等待;

import sun.security.provider.Sun;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * 资源控制类，需要确保他是单例模式
 */
public class Allocator {
    //资源存放池
    private List<Account> als = new ArrayList<>();
    //单例
    private static volatile Allocator allocator= null;
    private Allocator(){}
    //资源申请
    public synchronized void apply(Account from ,Account to) {
        while (als.contains(from) || als.contains(to)){
            try {

                this.wait();
                System.out.println("wait2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        als.add(from);
        als.add(to);
    }

    //释放资源
    public synchronized void free(Account from ,Account to){
        als.remove(from);
        als.remove(to);
        this.notifyAll();
        System.out.println("999999");
    }

    //单例模式
    public static Allocator getInstance(){
        return  AllocatorSingleton.allocator;
    }

    static class AllocatorSingleton{
        private static Allocator allocator = new Allocator();
    }
}
