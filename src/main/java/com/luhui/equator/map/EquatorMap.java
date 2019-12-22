package com.luhui.equator.map;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:17 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@RequiredArgsConstructor
@Data
public final class EquatorMap {

    private final Class<?> sourceType;
    private final Class<?> targetType;

    private Set<EquatorFieldMapping> equatorFieldMappings;



}
