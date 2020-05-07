package multithread.c_001;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 因为不能保证原子性所以无法保证线程安全，它只能保证并发可见性， 所以 volatile 无法替代 synchronized
 *
 * volatile 能保证 count的并发可见性，但是无法保证 count++的原子性
 *
 * 当然也可以使用原子类，详情请看 {@link T05_Atomic}
 */
public class T02_volatile_not_safe {
  private /*volatile*/ int count = 0;

  void m() {
    for(int i = 0; i < 1000; i++) count++;
  }

  public static void main(String[] args) {
    T02_volatile_not_safe t = new T02_volatile_not_safe();
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
