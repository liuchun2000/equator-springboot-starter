package com.luhui.equator.compare;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.luhui.equator.EquatorConfiguration;
import com.luhui.equator.EquatorException;
import com.luhui.equator.EquatorMethod;
import com.luhui.equator.EquatorResult;
import com.luhui.equator.accessor.ObjectAccessor;
import com.luhui.equator.accessor.ReflectionAsmObjectAccessor;
import com.luhui.equator.annotations.EquatorDynamicProvider;
import com.luhui.equator.compare.mode.EquatorMode;
import com.luhui.equator.compare.mode.EquatorModeContext;
import com.luhui.equator.compare.mode.ObjectCompareContext;
import com.luhui.equator.expression.ExpressionInvoker;
import com.luhui.equator.expression.QLExpressionInvoker;
import com.luhui.equator.map.EquatorFieldMapping;
import com.luhui.equator.utils.ReflectAsmUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p> 基于reflectasm对象比较器 </p>
 *
 * <pre> Created: 2019/12/4 13:38 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Slf4j
public class ReflectAsmCompare implements ObjectCompare {

    private final ObjectAccessor objectAccessor = new ReflectionAsmObjectAccessor();
    private final EquatorConfiguration equatorConfiguration;
    private final ExpressionInvoker expressionInvoker = QLExpressionInvoker.INSTANCE;


    public ReflectAsmCompare(EquatorConfiguration equatorConfiguration) {
        this.equatorConfiguration = equatorConfiguration;
    }

    @Override
    public <T extends EquatorResult> List<T> compare(ObjectCompareContext compareContext) {
        final Object source = compareContext.getSource();
        final Object target = compareContext.getTarget();
        final Method method = compareContext.getEquatorMethod().getMethod();
        final EquatorDynamicProvider equatorProvider = AnnotatedElementUtils.findMergedAnnotation(method, EquatorDynamicProvider.class);
        final Set<EquatorFieldMapping> equatorFieldMappings;
        if(equatorProvider==null){
            equatorFieldMappings = compareContext.getEquatorMethod().getEquatorMap().getEquatorFieldMappings();
        }else{
            equatorFieldMappings = equatorConfiguration.getApplicationContext().getBean(equatorProvider.provider()).provide(compareContext);
        }
        final Class<?> returnType = method.getReturnType();
        if (returnType != List.class) {
            throw new EquatorException("暂时返回值只支持List。");
        }
        final Class<T> rowType = (Class<T>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
        if (!EquatorResult.class.isAssignableFrom(rowType)) {
            throw new EquatorException("泛型类型必须是EquatorResult的子类！");
        }

        ConstructorAccess<T> access = ReflectAsmUtils.getConstructorAccess(rowType);

        List<T> equatorMaps = new ArrayList<>();

        for (EquatorFieldMapping equatorFieldMapping : equatorFieldMappings) {
            final String sourceFieldName = equatorFieldMapping.getSource();
            final Object sourceVal = objectAccessor.getFieldValue(source, sourceFieldName);

            final String targetFieldName = equatorFieldMapping.getTarget();
            final Object targetVal = objectAccessor.getFieldValue(target, targetFieldName);

            final String notEquatorSourceVal = equatorFieldMapping.getNotEquatorSourceVal();
            final String notEquatorTargetVal = equatorFieldMapping.getNotEquatorTargetVal();
            final Class<? extends EquatorMode> equatorModeCls = equatorFieldMapping.getEquatorType().getEquatorModeCls();
            final EquatorMode equatorMode = equatorConfiguration.getApplicationContext().getBean(equatorModeCls);
            if (!equatorMode.equatorMode(new EquatorModeContext(sourceVal, targetVal, equatorConfiguration,equatorFieldMapping,
                    compareContext.getEquatorMethod(),compareContext.getInvokeMethodArgs()))) {
                T equatorResult = access.newInstance();
                equatorResult.setSourceField(sourceFieldName);
                if(StringUtils.isBlank(notEquatorSourceVal)) {
                    equatorResult.setSourceValue(sourceVal);
                }else{
                    equatorResult.setSourceValue(invokeExpression(compareContext,equatorFieldMapping.getNotEquatorSourceVal()));
                }
                equatorResult.setTargetField(targetFieldName);
                if(StringUtils.isBlank(notEquatorTargetVal)) {
                    equatorResult.setTargetValue(targetVal);
                }else{
                    equatorResult.setTargetValue(invokeExpression(compareContext,equatorFieldMapping.getNotEquatorTargetVal()));
                }
                equatorResult.setFieldName(equatorFieldMapping.getFieldName());
                equatorMaps.add(equatorResult);
            }
        }

        return equatorMaps;
    }

    private Object invokeExpression(ObjectCompareContext objectCompareContext,String expression) {
        Map<String,Object> args = new HashMap<>(2);
        args.put("source",objectCompareContext.getSource());
        args.put("target",objectCompareContext.getTarget());

        final EquatorMethod equatorMethod = objectCompareContext.getEquatorMethod();
        final Map<Integer, String> params = equatorMethod.getParams();
        for (Map.Entry<Integer, String> entry : params.entrySet()) {
            args.put(entry.getValue(),objectCompareContext.getInvokeMethodArgs()[entry.getKey()]);
        }

        return expressionInvoker.invoke(expression, args);
    }
}
