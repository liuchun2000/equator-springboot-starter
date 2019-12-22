package com.luhui.equator;

import com.luhui.equator.compare.ObjectCompare;
import com.luhui.equator.compare.ReflectAsmCompare;
import com.luhui.equator.expression.ExpressionInvoker;
import com.luhui.equator.expression.QLExpressionInvoker;
import com.luhui.equator.map.AnnotationProcessor;
import com.luhui.equator.map.MapProcessor;
import com.luhui.equator.map.ProviderProcessor;
import lombok.Data;
import org.springframework.context.ApplicationContext;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 11:02 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Data
public class  EquatorConfiguration {

    private EquatorRegistry equatorRegistry = new EquatorRegistry(this);
    private ApplicationContext applicationContext;
    private MapProcessor mapProcessor;
    private ObjectCompare objectCompare = new ReflectAsmCompare(this);
    private ExpressionInvoker expressionInvoker = new QLExpressionInvoker(applicationContext);

    public EquatorConfiguration(){
        initMapProcessor();
    }

    private void initMapProcessor() {
        ProviderProcessor providerProcessor = new ProviderProcessor(this);
        AnnotationProcessor annotationProcessor = new AnnotationProcessor(this);
        providerProcessor.setNext(annotationProcessor);
        this.mapProcessor = providerProcessor;
    }
}
