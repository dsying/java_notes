## volatile

+ 保证线程可见性
+ 禁止指令重排
    + 单例模式的双重检查
    
## CAS(无锁优化，自旋)
+ Compare And Set
+ cas(v, Expected, NewValue)
    + if (v == Expected) v == NewValue
    + else 继续cas
+ ABA 问题
    + 1 -> 2 -> 1
    + 版本号解决 ABA 问题
        + 1A -> 2B -> 1C
    + 如果是基础类型 --> 无所谓
    + 如果是引用类型(你前女友找你来复合，然后她中间又找了个男朋友，分手后来找你) ---> 你觉得有所谓吗
