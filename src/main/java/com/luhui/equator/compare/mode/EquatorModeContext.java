package com.luhui.equator.compare.mode;

import com.luhui.equator.EquatorConfiguration;
import com.luhui.equator.EquatorMethod;
import com.luhui.equator.map.EquatorFieldMapping;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 15:24 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@AllArgsConstructor
@Data
public class EquatorModeContext {
    private final Object source;
    private final Object target;
    private final EquatorConfiguration equatorConfiguration;
    private final EquatorFieldMapping equatorFieldMapping;
    private final EquatorMethod equatorMethod;
    private final Object[] invokeMethodArgs;
}
