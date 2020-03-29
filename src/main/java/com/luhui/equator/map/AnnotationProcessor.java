package com.luhui.equator.map;

import com.luhui.equator.EquatorConfiguration;
import com.luhui.equator.annotations.EquatorMapping;
import com.luhui.equator.annotations.EquatorMappings;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> 通过注解指定映射方式的处理器  </p>
 *
 * <pre> Created: 2019/11/27 14:06 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class AnnotationProcessor extends MapProcessor {

    public AnnotationProcessor(EquatorConfiguration equatorConfiguration) {
        super(equatorConfiguration);
    }

    @Override
    protected boolean matches(Method method) {
        return true;
    }

    @Override
    protected EquatorMap doBuild(Method method) {
        EquatorMappings equatorMappings = AnnotationUtils.findAnnotation(method, EquatorMappings.class);
        if (equatorMappings == null) {
            equatorMappings = EquatorMappings.DEFAULT_MAPPINGS;
        }
        final EquatorMapping[] mappings = equatorMappings.value();

        final Set<EquatorFieldMapping> fieldMappings = Arrays.stream(mappings)
                .map(mapping ->
                        new EquatorFieldMapping(mapping.source(), mapping.target(),
                                mapping.equalsType(), mapping.expression(), mapping.fieldName(),
                                mapping.notEquatorSourceVal(),mapping.notEquatorTargetVal()))
                .collect(Collectors.toSet());
        final Class<?>[] parameterTypes = method.getParameterTypes();
        EquatorMap equatorMap = new EquatorMap(parameterTypes[0], parameterTypes[1]);
        equatorMap.setEquatorFieldMappings(fieldMappings);

        if (equatorMappings.byDefault()) {
            buildByDefaultField(parameterTypes[0],parameterTypes[1],equatorMap,equatorMappings.excludes());
        }
        return equatorMap;
    }
}
