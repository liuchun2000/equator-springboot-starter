package com.luhui.equator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 动态provider，和EquatorProvider不同的是，EquatorProvider是启动时候创建好再缓存，他认为配置是恒久不变的，且线程不安全
 * 而EquatorDynamicProvider是每次创建，他并不认为配置永远不变，且线程安全，这种方式暂时不支持byDefault方式
 * 对于代码写死，永远不会变的建议使用EquatorProvider,对于数据数据配置
 * </p>
 *
 * <pre> Created: 2019/12/16 16:55 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EquatorDynamicProvider {
    /**
     * 指定从哪个构建者去构建map对象，从代码中构建对象
     * @return
     */
    Class<? extends IDynamicEquatorProvider> provider();
}
