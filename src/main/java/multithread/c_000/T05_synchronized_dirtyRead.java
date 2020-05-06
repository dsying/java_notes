package multithread.c_000;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行?
 *
 * 容易产生脏读问题(dirtyRead)
 */
public class T05_synchronized_dirtyRead {
  static class Account {
    double balance;

    public /*synchronized*/ double getBalance() {
      return balance;
    }

    public synchronized void setBalance(double balance) throws InterruptedException {
      TimeUnit.SECONDS.sleep(2);
      this.balance = balance;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Account account = new Account();
    new Thread(() -> {
      try {
        account.setBalance(100);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    TimeUnit.SECONDS.sleep(1);
    System.out.println(account.getBalance());

    TimeUnit.SECONDS.sleep(1);
    System.out.println(account.getBalance());
  }
}
