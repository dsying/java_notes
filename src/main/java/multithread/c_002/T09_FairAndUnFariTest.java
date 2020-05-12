package multithread.c_002;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class T09_FairAndUnFariTest {
  private static ReentrantLock fairLock = new ReentrantLock(true);
  private static ReentrantLock unFairLock = new ReentrantLock(false);

  public static void main(String[] args) {
    testLock(fairLock);
//    testLock(unFairLock);
  }

  private static void testLock(ReentrantLock lock) {
    for(int i = 0; i < 5; i++) {
      new Thread(() -> {
        for(int j = 0; j < 2; j++) {
          try {
            lock.lock();
            printLockAndWaiting(lock);
            TimeUnit.SECONDS.sleep(1);
          } catch(InterruptedException e) {
            e.printStackTrace();
          } finally {
            lock.unlock();
          }
        }
      }, " " + i).start();
    }
  }

  private static void printLockAndWaiting(ReentrantLock lock) {
    try {
      Field sync = lock.getClass().getDeclaredField("sync");
      sync.setAccessible(true);
      AbstractQueuedSynchronizer aqs = (AbstractQueuedSynchronizer) sync.get(lock);
      Collection<Thread> queuedThreads = aqs.getQueuedThreads();
      System.out.print("Lock by:" + Thread.currentThread().getName());
      System.out.print(", Waiting by: [");
      queuedThreads.stream().sorted(Comparator.comparing(Thread::getName)).forEach(thread -> {
        System.out.print(thread.getName() + ",");
      });
      System.out.print("]");
      System.out.println("\r");
    } catch(NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
