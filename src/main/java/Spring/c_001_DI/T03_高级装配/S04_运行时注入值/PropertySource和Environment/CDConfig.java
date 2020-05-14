package Spring.c_001_DI.T03_高级装配.S04_运行时注入值.PropertySource和Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 在Spring中，处理外部值的最简单方式就是声明属性源并通过Spring的Environment来检索属性。
 */

@Configuration
@PropertySource("classpath:/app.yml")
public class CDConfig {
  @Autowired
  Environment env;

  @Bean
  public CD cd() {
    return new CD(env.getProperty("title"), env.getProperty("artist"));
  }
}
