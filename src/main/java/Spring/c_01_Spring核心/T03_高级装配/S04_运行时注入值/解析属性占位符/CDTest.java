package Spring.c_01_Spring核心.T03_高级装配.S04_运行时注入值.解析属性占位符;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = CDConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
public class CDTest {
  private static ApplicationContext ctx;

  @BeforeAll
  public static void setUp() {
    ctx = new AnnotationConfigApplicationContext(CDConfig.class);
  }

  @Test
  void test() {
    CD cd = ctx.getBean("cd", CD.class);
    System.out.println(cd.getTitle());
  }

}
