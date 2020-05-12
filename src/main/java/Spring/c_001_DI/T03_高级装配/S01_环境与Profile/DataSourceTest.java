package Spring.c_001_DI.T03_高级装配.S01_环境与Profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = DataSourceConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
@ActiveProfiles("dev")
public class DataSourceTest {
  @Autowired
  DataSource devDataSource;

  @Autowired
  DataSource testDataSource;

  @Test
  void dev() {
    Assertions.assertEquals("dev", devDataSource.getEnvironment());
  }

  @Test
  void test() {
    Assertions.assertEquals("test", testDataSource.getEnvironment());
  }
}
