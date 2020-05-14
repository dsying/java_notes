package Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy // 启用自动代理功能
public class AspectJavaConfig {

  @Bean
  public Audience_zhangsan audience() {
    return new Audience_zhangsan();
  }

  @Bean
  public Audience_lishi audience1() {
    return new Audience_lishi();
  }
}
