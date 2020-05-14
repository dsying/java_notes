package Spring.c_01_Spring核心.T03_高级装配.S02_条件化bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicJavaConfig {
  @Bean("magic")
  @Conditional(MagicExistsCondition.class) /** {@link MagicExistsCondition} 的 matches 方法 返回true时 就创建该bean*/
  public Magic magic() {
    return new Magic();
  }
}
