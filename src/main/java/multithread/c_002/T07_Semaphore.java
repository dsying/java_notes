package multithread.c_002;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore: 收费站和车辆， 100辆车(线程)和2个收费口(Semaphore的permits)
 *
 * 作用: 限流
 *
 * 理解: 同一时间只能允许 n 个线程执行任务， acquire()获取许可证，执行完，返还许可证。 许可证发完了，其它线程就要等
 */
public class T07_Semaphore {
  public static void main(String[] args) {
    // 同时允许几个线程执行
    Semaphore s = new Semaphore(1);

    new Thread(() -> {
      try {
        s.acquire();
        System.out.println("T1 running ...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1 running ...");
      } catch(InterruptedException e) {
        e.printStackTrace();
      } finally {
        s.release();
      }
    }).start();

    new Thread(() -> {
      try {
        s.acquire();
        System.out.println("T2 running ...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T2 running ...");
      } catch(InterruptedException e) {
        e.printStackTrace();
      } finally {
        s.release();
      }
    }).start();
  }
}
