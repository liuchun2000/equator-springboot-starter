package com.luhui.equator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 15:29 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface EquatorParam {

    /**
     * 参数起别名，起别名后可以在表达式中根据指定参数名去获取参数。
     * 无论是否起了别名，都可以通过source取第一个参数，通过target取第二个参数。除非通过注解指定了source或target名的参数。
     * @return
     */
    String value();
}
