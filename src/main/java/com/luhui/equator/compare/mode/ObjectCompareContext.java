package com.luhui.equator.compare.mode;

import com.luhui.equator.EquatorMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 15:34 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@AllArgsConstructor
@Data
public class ObjectCompareContext {

    private final Object source;
    private final Object target;
    private final EquatorMethod equatorMethod;
    private final Object[] invokeMethodArgs;
}
