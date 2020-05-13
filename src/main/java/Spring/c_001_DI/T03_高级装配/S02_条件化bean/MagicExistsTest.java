package Spring.c_001_DI.T03_高级装配.S02_条件化bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = MagicJavaConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
@ActiveProfiles("magic")
public class MagicExistsTest {
  @Autowired
  Magic magic;

  @Test
  void test() {
    Assertions.assertNotNull(magic);
  }
}

