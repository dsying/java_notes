package multithread.c_002;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLock3 {
  /**
   * synchronized 内部锁 的 死锁问题
   */
  static class T1 {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    void m1() {
      synchronized(lock1) {
        System.out.println("lock1 --> m1");
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
        synchronized(lock2) {
          System.out.println("lock1 --> m1");
        }
      }
    }

    void m2() {
      synchronized(lock2) {
        System.out.println("lock2 --> m2");
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
        synchronized(lock1) {
          System.out.println("lock2 --> m2");
        }
      }
    }

    public static void main(String[] args) {
      T1 t = new T1();
      new Thread(t::m1).start();
      new Thread(t::m2).start();
    }
  }

  /**
   * ReentrantLock 的 tryLock方法 尝试获取锁 可避免死锁问题
   */
  static class T2 {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    void m1() {
      if(lock1.tryLock()) {
        try {
          System.out.println("lock1 --> m1");
          TimeUnit.SECONDS.sleep(1);
          if(lock2.tryLock()) {
            try {
              System.out.println("lock2 --> m1");
              TimeUnit.SECONDS.sleep(1);
            } finally {
              lock2.unlock();
            }
          }
        } catch(InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock1.unlock();
        }
      }
    }

    void m2() {
      if(lock2.tryLock()) {
        try {
          System.out.println("lock2 --> m2");
          TimeUnit.SECONDS.sleep(1);
          if(lock1.tryLock()) {
            try {
              System.out.println("lock1 --> m2");
              TimeUnit.SECONDS.sleep(1);
            } finally {
              lock1.unlock();
            }
          }
        } catch(InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock2.unlock();
        }
      }
    }

    public static void main(String[] args) {
      T2 t = new T2();
      new Thread(t::m1).start();
      new Thread(t::m2).start();
    }
  }

  /**
   * ReentrantLock 的 tryLock(times) 方法可在一段时间内尝试获取锁
   */
  static class T3 {
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    void m1() throws InterruptedException {
      if(lock1.tryLock(1, TimeUnit.SECONDS)) {
        try {
          System.out.println("lock1 --> m1");
          TimeUnit.SECONDS.sleep(1);
          if(lock2.tryLock(1, TimeUnit.SECONDS)) {
            try {
              System.out.println("lock2 --> m1");
              TimeUnit.SECONDS.sleep(1);
            } finally {
              lock2.unlock();
            }
          }
        } catch(InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock1.unlock();
        }
      }
    }

    void m2() throws InterruptedException {
      if(lock2.tryLock(2, TimeUnit.SECONDS)) {
        try {
          System.out.println("lock2 --> m2");
          TimeUnit.SECONDS.sleep(1);
          if(lock1.tryLock(2, TimeUnit.SECONDS)) {
            try {
              System.out.println("lock1 --> m2");
              TimeUnit.SECONDS.sleep(1);
            } finally {
              lock1.unlock();
            }
          }
        } catch(InterruptedException e) {
          e.printStackTrace();
        } finally {
          lock2.unlock();
        }
      }
    }

    public static void main(String[] args) {
      T3 t = new T3();
      new Thread(() -> {
        try {
          t.m1();
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
      new Thread(() -> {
        try {
          t.m2();
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}
