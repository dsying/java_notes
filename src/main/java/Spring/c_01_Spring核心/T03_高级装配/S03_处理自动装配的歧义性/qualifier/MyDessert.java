package Spring.c_01_Spring核心.T03_高级装配.S03_处理自动装配的歧义性.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyDessert {
  Dessert dessert;

  Dessert dessert1;

  @Autowired
  @Qualifier("iceCream")
  public void setDessert(Dessert dessert) {
    this.dessert = dessert;
  }

  public Dessert getDessert() {
    return dessert;
  }

  public Dessert getDessert1() {
    return dessert1;
  }

  @Autowired
  @Qualifier("cocoCake") /** {@link Cake} 使用了自定义的限定符*/
  public void setDessert1(Dessert dessert1) {
    this.dessert1 = dessert1;
  }
}
