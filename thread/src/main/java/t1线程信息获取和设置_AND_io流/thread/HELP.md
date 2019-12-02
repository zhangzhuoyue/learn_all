* Thread 状态信息












* printWriter

        可以针对文件直接进行操作
        如果一个类中的构造方法里面有File对象或者String类型数据,这个类可以对文本文件直接操作
                         FileInputStream
                          FileOutputStream
                          FileWriter
                         FileReader..
                          PrintWriter
         3)自动刷新功能::PrintWriter(OutputStream out/Writer out,boolean autoflush);第二个参数如果是true 表示启动自动刷新功能
         4)打印的方法:print(XXX x)/println(XXX  xx)
        ————————————————
        版权声明：本文为CSDN博主「scbiaosdo」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/scbiaosdo/article/details/80425086

* File
            
            String path = Main.class.getClassLoader().getResource("").getPath();
            path = URLDecoder.decode(path, "utf-8");
            System.out.println(path);
            File logFile = new File(path + "data/log.txx");
            //判断文件是否存在
            if (!logFile.getParentFile().exists()) {  //getParentFile()抽象路径名中文件的父目录是否存在
                logFile.getParentFile().mkdirs();     //如果抽象路径名的父目录不存在就创建
            }
            if (!logFile.exists()) {               //判断抽象路径中的文件是否存在
                logFile.createNewFile();            //抽象路径中的文件不存在就创建
            }