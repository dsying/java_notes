package Spring.c_001_DI.T03_高级装配.S04_运行时注入值.解析属性占位符;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * 在Spring中，处理外部值的最简单方式就是声明属性源并通过Spring的Environment来检索属性。
 */

@Configuration
@PropertySource("classpath:/app.yml")
public class CDConfig {

  /**
   * 为了使用占位符，我们必须要配置一个PropertyPlaceholderConfigurer bean 或PropertySourcesPlaceholderConfigurer bean。
   * 从Spring 3.1开始，推荐使 用PropertySourcesPlaceholderConfigurer，因为它能够基于Spring Environment 及其属性源来解析占位符
   * @return
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public CD cd(@Value("${title}") String title, @Value("${artist}") String artist) {
    return new CD(title, artist);
  }
}
