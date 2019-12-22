package com.luhui.equator.spring;

import com.luhui.equator.EquatorConfiguration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/3 18:52 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class EquatorFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private Class<T> mapperInterface;
    private EquatorConfiguration equatorConfiguration;


    public EquatorFactoryBean() {
        this.mapperInterface = mapperInterface;
    }

    public EquatorConfiguration getEquatorConfiguration() {
        return equatorConfiguration;
    }

    public void setEquatorConfiguration(EquatorConfiguration equatorConfiguration) {
        this.equatorConfiguration = equatorConfiguration;
    }

    public EquatorFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return equatorConfiguration.getEquatorRegistry().getEquator(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        equatorConfiguration.getEquatorRegistry().addEquator(mapperInterface);
    }
}
