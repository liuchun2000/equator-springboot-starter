package com.luhui.equator;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Proxy;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:47 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@RequiredArgsConstructor
public class EquatorProxyFactory<T> {

    private final Class<T> equatorInterface;
    private final EquatorConfiguration configuration;

    public T newInstance() {
        final EquatorProxy equatorProxy = new EquatorProxy(equatorInterface,configuration);
        return (T) Proxy.newProxyInstance(equatorInterface.getClassLoader(), new Class[] { equatorInterface }, equatorProxy);
    }
}
