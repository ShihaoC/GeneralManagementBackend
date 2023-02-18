package cn.mrcsh.Annotations;

import java.lang.annotation.*;

/**
 * 接口监控注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface APIMonitor {
    String parentAPI() default "";

    String api() default "";

    String value() default "";


}
