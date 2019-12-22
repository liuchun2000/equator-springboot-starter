package com.luhui.equator.expression;

import java.util.Map;

/**
 * <p> 表达式执行者 </p>
 *
 * <pre> Created: 2019/12/5 11:23 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public interface ExpressionInvoker {

    /**
     * 执行指定表达式
     * @param expression
     * @param args
     * @return
     */
    Object invoke(String expression, Map<String,Object> args);
}
