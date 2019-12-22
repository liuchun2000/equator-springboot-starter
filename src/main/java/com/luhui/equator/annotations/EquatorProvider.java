package com.luhui.equator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 13:22 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EquatorProvider {

    /**
     * 指定从哪个构建者去构建map对象，从代码中构建对象
     * @return
     */
    Class<? extends IEquatorProvider> provider();

}
