package multithread.c_000;

import java.util.stream.Stream;

public class T03_ThreadState {
  public static void main(String[] args) {

    /**
     * 线程的六种状态
     */
    Stream.of(Thread.State.values()).forEach(System.out::println);

    Thread t1 = new Thread(() -> {

    });

    System.out.println(t1.getState());

  }
}
