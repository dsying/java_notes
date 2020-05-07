package multithread.c_001;

/**
 *  1 单例模式知道吗?
 *  2 单例模式的双重检查知道吗?
 *  3 单例模式的双重检查有没有必要加volatile?
 */
public class T01_singleton {

  /**
   * JVM类加载过程  加载---> 连接(校验->准备-->解析) ---> 初始化
   *
   * 静态字段会在类初始化阶段完成赋值
   *
   * 问题: 我不想一开始就赋值，而是在我第一次调用 getInstance时自己判断
   */
  static class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() { }

    public static Singleton1 getInstance() {
      return INSTANCE;
    }
  }


  /**
   * 先判断INSTANCE 是否为 null , 是 --> 创建实例并返回， 否 --> 直接返回
   *
   * 问题: 多线程 在 if(INSTANCE == null) 这里会出现都 判断为空的情况
   */
  static class Singleton2 {
    private static Singleton2 INSTANCE;

    private Singleton2() { }

    public static Singleton2 getInstance() {
      if(INSTANCE == null) {
        INSTANCE = new Singleton2();
      }
      return INSTANCE;
    }
  }

  /**
   * getInstance方法 加锁
   *
   * 问题: 直接在getInstance方法上 加synchronized 太重
   */
  static class Singleton3 {
    private static Singleton3 INSTANCE;

    private Singleton3() { }

    public static synchronized Singleton3 getInstance() {
      if(INSTANCE == null) {
        INSTANCE = new Singleton3();
      }
      return INSTANCE;
    }
  }

  /**
   * 相比于Singleton3而言，Singleton4缩小了 synchronized的范围
   *
   * 问题: 虽缩小了synchronized范围，但同时引发了多个线程可能同时 出现 INSTANCE = null 的情况
   *      本例中synchronized仅能保证 INSTANCE = new Singleton4() 这一操作的原子性及并发可见性
   */
  static class Singleton4 {
    private static Singleton4 INSTANCE;

    private Singleton4() { }

    public static Singleton4 getInstance() {
      if(INSTANCE == null) {
        synchronized(Singleton4.class) {
          INSTANCE = new Singleton4();
        }
      }
      return INSTANCE;
    }
  }

  /**
   * 双重判空
   *
   * new 对象包含三个操作
   * 1 申请内存
   * 2 实例初始化
   * 3 赋值给引用
   *
   * 问题： new Singleton5() 的指令重排问题
   *       如果发生指令重排 3 可能跑到 2 前面去， 导致其他线程拿到的实例的值不正确
   */
  static class Singleton5 {
    private static Singleton5 INSTANCE;

    private Singleton5() { }

    public static Singleton5 getInstance() {
      if(INSTANCE == null) {
        synchronized(Singleton5.class) {
          // 多个线程虽都能进入 同步代码块， 但进入后的再次判断 会拦截后续所有新建实例的操作
          if (INSTANCE == null) {
            INSTANCE = new Singleton5();
          }
        }
      }
      return INSTANCE;
    }
  }


  /**
   * 终极方案: volatile + 双重检查
   * volatile 禁止指令重排
   */
  static class Singleton6 {
    private static volatile Singleton6 INSTANCE;

    private Singleton6() { }

    public static Singleton6 getInstance() {
      if(INSTANCE == null) {
        synchronized(Singleton6.class) {
          // 多个线程虽都能进入 同步代码块， 但进入后的再次判断 会拦截后续所有新建实例的操作
          if (INSTANCE == null) {
            INSTANCE = new Singleton6();
          }
        }
      }
      return INSTANCE;
    }
  }


  public static void main(String[] args) {

    for(int i = 0; i < 1000; i++) {
      new Thread(() -> {
        System.out.println(Singleton5.getInstance().hashCode());
      }).start();
    }
  }
}
