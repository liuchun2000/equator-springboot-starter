package com.luhui.equator.map;

import com.luhui.equator.compare.mode.EquatorType;
import lombok.Data;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 18:56 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Data
public class EquatorFieldMapping {

    private final String source;
    private final String target;
    private final EquatorType equatorType;
    private final String expression;
    private final String fieldName;
    private final String notEquatorSourceVal;
    private final String notEquatorTargetVal;


    public EquatorFieldMapping(String source, String target, EquatorType equatorType, String expression, String fieldName, String notEquatorSourceVal,String notEquatorTargetVal) {
        this.source = source;
        this.target = target;
        this.equatorType = equatorType;
        this.expression = expression;
        this.fieldName = fieldName;
        this.notEquatorSourceVal = notEquatorSourceVal;
        this.notEquatorTargetVal = notEquatorTargetVal;

    }

    public EquatorFieldMapping(String source, String target, EquatorType equatorType, String expression, String fieldName) {
        this(source, target, equatorType,expression,fieldName,null,null);
    }

    public EquatorFieldMapping(String source, String target, EquatorType equatorType) {
        this(source,target,equatorType,null, null);
    }

    public EquatorFieldMapping(String source, String target) {
        this(source,target,EquatorType.DEFAULT,null, null);
    }
}
