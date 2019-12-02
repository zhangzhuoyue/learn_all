package t3线程中断的控制.thread;

import sun.text.resources.cldr.lg.FormatData_lg;

import java.io.File;

/**
 * @author zhangyue
 * @create 2019/12/2
 */
public class FileSearch extends Thread{

    private String filePath;
    private String fileName;

    public FileSearch(String filePath, String fileName) {

        this.filePath = filePath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("2222222");
        try {
            searchFile(new File(filePath));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //资源释放
    public void releaseResource(){

    }

    //搜索目录下的文件
    //找到指定的文件后，结束循环。但是注意，无法结束递归中的for训话。
    //所以使用中断直接结束 任务。
    public void searchFile(File file) throws InterruptedException {
        System.out.println("****************");
        File[] files = file.listFiles();   //获取指定路径下所有的文件
        System.out.println(files == null);
        if (files != null){
            for (File file1 : files){
                if (file1.isDirectory()){   //判断file是否是目录，如果是目录，则递归判断。
                    System.out.println(file1.getName());
                    searchFile(file1);
                }else if (file1.isFile()){ //如果是文件，则进入处理文件的方法。
                    boolean flag = processFile(file1);
                    if (flag == true) {
                        break;
                    }


                }

            }
        }
    }

    //处理文件
    //当知道到文件后，设置中断状态，然后再合适的位置响应中断。
    public boolean processFile(File file) throws InterruptedException {
        if (file.getName().equals(fileName)){
            System.out.printf("%s   :  %s \n",Thread.currentThread().getName(),file.getAbsolutePath());
            interrupt();                                                                                    //匹配到结果，将当前线程设置为中断
            return true;
        }
        System.out.println("processFile------------"+file.getName());
        if (Thread.currentThread().isInterrupted()){
            throw new InterruptedException();
        }
        return false;
    }
}
