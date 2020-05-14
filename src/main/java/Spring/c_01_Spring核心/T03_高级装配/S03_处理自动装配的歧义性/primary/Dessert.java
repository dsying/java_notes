package Spring.c_01_Spring核心.T03_高级装配.S03_处理自动装配的歧义性.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public interface Dessert {
}

@Component
@Primary
class Cake implements Dessert {

}

@Component
//@Primary
class Cookie implements Dessert {

}

@Component
class IceCream implements Dessert {

}
