package Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyDessert {
  Dessert dessert;

  @Autowired
  public void setDessert(Dessert dessert) {
    this.dessert = dessert;
  }
}
