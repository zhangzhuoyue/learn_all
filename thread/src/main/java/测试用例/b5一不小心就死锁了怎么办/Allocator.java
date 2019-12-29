package 测试用例.b5一不小心就死锁了怎么办;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类作用的是一次申请资源
 */
public class Allocator {

    private static  List<Object> als  = new ArrayList<>();
    private static volatile Allocator  allocator= null;

    private Allocator(){}
    //一次申请资源
    synchronized boolean apply(Object from, Object to) {
        //这里使用if判断，只判断了一次，但是在使用该类申请资源的时候，使用的while死循环判断，不断检测资源的占用情况
        if (als.contains(from) || als.contains(to)){
            return false;
        }else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    //释放资源
    synchronized void free(Object from ,Object to){
        als.remove(from);
        als.remove(to);
    }

    //单利模式 双重检索
    public static Allocator getInstance(){
        if (allocator == null){
            synchronized (Allocator.class){
                if (allocator == null){
                    allocator = new Allocator();
                }
            }
        }
        return allocator;
    }
}
