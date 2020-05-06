package multithread.c_000;

/**
 * Synchronized关键字 保证原子性和并发可见性
 *
 * 锁: 每个对象都有一把锁，锁的信息保存在对象的 对象头 中，
 * entryCount：每把锁又与一个 entry count想关联，当线程持有锁时，entryCount加1，释放锁时entryCount减1.
 *            换句话说，当entryCount > 0时，代表锁被占用， 反之代表锁未被占用
 *
 * 多个线程需要竞争同一把锁才能保
 */
public class T04_synchronized {
  private static int count = 0;

  /**
   * 同步代码块: monitorenter 和 monitorexit 来实现对锁的占用和释放
   */
  public static void syncBlock() {
    Object lock = new Object();
    synchronized(lock) {
      // 任何线程要执行下面的代码，必须先拿到对象lock的锁🔒
      count++;
      System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
  }

  /**
   * 同步方法: 通过 ACC_SYNCHRONIZED来实现
   */
  public synchronized static void syncMethod() {
    // 静态同步方法的锁是当前类的class对象，即 T04_synchronized.class
    count++;
    System.out.println(Thread.currentThread().getName() + " count = " + count);
  }

  public static void syncTest1() {
    for(int i = 0; i < 20; i++) {
      new Thread(() -> {
        syncBlock();
      }).start();
    }
  }


  public static void syncTest2() {
    T t = new T();
    for(int i = 0; i < 100; i++) {
      new Thread(t, "Thread" + i).start();
    }
  }

  public static void main(String[] args) {
//    syncTest1();

    syncTest2();
  }

  static class T implements Runnable {
    private int count = 10;
    @Override
    /**
     * synchronized 能保证原子性和并发可见性
     */
    public /*synchronized*/ void run() {
      // 无法保证run方法内部的两条语句能够连续执行
      count--;
      System.out.println(Thread.currentThread().getName() + " count: " + count);
    }
  }
}
