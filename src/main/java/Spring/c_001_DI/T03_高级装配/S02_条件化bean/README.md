# 条件化的bean
> Spring 4引入了一个新的 @Conditional注解，它可以用到带有@Bean注解的方法上。如果给定的条件计算结果 为true，就会创建这个bean，否则的话，这个bean会被忽略

## 使用
```java
  @Bean("magic")
  @Conditional(MagicExistsCondition.class)
  public Magic magic() {
    return new Magic();
  }
```

```java
public class MagicExistsCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Environment environment = context.getEnvironment();
    for(String str : environment.getActiveProfiles()) {
      if(str.equals("magic"))
        return true;
    }
    return false;
  }
}
```
设置给@Conditional的类可以是任意实现了Condition接口的类型。可以看出来，这个 接口实现起来很简单直接，只需提供matches()方法的实现即可。如果matches()方法返 回true，那么就会创建带有@Conditional注解的bean。如果matches()方法返回 false，将不会创建这些bean

### ConditionContext

通过ConditionContext，我们可以做到如下几点： 
+ 借助getRegistry()返回的BeanDefinitionRegistry检查bean定义； 
+ 借助getBeanFactory()返回的ConfigurableListableBeanFactory检查bean是 否存在，甚至探查bean的属性； 
+ 借助getEnvironment()返回的Environment检查环境变量是否存在以及它的值是 什么； 
+ 读取并探查getResourceLoader()返回的ResourceLoader所加载的资源； 
+ 借助getClassLoader()返回的ClassLoader加载并检查类是否存在

### AnnotatedTypeMetadata
+ 借助isAnnotated()方法，我们能够判断带有@Bean注解的方法是不是还有其他特定的注解

## @Profile 是基于@Conditional 和Condition实现的
```java
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ProfileCondition.class)
public @interface Profile {
	String[] value();
}
```
ProfileCondition的matches方法返回true就创建bean
