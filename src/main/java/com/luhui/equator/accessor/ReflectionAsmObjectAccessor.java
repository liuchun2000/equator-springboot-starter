package com.luhui.equator.accessor;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.luhui.equator.utils.ReflectAsmUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 17:00 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class ReflectionAsmObjectAccessor implements ObjectAccessor {
    @Override
    public Object getFieldValue(Object obj, String fieldName) {
        final MethodAccess methodAccess = ReflectAsmUtils.getMethodAccess(obj.getClass());
        String getMethodName = "get" + StringUtils.capitalize(fieldName);
        return methodAccess.invoke(obj,getMethodName);
    }
}
