* 条件队列
        
        条件队列：它使得一组线程(称之为等待线程集合)，能够通过某种方式来等待特定的条件为真。传统队列是一个个数据，与之不同的是，条件队列中的元素是一个个正在等待相关条件的线程。
        在java中，每个对象可以作为一个锁，同样可以作为一个条件队列，Object 中的wait,notify,notifyAll 构成内部队列的api。
        wait() , 在状态前提条件不满足的情况下调用使得当前线程堵塞进入等待队列。
        notify()/notifyAll()，在改变状态后，可能会满足某个条件，唤醒等待线程进行处理。
    
* Thread.join()
            
            public final synchronized void join(long millis)
                      throws InterruptedException {
                          long base = System.currentTimeMillis();
                         long now = 0;
                 
                          if (millis < 0) {
                             throw new IllegalArgumentException("timeout value is negative");
                          }
                 thread.join() 方法底层使用条件队列。底层使用条件队列，wait  notify  notifyAll synchronized
                 在主线程中调用thread1.join() , 进入到这个方法中调用await()方法，主线程挂起，执行子线程的任务，当子线程的任务执行完毕后，调用notifyAll() 方法，
                 唤醒主线程。
                 
              if (millis == 0) {
                  while (isAlive()) {
                      wait(0);
                  }
              } else {
                  while (isAlive()) {
                      long delay = millis - now;
                      if (delay <= 0) {
                          break;
                      }
                      wait(delay);
                      now = System.currentTimeMillis() - base;
                  }
              }
          }
     
    