package Spring.c_01_Spring核心.T02_装配bean.ComponentScanAndAutowired;

import org.springframework.stereotype.Component;

@Component
public class CDPlayer {
  void play() {
    System.out.println("周杰伦 -- 稻香");
  }
}
