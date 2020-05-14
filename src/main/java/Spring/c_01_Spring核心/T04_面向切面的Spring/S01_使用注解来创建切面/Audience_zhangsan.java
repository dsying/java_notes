package Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 如果一场演出没有观众的话，那不能称之为演出。对不对？
 * 从演出的角度来看，观众是非常 重要的，但是对演出本身的功能来讲，它并不是核心，这是一个单独的关注点。
 * 因此，将观 众定义为一个切面，并将其应用到演出上就是较为明智的做法
 *
 * Audience类使用@AspectJ注解进行了标注。该注解表明Audience不仅仅是一个POJO， 还是一个切面。
 * Audience类中的方法都使用注解来定义切面的具体行为。
 *
 * Audience有三个方法，定义了一个观众在观看演出时可能会做的事情。
 */
@Aspect
public class Audience_zhangsan {

  @Pointcut("execution(* Spring.c_01_Spring核心.T04_面向切面的Spring.S01_使用注解来创建切面.Performance.perform(..))")
  public void perform() {}
  /**
   * 在演出之前，观众 将手机调至静音状态（silenceCellPhones()）。
   *
   * @Before: 何时
   * execution 何地
   * silenceCellPhone 何事
   */
  @Before("perform()")
  public void silenceCellPhone() {
    System.out.println("表演开始前，张三: 关闭手机");
  }

  /**
   * 如果演出 很精彩的话，观众应该会鼓掌喝彩（applause()）
   */
  @AfterReturning("perform()")
  public void applause() {
    System.out.println("表演成功后，张三: 起立，👏 👏 👏");
  }

  /**
   * 如果演出没有达到观众预期的 话，观众会要求退款（demandRefund()）
   */
  @AfterThrowing("perform()")
  public void demandRefund() {
    System.out.println("表演失败后，张三: 起哄, 退钱");
  }


}
