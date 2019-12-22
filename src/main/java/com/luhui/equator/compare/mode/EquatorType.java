package com.luhui.equator.compare.mode;

import com.luhui.equator.compare.mode.impl.AddressEquatorMode;
import com.luhui.equator.compare.mode.impl.DefaultEquatorMode;
import com.luhui.equator.compare.mode.impl.ExpressionEquatorMode;

/**
 * <p>  提供比较的几种方式 </p>
 *
 * <pre> Created: 2019/11/27 10:08 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public enum EquatorType {
    
    /**
     * 地址比较（双等于）
     */
    ADDRESS(AddressEquatorMode.class),
    /**
     * 主要是equals方式比较，对于bigDecimal采用compateTo
     */
    DEFAULT(DefaultEquatorMode.class),
    /**
     * 自定义表达式，默认使用QLExpression
     */
    EXPRESSION(ExpressionEquatorMode.class);

    private Class<? extends EquatorMode> equatorModeCls;

    public Class<? extends EquatorMode> getEquatorModeCls() {
        return equatorModeCls;
    }

    EquatorType(Class<? extends EquatorMode> equatorModeCls){
        this.equatorModeCls = equatorModeCls;
    }
}
