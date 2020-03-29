package com.luhui.equator.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 13:14 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
public @interface EquatorMappings{

    /**
     * 要比较的映射配置
     * @return
     */
    EquatorMapping[] value() default {};

    /**
     * 对于没有写的相同的属性名，是否自动映射，true映射，false不映射
     * @return
     */
    boolean byDefault() default true;

    /**
     * 排除要比较的字段，仅 byDefault=true时生效
     * @return
     */
    String[] excludes() default {};


    EquatorMappings DEFAULT_MAPPINGS = new EquatorMappings(){

        @Override
        public Class<? extends Annotation> annotationType() {
            return EquatorMappings.class;
        }

        @Override
        public EquatorMapping[] value() {
            return new EquatorMapping[0];
        }

        @Override
        public boolean byDefault() {
            return true;
        }

        @Override
        public String[] excludes() {
            return new String[0];
        }
    };
}