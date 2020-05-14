package Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Audience_lishi 使用 Around 环绕通知完成了 Audience_zhangsan 中 三个通知做的事情
 */
@Aspect
public class Audience_lishi {

  @Pointcut("execution(* Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面.Performance.perform(..))")
  public void perform() {}

  @Around("perform()")
  public void watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("表演开始前，李四: 关闭手机");
    joinPoint.proceed();
    System.out.println("表演成功后，李四: 起立，👏 👏 👏");
  }
}
