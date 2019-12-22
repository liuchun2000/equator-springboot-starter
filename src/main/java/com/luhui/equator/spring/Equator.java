package com.luhui.equator.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 注解在Equator接口类上，自动将生成的代理类放入spring ioc容器 </p>
 *
 * <pre> Created: 2019/12/3 19:42 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Equator {
}
