package multithread.c_000;

public class T02_sleep_yeild_join {
  public static void main(String[] args) throws InterruptedException {
//    testSleep1();
//    testSleep2();

//    testYield();

    testJoin();
  }

  /**
   * Thread.sleep(1000) ： 告诉操作系统，未来的1000毫秒内不参与cpu时间片的竞争, 但并不意味着
   * Thread.sleep(0)：触发操作系统立即进行一次时间片段的重新分配
   */
  static void testSleep1() {
    new Thread(() -> {
      for(int i = 0; i < 10; i++) {
        System.out.println("T:" + i);
        try {
          Thread.sleep(500);
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * Thread.sleep() 不会导致当前线程丢失所的所有权， 到时间后会进入 **就绪状态**
   */
  static void testSleep2() {
    Thread thread1 = new Thread(() -> {
      synchronized(T02_sleep_yeild_join.class) {
        System.out.println("thread 1 -- 1");
        try {
          Thread.sleep(1000);
          System.out.println("thread 1 -- 2");
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    Thread thread2 = new Thread(() -> {
      synchronized(T02_sleep_yeild_join.class) {
        System.out.println("thread 2");
      }
    });
    thread1.start();

    thread2.start();
  }

  /**
   * yield 即 "谦让"，也是 Thread 类的方法。
   * 它让掉当前线程 CPU 的时间片，使正在运行中的线程重新变成 **就绪状态**，并重新竞争 CPU 的调度权。它可能会获取到，也有可能被其他线程获取到
   */
  static void testYield() {
    new Thread(() -> {
      for(int i = 0; i < 100; i++) {
        System.out.println("A: " + i);
        if(i % 10 == 0) Thread.yield();
      }
    }).start();

    new Thread(() -> {
      for(int i = 0; i < 100; i++) {
        System.out.println("B: " + i);
        if(i % 10 == 0) Thread.yield();
      }
    }).start();
  }

  /**
   * 等待另外一个线程的结束 ↖
   * ｜
   * ｜
   * ⬇
   * t2.join()  ---➡ ⬇
   * ⬇ ⬅ -----  ↖   ⬇
   * ⬇            ↖  ⬇
   * ⬇             ↖ ⬇
   * ⬇              ↖⬇
   * ⬇
   */
  static void testJoin() {
    Thread t1 = new Thread(() -> {
      for(int i = 0; i < 10; i++) {
        System.out.println("A: " + i);
        try {
          Thread.sleep(100);
        } catch(InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        t1.join();
        System.out.println("t2 running ...");
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    });

    t2.start();
    t1.start();
  }
}
