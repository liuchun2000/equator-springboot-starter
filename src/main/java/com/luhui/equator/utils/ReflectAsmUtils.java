package com.luhui.equator.utils;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 13:56 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class ReflectAsmUtils {

    private static final Map<Class,ConstructorAccess> CONSTRUCTOR_ACCESS_MAP = new IdentityHashMap<>();
    private static final Map<Class,MethodAccess> METHOD_ACCESS_MAP = new IdentityHashMap<>();

    public static <T> ConstructorAccess<T> getConstructorAccess(Class<?> cls){
        ConstructorAccess constructorAccess = CONSTRUCTOR_ACCESS_MAP.get(cls);
        if(constructorAccess!=null){
            return constructorAccess;
        }
        synchronized (cls) {
            constructorAccess = CONSTRUCTOR_ACCESS_MAP.get(cls);
            if(constructorAccess!=null){
                return constructorAccess;
            }
            ConstructorAccess access = ConstructorAccess.get(cls);
            CONSTRUCTOR_ACCESS_MAP.put(cls, access);
            return access;
        }
    }

    public static MethodAccess getMethodAccess(Class<?> cls){
        MethodAccess methodAccess = METHOD_ACCESS_MAP.get(cls);
        if(methodAccess!=null){
            return methodAccess;
        }
        synchronized (cls) {
            methodAccess = METHOD_ACCESS_MAP.get(cls);
            if(methodAccess!=null){
                return methodAccess;
            }
            MethodAccess access = MethodAccess.get(cls);
            METHOD_ACCESS_MAP.put(cls, access);
            return access;
        }
    }
}
