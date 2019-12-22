package com.luhui.equator.spring;

import com.luhui.equator.compare.mode.impl.AddressEquatorMode;
import com.luhui.equator.compare.mode.impl.DefaultEquatorMode;
import com.luhui.equator.compare.mode.impl.ExpressionEquatorMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/3 19:40 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Configuration
public class EquatorAutoConfiguration {

    @Bean
    public EquatorScannerConfigurer equatorScannerConfigurer(){
        return new EquatorScannerConfigurer();
    }

    @Bean
    public AddressEquatorMode addressEquatorMode(){
        return new AddressEquatorMode();
    }

    @Bean
    public DefaultEquatorMode defaultEquatorMode(){
        return new DefaultEquatorMode();
    }

    @Bean
    public ExpressionEquatorMode expressionEquatorMode(){
        return new ExpressionEquatorMode();
    }
}
