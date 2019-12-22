package com.luhui.equator;

import lombok.RequiredArgsConstructor;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:49 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@RequiredArgsConstructor
public class EquatorRegistry {

    private final Map<Class<?>,EquatorProxyFactory> knownEquators = new IdentityHashMap<>();
    private final EquatorConfiguration configuration;


    public <T> T getEquator(Class<T> type){
        final EquatorProxyFactory<T> equatorProxyFactory = knownEquators.get(type);
        if(equatorProxyFactory==null){
            throw new EquatorException("Type "+ type +" is not known to the EquatorRegistry.");
        }
        return equatorProxyFactory.newInstance();
    }

    public <T> boolean hasEquator(Class<T> type){
        return knownEquators.containsKey(type);
    }

    public <T> void addEquator(Class<T> type){
        if(type.isInterface()){
            if(hasEquator(type)){
                throw new EquatorException("Type " + type + " is already known to the EquatorRegistry.");
            }
        }
        knownEquators.put(type,new EquatorProxyFactory(type,configuration));
    }
}
