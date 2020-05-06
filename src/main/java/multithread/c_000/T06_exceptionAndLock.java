package multithread.c_000;

import java.util.concurrent.TimeUnit;

/**
 * 异常与锁
 *
 * 程序在执行过程中，如果出现异常，默认情况下锁会被释放
 *
 */
public class T06_exceptionAndLock {
  public static class T {
    synchronized void m() {
      System.out.println(Thread.currentThread().getName() + " started m() --->");
      int count = 0;
      while(true) {
        count++;
        System.out.println(Thread.currentThread().getName() + " count: " + count);

        if(count > 5) {
          int i = 1 / 0;
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    T t = new T();
    new Thread(() -> {
      t.m();
    }).start();

    TimeUnit.SECONDS.sleep(2);

    new Thread(() -> {
      t.m();
    }).start();
  }
}
