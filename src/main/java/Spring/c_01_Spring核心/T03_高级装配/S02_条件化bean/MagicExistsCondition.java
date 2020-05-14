package Spring.c_01_Spring核心.T03_高级装配.S02_条件化bean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

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
