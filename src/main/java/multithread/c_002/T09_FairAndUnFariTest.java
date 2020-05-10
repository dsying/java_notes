package multithread.c_002;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class T09_FairAndUnFariTest {
  private static ReentrantLock fairLock = new ReentrantLock(true);
  private static ReentrantLock unFairLock = new ReentrantLock(false);

  public static void main(String[] args) {
//    testLock(fairLock);

    testLock(unFairLock);
  }
  private static void testLock(ReentrantLock lock) {
    for(int i = 0; i < 5; i++) {
      new Thread(() -> {
        try {
          lock.lock();
          printLockAndWaiting(lock);
          TimeUnit.SECONDS.sleep(1);
        } catch(InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }, "thread-" + i).start();
    }
  }




  private static void getOwner(ReentrantLock lock) {
    Method getQueuedThreads = null;
    Thread result = null;
    try {
      getQueuedThreads = lock.getClass().getDeclaredMethod("getOwner");
      getQueuedThreads.setAccessible(true);
      result = (Thread) getQueuedThreads.invoke(lock);
    } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    System.out.print("Lock by:" + result.getName() + ", ");
  }


  private static void printLockAndWaiting(ReentrantLock lock) {
    try {
      Field sync = lock.getClass().getDeclaredField("sync");
      sync.setAccessible(true);
      AbstractQueuedSynchronizer aqs = (AbstractQueuedSynchronizer) sync.get(lock);
      Collection<Thread> queuedThreads = aqs.getQueuedThreads();

      getOwner(lock);

      System.out.print("Waiting by: [");
      queuedThreads.stream().sorted(Comparator.comparing(Thread::getName)).forEach(thread -> {
        System.out.print(thread.getName() + ",");
      });
      System.out.print("]");
      System.out.println("\n");
    } catch(NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
