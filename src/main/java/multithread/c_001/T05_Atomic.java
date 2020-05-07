package multithread.c_001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可以与 {@link T02_volatile_not_safe} 相比较
 *
 */
public class T05_Atomic {

//  private volatile int count = 0;
  private AtomicInteger count = new AtomicInteger();

  void m() {
    for(int i = 0; i < 1000; i++) {
//      count++;
      count.incrementAndGet();
    }
  }

  public static void main(String[] args) {
    T05_Atomic t = new T05_Atomic();

    List<Thread> threads = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
      threads.add(new Thread(t::m, "thread-" + i));
    }

    threads.forEach(Thread::start);
    threads.forEach(thread -> {
      try {
        thread.join();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    });

    // assert count = 10000
    System.out.println(t.count);
  }
}
