package Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性;

import Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.normal.DessertConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = DessertConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
public class Dessert_normal_Test {

  /**
   * 没有找到唯一的bean，反而找到了三个bean: cake, cookie, iceCream
   *
   * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
   * No qualifying bean of type 'Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.Dessert' available:
   * expected single matching bean but found 3: cake,cookie,iceCream
   */
  @Test
  void test() {
  }
}
