public class Thread{
    //中断目标线程（但线程不会立刻停止，也就是不会抢占式停止）
    public void interrupt(){}
    //返回线程的中断状态 已中断：true 未中断：false
    public boolean isInterrupted(){}
    //清除当前线程的中断状态，并返回它之前的值，这是清除中断状态的唯一方法
    public static boolean interrupted(){}
}