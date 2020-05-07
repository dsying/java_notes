package multithread.c_002;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock4 {

  public static void main(String[] args) throws InterruptedException {
    Lock lock = new ReentrantLock();

    Thread t1 = new Thread(() -> {
      System.out.println("thread-1 --->");
      lock.lock();
      try {
        System.out.println("thread-1 ---> 获得锁");
        TimeUnit.SECONDS.sleep(20); // 20秒内都不会释放锁
      } catch(InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("thread-1 <--- 释放锁");
        lock.unlock();
      }
    });
    t1.start();

    /**
     * t1 sleep（20s）,所以 t2 在 至少20秒的时间内都无法获得锁
     *
     * lockInterruptibly()方法的出现，使得我们可以控制 t2响应 Interruupt 而退出 等待同步队列
     */
    Thread t2 = new Thread(() -> {
      System.out.println("thread-2 --->");
      try {
        lock.lockInterruptibly(); // 可以对interrupt()作出反应
        System.out.println("thread-2 ---> 获得锁");
        lock.lockInterruptibly(); // 可以对interrupt()作出反应
        TimeUnit.SECONDS.sleep(5);
      } catch(InterruptedException e) {
        System.out.println("thread-2 catch");
        e.printStackTrace();
      } finally {
        System.out.println("thread-2 <--- 释放锁");
        lock.unlock();
      }
    });
    t2.start();

    TimeUnit.SECONDS.sleep(1);

    t2.interrupt(); // 打断线程t2的等待
  }
}
