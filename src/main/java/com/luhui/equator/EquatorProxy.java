package com.luhui.equator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:42 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@RequiredArgsConstructor
@Getter
public class EquatorProxy implements InvocationHandler {

    private final Class equatorInterface;
    private final EquatorConfiguration configuration;
    private final Map<Method,EquatorMethod> methodCache = new IdentityHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            //the caller calls an Object method, such as toString(),hashCode()
            return method.invoke(this, args);
        } else if (isDefaultMethod(method)) {
            //the method is default method
            return invokeDefaultMethod(proxy, method, args);
        }
        final EquatorMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(args);
    }

    private EquatorMethod cachedMapperMethod(Method method) {
        return methodCache.computeIfAbsent(method, k -> new EquatorMethod(equatorInterface, method, configuration));
    }

    private boolean isDefaultMethod(Method method) {
        return (method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
                && method.getDeclaringClass().isInterface();
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor
                .newInstance(declaringClass,
                        MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }
}
