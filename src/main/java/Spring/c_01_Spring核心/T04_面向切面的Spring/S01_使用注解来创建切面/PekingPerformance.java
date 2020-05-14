package Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面;

import org.springframework.stereotype.Component;

@Component
public class PekingPerformance implements Performance{
  @Override
  public void perform() {
    System.out.println("京剧表演 开始");
  }
}
