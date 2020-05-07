package multithread.c_001;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 锁优化问题
 */
public class T03_synchronized_optimization {

  /**
   * 锁优化之 --> 细化
   */
  class T1 {
    int count = 0;

    synchronized void m1() throws InterruptedException {
      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);

      count++;

      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);
    }

    void m2() throws InterruptedException {
      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);

      // 业务逻辑只有下面这句需要sync,这时不应该在整个方法上加锁
      // 此时采用细粒度的锁，可以使线程竞争锁的时间缩短，从而提高效率
      synchronized(this) {
        count++;
      }

      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);
    }
  }

  /**
   * 锁优化之 --> 粗化
   */
  class T2 {
    int count = 0;

    synchronized void m1() throws InterruptedException {
      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);

      synchronized(this) {
        count++;
      }

      synchronized(this) {
        count++;
      }

      synchronized(this) {
        count++;
      }

      synchronized(this) {
        count++;
      }

      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);
    }

    synchronized void m2() throws InterruptedException {
      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);

      // 业务逻辑有很多语句需要sync, 这时不应该在每个sync操作上加锁
      // 此时采用粗粒度的锁，可以使线程竞争锁的时间缩短，从而提高效率
      count++;
      count++;
      count++;
      count++;

      // do sth need not sync
      TimeUnit.SECONDS.sleep(1);
    }
  }
}
