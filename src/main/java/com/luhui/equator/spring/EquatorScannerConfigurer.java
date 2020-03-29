package com.luhui.equator.spring;

import com.luhui.equator.EquatorConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.util.Assert.notNull;


/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/3 19:15 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class EquatorScannerConfigurer implements BeanDefinitionRegistryPostProcessor,
        ApplicationContextAware, ResourceLoaderAware, BeanFactoryAware {

    private ResourceLoader resourceLoader;
    private BeanFactory beanFactory;
    private EquatorConfiguration equatorConfiguration = new EquatorConfiguration();



    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
        EquatorMapperScanner scanner = new EquatorMapperScanner(registry,equatorConfiguration);
        if (this.resourceLoader != null) {
            scanner.setResourceLoader(this.resourceLoader);
        }
        scanner.setAnnotationClass(Equator.class);
        scanner.registerFilters();
        scanner.doScan(StringUtils.toStringArray(packages));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        equatorConfiguration.setApplicationContext(applicationContext);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public EquatorConfiguration getEquatorConfiguration() {
        return equatorConfiguration;
    }
}
