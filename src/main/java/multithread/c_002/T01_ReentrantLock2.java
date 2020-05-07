package multithread.c_002;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link ReentrantLock} 用于替代 synchronized
 *
 * 区别:
 * 1 synchronized遇到异常会自动释放锁，但是 ReentrantLock必须手动释放锁 unLock()
 * 2 ReentrantLock 可以 尝试获取锁 tryLock()， 该方法会立即返回一个boolean值，而不是阻塞
 */
public class T01_ReentrantLock2 {
  private ReentrantLock lock = new ReentrantLock();

  void m1() {
    lock.lock();
    try {
      for(int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(i);
      }
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  /**
   * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
   * 可以根据tryLock的返回值来判断是否锁定
   */
  void m2() {
    boolean locked = false;
    try {
      //
      locked = lock.tryLock(5,TimeUnit.SECONDS);
      System.out.println("m2 invoked ...");
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      if(locked) lock.unlock();
    }
  }

  public static void main(String[] args) {
    T01_ReentrantLock2 t = new T01_ReentrantLock2();
    new Thread(t::m1).start();

    new Thread(() -> {
      for(int i = 0; i < 3; i++) {
        t.m2();
      }
    }).start();
  }
}
