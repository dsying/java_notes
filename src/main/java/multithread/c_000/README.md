+ 线程的概念
+ 启动方式
+ 常用方法

+ 线程同步
    + synchronized
        + 锁的是对象不是代码
        + this XX.class
        + 锁定方法 非锁定方法 同时执行
        + 锁升级
            + 偏向锁
            + 自旋锁
            + 重量级锁

> 执行时间短，线程数少，用自旋锁

> 执行时间长，线程数多，用系统锁
