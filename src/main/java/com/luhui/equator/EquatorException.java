package com.luhui.equator;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:52 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class EquatorException extends RuntimeException {

    public EquatorException(String msg){
        super(msg);
    }

    public EquatorException(String msg,Throwable throwable){
        super(msg,throwable);
    }
}
