## 使用背景
在开发软件的时候，有一个很大的挑战就是将应用程序从一个环境迁移到另外一个环境。开 发阶段中，某些环境相关做法可能并不适合迁移到生产环境中，甚至即便迁移过去也无法正 常工作。数据库配置、加密算法以及与外部系统的集成是跨环境部署时会发生变化的几个典 型例子

## 使用
### 配置 profile bean
```java
  @Bean
  @Profile("dev") // 只有在dev profile激活时才会创建
  public DataSource devDataSource() {
    return new DevDatasource();
  }
```
### 激活bean
+ spring.profiles.active(优先级高)
+ spring.profiles.default
如果设置了 spring.profiles.active属性的话，那么它的值就会用来确定哪个profile是激活的。但 如果没有设置spring.profiles.active属性的话，那Spring将会查 找spring.profiles.default的值。如果spring.profiles.active和 spring.profiles.default均没有设置的话，那就没有激活的profile，因此只会创建那 些没有定义在profile中的bean

有多种方式来设置这两个属性： 
+ 作为DispatcherServlet的初始化参数； 
+ 作为Web应用的上下文参数； 
+ 作为JNDI条目； 
+ 作为环境变量； 
+ 作为JVM的系统属性； 
+ 在集成测试类上，使用@ActiveProfiles注解设置。
