package multithread.c_002;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_AQS {
  public static void main(String[] args) {
    test1();
  }

  /**
   * 节点是构成同步队列的基础, 同步器拥有首节点(head）和 尾节点(tail)
   * 没有成功获取同步状态的线程将会成为节点加入到对列的尾部
   */
  static void test1() {
    ReentrantLock lock = new ReentrantLock();
    new Thread(() -> {
      lock.lock();
      try {
        TimeUnit.SECONDS.sleep(20);
      } catch(InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }, "t_sleep").start();


    for(int i = 0; i < 2; i++) {
      new Thread(() -> {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获取同步状态");
        lock.unlock();
      }, "t" + i).start();
    }
    getQueuedThreads(lock);
  }

  private static void getQueuedThreads(ReentrantLock lock) {
    Method getQueuedThreads = null;
    Object result = null;
    try {
      getQueuedThreads = lock.getClass().getDeclaredMethod("getQueuedThreads");
      getQueuedThreads.setAccessible(true);
      result = getQueuedThreads.invoke(lock);
    } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    System.out.println(result);

  }

  private static void getSync(ReentrantLock lock) {
    try {
      Field sync = lock.getClass().getDeclaredField("sync");
      sync.setAccessible(true);
      AbstractQueuedSynchronizer aqs = (AbstractQueuedSynchronizer) sync.get(lock);
      Collection<Thread> queuedThreads = aqs.getQueuedThreads();
      queuedThreads.stream().forEach(System.out::println);
    } catch(NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
