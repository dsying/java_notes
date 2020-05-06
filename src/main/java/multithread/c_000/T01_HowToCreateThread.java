package multithread.c_000;

/**
 * 如何创建线程
 */
public class T01_HowToCreateThread {
  /**
   * 1 继承Thread类
   */
  static class T1 extends Thread {
    @Override
    public void run() {
      System.out.println("my thread t1");
    }
  }

  /**
   * 2 实现Runnable接口
   */
  static class T2 implements Runnable {
    public void run() {
      System.out.println("my thread t2");
    }
  }

  public static void main(String[] args) {
    new T1().start();

    new Thread(new T2()).start();

    /**
     * 2.1 匿名内部类
     */
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("my thread t3");
      }
    }).start();

    /**
     * 2.2 lambda表达式
     */
    new Thread(() -> System.out.println("my thread t4")).start();
  }
}
