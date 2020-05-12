package Spring.c_001_DI.T03_高级装配.S01_环境与Profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DataSourceConfig {
  @Bean
  @Profile("dev") // 只有在dev profile激活时才会创建
  public DataSource devDataSource() {
    return new DevDatasource();
  }

  @Bean
  @Profile("prod") // 只有在prod profile激活时才会创建
  public DataSource prodDataSource() {
    return new ProdDatasource();
  }

  @Bean // 没有指定profile的bean始终都会被创建，与激活哪个profile没有关系
  public DataSource testDataSource() {
    return new TestDatasource();
  }
}
