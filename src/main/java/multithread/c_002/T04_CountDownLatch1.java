package multithread.c_002;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * CountDownLatch
 */
public class T04_CountDownLatch1 {
  public static void main(String[] args) {
    CountDownLatch latch = new CountDownLatch(100);
    Thread[] threads = new Thread[(int)latch.getCount()];
    for(int i = 0; i < 100; i++) {
      int finalI = i;
      threads[i] = new Thread(() -> {
        System.out.println(finalI);
        latch.countDown();
      });
    }

    Stream.of(threads).forEach(Thread::start);

    try {
      latch.await(); // 等同于一把 们拴， 知道CountDownLatch的count 为0时才能执行后续代码
    } catch(InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("end -->");
  }
}
