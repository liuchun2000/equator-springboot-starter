package com.luhui.equator.annotations;

import com.luhui.equator.compare.mode.EquatorType;

/**
 * <p> 比较映射  </p>
 *
 * <pre> Created: 2019/11/27 10:00 </pre>
 *
 * @author hlu
 * @version 1.0
 */

public @interface EquatorMapping {

    /**
     * 源字段名（第一个参数）
     * @return
     */
    String source();

    /**
     * 目标字段名（第二个参数）
     * @return
     */
    String target();

    /**
     * 比较类型
     * @return
     */
    EquatorType equalsType() default EquatorType.DEFAULT;

    /**
     * 比较类型是表达式的时候使用
     * @return
     */
    String expression() default "";

    /**
     * 字段中文名
     * @return
     */
    String fieldName() default "";


    /**
     * 比较不相等时候的源对象 value值
     * @return
     */
    String notEquatorSourceVal() default "";

    /**
     * 比较不相等时候目录对象value值
     * @return
     */
    String notEquatorTargetVal() default "";
}
