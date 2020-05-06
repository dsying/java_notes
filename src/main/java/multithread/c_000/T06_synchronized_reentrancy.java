package multithread.c_000;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 *
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁
 * 再次申请的时候仍然能得到该对象的锁， 也就是synchronized保存的锁是可重入的
 */
public class T06_synchronized_reentrancy {
  synchronized void m1() throws InterruptedException {
    System.out.println("method m1 invoked --->");
    TimeUnit.SECONDS.sleep(1);
    m2();
  }

  synchronized void m2() {
    System.out.println("method m2 invoked --->");
  }

  public static void main(String[] args) throws InterruptedException {
    new T06_synchronized_reentrancy().m1();
  }


  /**
   * synchronized 锁可重入性在 继承中的体现
   */
  static class Father {
    synchronized void m() {
      System.out.println("father method invoked --->");
    }
  }

  static class Son extends Father {
    @Override
    synchronized void m() {
      // 如果不可重入 此处根本不可能调用父类的方法
      super.m();
      System.out.println("son method m invoked --->");
    }

    public static void main(String[] args) {
      new Son().m();
    }
  }
}
