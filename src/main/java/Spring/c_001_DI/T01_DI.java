package Spring.c_001_DI;


import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class T01_DI {
  @Test
  public void peopleShouldDoSomething() {
    Work mockWork = mock(Work.class); // 创建 mockWork
    People people = new People(mockWork); // 注入 mockWork
    people.doWork();
    verify(mockWork, times(1)).doSomething(); // 验证 方法被调用次数
  }
}

class People {
 private Work work;

  /**
   * People 没有与任何特定的Work实现 发生耦合，
   * 对People来说，只要传入 Work的实现类就可以，具体是哪种类型无关紧要
   *
   * 这就是DI带来的最大好处 --- 松耦合
   */
 public People(Work work) {
   this.work = work;
 }

 public void doWork() {
   work.doSomething();
 }
}

interface Work {
  void doSomething();
}

class ITWorker implements Work {

  @Override
  public void doSomething() {
    System.out.println("敲代码");
  }
}
