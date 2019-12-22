package com.luhui.equator.annotations;

import com.luhui.equator.compare.mode.ObjectCompareContext;
import com.luhui.equator.map.EquatorFieldMapping;

import java.util.Set;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/16 18:21 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public interface IDynamicEquatorProvider {

    /**
     * 构建map对象
     * @return
     */
    Set<EquatorFieldMapping> provide(ObjectCompareContext objectCompareContext);
}
