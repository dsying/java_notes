package multithread.c_001;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 *  + 保证并发可见性
 *  +
 */
public class T00_Volatile {
  private volatile boolean running = true; //对比一下有无 volatile的情况下,整个程序的运行结果的区别

  void m() {
    System.out.println("m start -->");
    while(running) {

    }
    System.out.println("m end <--");
  }

  public static void main(String[] args) throws InterruptedException {
    T00_Volatile t = new T00_Volatile();
    new Thread(t::m, "t1").start();

    TimeUnit.SECONDS.sleep(1);

    t.running = false;
  }
}
