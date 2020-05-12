package Spring.c_001_DI.T02_装配bean.JavaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 想要将第三方库中 的组件装配到你的应用中，在这种情况下，是没有办法在它的类上添加@Component 和 @Autowired注解的，因此就不能使用自动化装配的方案了
 *
 * {@link Configuration} @Configuration 注解表明这个类是一个配置类，该类应该包含在Spring应用上下文中如何创建bean的细节
 */
@Configuration
public class CDPlayerConfig {
  @Bean("cdPlayer")
  CDPlayer createCDPlayer() {
    return new CDPlayer();
  }
}
