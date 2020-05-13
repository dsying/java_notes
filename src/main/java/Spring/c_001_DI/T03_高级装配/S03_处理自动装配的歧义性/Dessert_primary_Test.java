package Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性;

import Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.primary.DessertConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = DessertConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
public class Dessert_primary_Test {

  /**
   * 通过@Primary来标示首选的bean
   *
   * 但当存在多个@Primary后，同样会出现 多个bean同时匹配的问题， 你可以把 {@link Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.primary.Cookie} 的 @Primary 注解放开试试
   * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
   * No qualifying bean of type 'Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.primary.Dessert' available:
   * more than one 'primary' bean found among candidates: [cake, cookie, iceCream]
   */
  @Test
  void test() {
  }
}
