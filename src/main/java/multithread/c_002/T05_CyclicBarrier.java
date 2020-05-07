package multithread.c_002;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T05_CyclicBarrier {
  public static void main(String[] args) {
    // 20: 20个线程到达，是一个前置条件， Runnable： 后置事件， 达成前置条件后执行
    CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> {
      System.out.println("人满， 发车");
    });

    for(int i = 0; i < 100; i++) {
      new Thread(() -> {
        try {
          cyclicBarrier.await();
        } catch(InterruptedException | BrokenBarrierException e) {
          e.printStackTrace();
        }
      }).start();
    }

  }
}
