package com.luhui.equator.map;

import com.luhui.equator.EquatorConfiguration;
import com.luhui.equator.annotations.EquatorProvider;
import com.luhui.equator.annotations.IEquatorProvider;
import com.luhui.equator.utils.ReflectAsmUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * <p> 提供者处理器 </p>
 *
 * <pre> Created: 2019/11/27 13:59 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class ProviderProcessor extends MapProcessor{

    public ProviderProcessor(EquatorConfiguration equatorConfiguration) {
        super(equatorConfiguration);
    }

    @Override
    protected boolean matches(Method method) {
        return AnnotationUtils.findAnnotation(method, EquatorProvider.class)!=null;
    }

    @Override
    protected EquatorMap doBuild(Method method) {
        final EquatorProvider equatorProvider = AnnotatedElementUtils.findMergedAnnotation(method, EquatorProvider.class);
        final Class<? extends IEquatorProvider> providerClass = equatorProvider.provider();
        final ApplicationContext applicationContext = equatorConfiguration.getApplicationContext();
        final IEquatorProvider provider;
        if(applicationContext!=null){
            provider = applicationContext.getBean(providerClass);
        }else{
            provider = ReflectAsmUtils.<IEquatorProvider>getConstructorAccess(providerClass).newInstance();
        }
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final EquatorMap equatorMap = new EquatorMap(parameterTypes[0],parameterTypes[1]);
        equatorMap.setEquatorFieldMappings(provider.provide(method));
        if(provider.byDefault(method)){
            buildByDefaultField(parameterTypes[0],parameterTypes[1],equatorMap,provider.excludes(method));
        }
        return equatorMap;
    }
}
