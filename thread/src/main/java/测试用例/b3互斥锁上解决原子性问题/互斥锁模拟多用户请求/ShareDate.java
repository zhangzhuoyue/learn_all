package 测试用例.b3互斥锁上解决原子性问题.互斥锁模拟多用户请求;

public class ShareDate {
    private int a  ;

    public synchronized   void setA(int a){
        this.a = a;
    }

    public synchronized int  get(){
        return a;
    }

    public synchronized int setAndGetA(int a){
        this.a = a;
        return this.a;
    }
}
