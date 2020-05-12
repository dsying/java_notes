## Spring从两个角度来实现自动化装配： 
+ 组件扫描（component scanning）：Spring会自动发现应用上下文中所创建的bean。 
+ 自动装配（autowiring）：Spring自动满足bean之间的依赖。

## 自动装配的关键点
+ 声明组件 @Component
    + 表明该类会作为组件类，并告知Spring要为这个类创建bean
    + @Component("bean的ID")
    
+ 扫描组件 @ComponentScan
    + 这个注解能够在Spring中启用组件扫描，默认会扫描和配置类相同的包 
    
+ 装配组件 @Autowired
    + 如果没有匹配的bean，那么在应用上下文创建的时候，Spring会抛出一个异常
    + 如果有多个bean都能满足依赖关系的话，Spring将会抛出一个异常，表明没有明确指定要选 择哪个bean进行自动装配
    

