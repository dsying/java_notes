## Lock接口提供的synchronized关键字所不具备的特性

+ 尝试非阻塞的获取锁 tryLock()
+ 超时获取锁 tryLock(times)
+ 能被中断的获取锁 lock.lockInterruptedException()



## Lock的API

|           描述           | 方法名称                                                     |
| :----------------------: | :----------------------------------------------------------- |
|       void lock()        | 获取锁，调用该方法的当前线程会获取锁，当获取锁后，从该方法返回 |
| Void lockInterruptibly() | 可中断的获取锁，和lock()方法的不同之处在于该方法会响应中断，即在锁的获取中可以中断当前线程 Thread 的 interrupt()方法 |
|    boolean tryLock()     | 尝试非阻塞的获取锁，调用该方法后立刻返回，如果能够获取则返回true,否则返回false |
|  boolean tryLock(time)   | 超时的获取锁                                                 |
|      void unlock()       | 释放锁                                                       |



## JUC包下的长用工具类

+ CountDownLatch
+ CyclicBarrier
+ ReadWriteLock
+ Semaphore
