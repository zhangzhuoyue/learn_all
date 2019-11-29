package t1线程信息获取和设置_AND_io流.thread;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @author zhangyue
 * @create 2019/11/27
 */
public class Main {

    @Test
    public void test() {
        System.out.printf("Min Priority : %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Normal Priority : %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Max Priority : %s\n", Thread.MAX_PRIORITY);

        //初始化线程
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                threads[i] = new Thread(new Calculator(i));
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i] = new Thread(new Calculator(i));
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("threadName : " + i);
        }

        //将线程状态写入到文件中
        PrintWriter pw = null;
        try {
            String path = Main.class.getClassLoader().getResource("").getPath();
            path = URLDecoder.decode(path, "utf-8");
            System.out.println(path);
            File logFile = new File(path + "data/log.txx");
            //判断文件是否存在
            if (!logFile.getParentFile().exists()) {
                logFile.getParentFile().mkdirs();
            }
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(logFile);
            pw = new PrintWriter(fileWriter);

            //将线程状态打印到日志中。
            for (int i = 0; i < 10; i++) {
                pw.printf("Main : status of thread :" + i + "  :  " + threads[i].getState()+"  || ");
                states[i] = threads[i].getState();
            }

            //启动线程
            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            //循环遍历所有开启的线程，如果状态发生变化，将状态打印到文件中。
            boolean finish = false;
            while (!finish) {
                //如果状态发生变化
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != states[i]) {
                        //将状态打印到文件中。
                        writeThreadState(pw, threads[i], states[i]);
                        //记录新的状态
                        states[i] = threads[i].getState();
                    }
                }
                //判断所有的线程是否结束
                finish = true;
                for (int i = 0; i < 10; i++) {
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }


    }


    //编写打印方法
    public void writeThreadState(PrintWriter pw, Thread thread, Thread.State OldState) {
        pw.printf("Main : Id %d\n", thread.getId());
        pw.printf("Main : thread name : %s\n", thread.getName());
        pw.printf("Main : Old state  %s\n", OldState.toString());
        pw.printf("Main : New state  %s\n", thread.getState().toString());
        pw.printf("Main : method state  %s\n", thread.currentThread().getStackTrace()[0].getMethodName());
        pw.printf("Main : New state  %s\n", thread.getState().toString());
        pw.printf("*****************************************************\n");

    }


    @Test
    public void apiTest(){
        Thread thread = new Thread();

        //线程资源加载
        thread.currentThread().getContextClassLoader(); //获取当前线程的资源加载器。在多线程的

        //线程状态
        thread.getState(); //获取线程状态


        //线程中断
        thread.interrupt();//中断方法

        //线程转储
        StackTraceElement[] st = thread.currentThread().getStackTrace();  //返回一个表示该线程堆栈转储的堆栈跟踪元素数组
        for (StackTraceElement ele : st){
            System.out.println(ele.getFileName());   //当前线程调用的类的类名
            System.out.println(ele.getMethodName()); //访问调用的类的方法名
            System.out.println(ele.getLineNumber());  //调用发生在位置
            System.out.println("*********************************");
        }


        //一般程序崩溃时会退出，同时在控制台输出崩溃时的堆栈信息
        //通过异常捕获，将造成系统异常的信息保存到文件中，或数据库中。
        //具体实现在MyUncaughtExceptionHandler.java 中


    }
}
