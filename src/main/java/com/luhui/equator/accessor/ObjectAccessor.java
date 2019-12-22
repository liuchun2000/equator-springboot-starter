package com.luhui.equator.accessor;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 13:29 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public interface ObjectAccessor {

    /**
     * 获取指定对象的指令字段值
     * @param obj
     * @param fieldName
     * @return
     */
    Object getFieldValue(Object obj,String fieldName);
}
