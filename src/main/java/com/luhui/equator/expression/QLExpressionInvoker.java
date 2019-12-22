package com.luhui.equator.expression;

import com.luhui.equator.EquatorException;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/5 11:24 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class QLExpressionInvoker implements ExpressionInvoker {

    private final ExpressRunner runner = new ExpressRunner();
    private ApplicationContext applicationContext;


    public QLExpressionInvoker(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private QLExpressionInvoker(){

    }

    public static final QLExpressionInvoker INSTANCE = new QLExpressionInvoker();


    @Override
    public Object invoke(String expression, Map<String, Object> args) {
        IExpressContext<String,Object> expressContext = new QLExpressContext(args);
        ((QLExpressContext) expressContext).setApplicationContext(applicationContext);
        try {
            return runner.execute(expression,expressContext, null, true, false);
        } catch (Exception e) {
            throw new EquatorException("表达式执行出错！",e);
        }
    }



}
