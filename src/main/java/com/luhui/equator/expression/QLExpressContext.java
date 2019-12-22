package com.luhui.equator.expression;

import com.ql.util.express.IExpressContext;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 11:07 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class QLExpressContext implements IExpressContext<String, Object> {

    private final Map<String,Object> context;

    private ApplicationContext applicationContext;

    public QLExpressContext(){
        this(new HashMap<>());
    }

    public QLExpressContext(Map<String,Object> params){
        if(params==null){
            this.context = new HashMap<>();
        }else {
            this.context = params;
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object get(Object key) {
        final Object obj = context.get(key);
        if(obj==null){
            //先从获取外围传入的参数，如果参数找不到，就去spring容器中根据bean名称去找这个bean
            if(key instanceof String) {
                try {
                    return applicationContext.getBean((String) key);
                } catch (Exception e) {
                    return null;
                }
            }
        }else{
            return obj;

        }
        return null;
    }

    @Override
    public Object put(String name, Object object) {
        return context.put(name,object);
    }





}
