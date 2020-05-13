package Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性;

import Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.qualifier.Dessert;
import Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.qualifier.DessertConfig;
import Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.qualifier.MyDessert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = DessertConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
public class Dessert_qualifier_Test {

  private static ApplicationContext ctx;
  @BeforeAll
  public static void setUp() {
    ctx = new AnnotationConfigApplicationContext(DessertConfig.class);
  }

  /**
   * Spring限定符 能够在所有可选的bean上进行缩小范围的操作，最终能够达到只有一个bean满足所规定的限制条件
   *
   * @Qualifier 是使用限定符的主要方式
   *
   * 为@Qualifier注解所设置的参数就是想要注入的bean的 ID。
   *
   * 更准确地讲，@Qualifier("iceCream")所引用的bean 要具有String类型的“iceCream”作为限定符
   *
   * 如果没有指定其他的限定符的话，所有的bean都 会给定一个默认的限定符，这个限定符与bean的ID相同
   *
   * 所以: bean的ID和限定符不是一个东西
   *
   */
  @Test
  void test() {
    Dessert myDessert = ctx.getBean("myDessert", MyDessert.class).getDessert();
    Assertions.assertEquals("IceCream", myDessert.getClass().getSimpleName());
  }

  @Test
  void test1() {
    Dessert myDessert1 = ctx.getBean("myDessert", MyDessert.class).getDessert1();
    Assertions.assertEquals("Cake", myDessert1.getClass().getSimpleName());
  }
}
