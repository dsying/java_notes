package Spring.c_001_DI.T03_高级装配.S03_处理自动装配的歧义性.normal;

import org.springframework.stereotype.Component;

public interface Dessert {
}

@Component
class Cake implements Dessert {

}

@Component
class Cookie implements Dessert {

}

@Component
class IceCream implements Dessert {

}
