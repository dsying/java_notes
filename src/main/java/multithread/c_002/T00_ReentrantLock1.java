package multithread.c_002;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T00_ReentrantLock1 {
  /**
   * Synchronized是可重入锁
   */
  static class T1 {
    synchronized void m1() {
      System.out.println("me invoked ...");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
      m2();
    }

    synchronized void m2() {
      System.out.println("m2 invoked ...");
    }

    public static void main(String[] args) {
      T1 t = new T1();
      new Thread(t::m1).start();
    }
  }

  /**
   * ReentrantLock是可重入锁
   */
  static class T2 {
    private Lock lock = new ReentrantLock();
    void m1() {
      lock.lock();
      try {
        for(int i = 0; i < 10; i++) {
          TimeUnit.SECONDS.sleep(1);
          if(i == 5) {
            m2();
          }
          System.out.println(i);
        }
      } catch(InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }

    void m2() {
      lock.lock();
      System.out.println("m2 invoked ");
      lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
      T2 t = new T2();
      new Thread(t::m1).start();
//      TimeUnit.SECONDS.sleep(1);
//      new Thread(t::m2).start();
    }
  }
}
