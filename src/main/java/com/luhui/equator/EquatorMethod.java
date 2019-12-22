package com.luhui.equator;

import com.luhui.equator.annotations.EquatorParam;
import com.luhui.equator.compare.mode.ObjectCompareContext;
import com.luhui.equator.map.EquatorMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 11:13 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Slf4j
@Getter
public class EquatorMethod {

    private final Class equatorInterface;
    private final Method method;
    private final EquatorConfiguration configuration;
    private EquatorMap equatorMap;
    private Map<Integer,String> params = new HashMap<>();

    public EquatorMethod(Class equatorInterface, Method method, EquatorConfiguration configuration) {
        this.equatorInterface = equatorInterface;
        this.method = method;
        this.configuration = configuration;
        final int parameterCount = method.getParameterCount();
        if(parameterCount<2){
            throw new EquatorException("方法定义不合法，至少要有两个参数！");
        }

        for (int i = 0; i < method.getParameterAnnotations().length; i++) {
            final int index = i;
            final Annotation[] parameterAnnotation = method.getParameterAnnotations()[i];
            Arrays.stream(parameterAnnotation)
                    .filter(anno -> anno.annotationType() == EquatorParam.class)
                    .findFirst()
                    .map(anno-> params.put(index,((EquatorParam)anno).value()));
        }
    }


    public Object execute(Object[] args) {

        if(equatorMap == null){
            equatorMap = configuration.getMapProcessor().build(this.method);
            if(equatorMap==null){
                throw new EquatorException("未找到比较配置！");
            }
        }

        Object source = args[0];
        Object target = args[1];
        return configuration.getObjectCompare().compare(new ObjectCompareContext(source,target,this,args));
    }




}
