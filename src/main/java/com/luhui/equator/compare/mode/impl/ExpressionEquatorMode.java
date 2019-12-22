package com.luhui.equator.compare.mode.impl;

import com.luhui.equator.EquatorException;
import com.luhui.equator.EquatorMethod;
import com.luhui.equator.compare.mode.EquatorMode;
import com.luhui.equator.compare.mode.EquatorModeContext;
import com.luhui.equator.expression.ExpressionInvoker;
import com.luhui.equator.expression.QLExpressionInvoker;
import com.luhui.equator.map.EquatorFieldMapping;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 17:14 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Slf4j
public class ExpressionEquatorMode implements EquatorMode, ApplicationContextAware {

    private ExpressionInvoker expressionInvoker = QLExpressionInvoker.INSTANCE;


    @Override
    public boolean equatorMode(EquatorModeContext equatorModeContext) {
        final EquatorFieldMapping equatorFieldMapping = equatorModeContext.getEquatorFieldMapping();
        final Object source = equatorModeContext.getSource();
        final String target = equatorFieldMapping.getTarget();
        final String expression = equatorFieldMapping.getExpression();
        if(StringUtils.isBlank(expression)){
            log.warn("source={},target={}进行比较时，表达式为空！将直接返回False",equatorFieldMapping.getSource(),equatorFieldMapping.getTarget());
            return false;
        }else{
            final Object retVal = invokeExpression(equatorModeContext, source, target, expression);
            if(retVal!=null && retVal instanceof Boolean){
                return (boolean) retVal;
            }else{
                throw new EquatorException("source="+equatorFieldMapping.getSource()+",target="+equatorFieldMapping.getTarget()+"进行比较时，表达式返回值不是布尔类型！");
            }
        }
    }

    private Object invokeExpression(EquatorModeContext equatorModeContext, Object source, String target, String expression) {
        Map<String,Object> args = new HashMap<>(2);
        args.put("source",source);
        args.put("target",target);

        final EquatorMethod equatorMethod = equatorModeContext.getEquatorMethod();
        final Map<Integer, String> params = equatorMethod.getParams();
        for (Map.Entry<Integer, String> entry : params.entrySet()) {
            args.put(entry.getValue(),equatorModeContext.getInvokeMethodArgs()[entry.getKey()]);
        }

        return expressionInvoker.invoke(expression, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(expressionInvoker instanceof QLExpressionInvoker){
            ((QLExpressionInvoker) expressionInvoker).setApplicationContext(applicationContext);
        }
    }
}
