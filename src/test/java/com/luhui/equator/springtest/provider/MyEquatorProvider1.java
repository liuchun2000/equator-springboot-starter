package com.luhui.equator.springtest.provider;

import com.luhui.equator.annotations.IEquatorProvider;
import com.luhui.equator.map.EquatorFieldMapping;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 16:46 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Component
public class MyEquatorProvider1 implements IEquatorProvider {
    @Override
    public Set<EquatorFieldMapping> provide(Method method) {
        Set<EquatorFieldMapping> equatorFieldMappings = new HashSet<>();
        equatorFieldMappings.add(new EquatorFieldMapping("id","id"));
        return equatorFieldMappings;
    }

    @Override
    public boolean byDefault(Method method) {
        return false;
    }
}
