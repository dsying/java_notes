package Spring.c_001_DI.T02_HowToCreateBean.ComponentScanAndAutowired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //以便在测试开始的时候自动创建Spring的应用上下文
@ContextConfiguration(classes = CDPlayerConfig.class) // Spring上下文需要在CDPlayerConfig中加载配置
public class CDPlayerTest {
  @Autowired
  CDPlayer cdPlayer;

  @Test
  void play() {
    cdPlayer.play();
    Assertions.assertNotNull(cdPlayer);
  }
}
