package com.luhui.equator.annotations;

import com.luhui.equator.map.EquatorFieldMapping;

import java.lang.reflect.Method;
import java.util.Set;


/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 13:23 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public interface IEquatorProvider {

    /**
     * 构建map对象
     * @return
     */
    Set<EquatorFieldMapping> provide(Method method);

    /**
     * 对于没有写的相同的属性名，是否自动映射，true映射，false不映射
     * @return
     */
    default boolean byDefault(Method method){
        return true;
    }

    /**
     * 排除要比较的字段，仅 byDefault=true时生效
     * @return
     */
    default String[] excludes(Method method){
        return new String[0];
    }
}
