package Spring.c_01_Spring核心.T03_高级装配.S03_处理自动装配的歧义性.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public interface Dessert {
}

@Component // bean的id为cake, 限定符 默认和ID相同
/** 但通过@Qualifer 创建了自定义限定符， 所以限定符为 cocoCake , 所以 {@link MyDessert#setDessert1(Dessert)} 方法的@Qualifer注解要使用相同的限定符 */
@Qualifier("cocoCake")
class Cake implements Dessert {

}

@Component // bean的id为cookie， 限定符 默认和ID相同
class Cookie implements Dessert {

}

@Component // bean的id为iceCream， 限定符 默认和ID相同
class IceCream implements Dessert {

}
