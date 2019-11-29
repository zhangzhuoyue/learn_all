package t1线程信息获取和设置_AND_io流.thread;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangyue
 * @create 2019/11/28
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {


    public void uncaughtException(Thread t, Throwable e) {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            path = URLDecoder.decode(path,"utf-8");
            System.out.println(path);

            FileWriter fw  = new FileWriter(path+"datalog/log.txt",true);
            fw.append(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date())+"\n");
            fw.append(t.toString() +"\n");

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            fw.append(sw.toString());
            fw.flush();
            fw.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
