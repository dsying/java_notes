package Spring.c_001_DI.T02_HowToCreateBean.ComponentScanAndAutowired;

import org.springframework.stereotype.Component;

@Component
public class CDPlayer {
  void play() {
    System.out.println("周杰伦 -- 稻香");
  }
}
