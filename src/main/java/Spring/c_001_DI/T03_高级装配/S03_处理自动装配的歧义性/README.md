# 处理自动装配的歧义
> 不过，仅有一个bean匹配所需的结果时，自动装配才是有效的。如果有多个bean能够匹 配结果的话，这种歧义性会阻碍Spring自动装配属性、构造器参数或方法参数

解决歧义的方法
1. 将可选bean中的某一个设为首选（primary）的bean
2. 使用限定符（qualifier）来帮助Spring 将可选的bean的范围缩小到只有一个bean


## 标示首选bean--@Primary
```java
@Component
@Primary
class Cake implements Dessert {}

@Component
class Cookie implements Dessert {}

@Component
class IceCream implements Dessert {}
```
告诉Spring在遇到歧义性的时候要选择首选的bean

## 限定自动装配的bean--@Qualifier
> Spring的限定符能够在所有可选的bean上进行缩小范围的操作，最终能够达到只 有一个bean满足所规定的限制条件, @Qualifier注解是使用限定符的主要方式

### 与@Autowired和@Inject协同使 用，在注入的时候指定想要注入进去的是哪个bean。
```java
  @Autowired
  @Qualifier("iceCream")
  public void setDessert(Dessert dessert) {
    this.dessert = dessert;
  }
```
> 基于默认的bean ID作为限定符是非常简单的
+ 为`@Qualifier`注解所设置的参数就是想要注入的**bean的 ID**。
+ 更准确地讲，`@Qualifier("iceCream")`所引用的bean 要具有String类型的“iceCream”作为**限定符**。
+ 如果没有指定其他的**限定符**的话，所有的bean都 会给定一个**默认的限定符**，这个限定符与**bean的ID**相同

#### 自定义限定符

```java
@Component // bean的id为cake, 限定符 默认和ID相同
/** 但通过@Qualifer 创建了自定义限定符， 所以限定符为 cocoCake , 所以 {@link MyDessert#setDessert1(Dessert)} 方法的@Qualifer注解要使用相同的限定符 */
@Qualifier("cocoCake")
class Cake implements Dessert {}
```
```java
  @Autowired
  @Qualifier("cocoCake") /** {@link Cake} 使用了自定义的限定符*/
  public void setDessert1(Dessert dessert1) {
    this.dessert1 = dessert1;
  }
```
