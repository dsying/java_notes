package multithread.c_001;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象o, 如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变, 等同于 换锁
 *
 * 应该避免将锁定对象的引用换成另外的对象， 或者直接将锁 变成 final类型的不可改变
 *
 */
public class T04_monitorLock_changed {
  /*final*/ Object o = new Object();

  void m() {
    synchronized(o) {
      System.out.println(Thread.currentThread().getName() + " --start --->");
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    T04_monitorLock_changed t = new T04_monitorLock_changed();

    new Thread(t::m, "t1").start();

    TimeUnit.SECONDS.sleep(1);

    // 如果不换锁，则 线程t2 10秒之内都不会执行，应为t1sleep10秒，这期间 t1不会释放锁
    // 但重新给o赋值，等同于换锁，线程t2不需要再去竞争之前那把被t1占用的锁，只要获取新锁就能立即执行
    t.o = new Object();

    new Thread(t::m, "t2").start();
  }
}
