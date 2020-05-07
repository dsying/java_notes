package multithread.c_002;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.locks.ReentrantReadWriteLock.*;

/**
 * 读写锁
 *    读锁: 共享锁 (多个线程可以同时读一个共享变量)
 *    写锁: 排他锁 (多个线程需要竞争锁，拿到锁才能去修改变量)
 */
public class T06_ReadWriteLock {
  private static int value = 0;
  private static ReentrantLock lock = new ReentrantLock();

  private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
  private static Lock writeLock = readWriteLock.writeLock();
  private static Lock readLock = readWriteLock.readLock();

  static void read(Lock lock) {
    lock.lock();
    try {
      TimeUnit.SECONDS.sleep(1);
      System.out.println("readOver");
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  static void write(Lock lock, int newValue) {
    lock.lock();
    try {
      TimeUnit.SECONDS.sleep(1);
      System.out.println("write over");
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
//    Runnable writeR = () -> write(lock, new Random().nextInt(100));
//    Runnable readR = () -> read(lock);

    Runnable writeR = () -> write(writeLock, new Random().nextInt(100));
    Runnable readR = () -> read(readLock);

    for(int i = 0; i < 18; i++) new Thread(readR).start();
    for(int i = 0; i < 2; i++) new Thread(writeR).start();
  }
}
