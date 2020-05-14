package Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AspectJavaConfig.class)
public class PerformTest {
  private static ApplicationContext ctx;

  @BeforeAll
  public static void setUp() {
    ctx = new AnnotationConfigApplicationContext(AspectJavaConfig.class);
  }

  @Test
  public void test1() {
    Performance peking = ctx.getBean("pekingPerformance", Performance.class);
    peking.perform();
  }
}
