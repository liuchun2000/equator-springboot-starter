package com.luhui.equator.compare.mode.impl;

import com.luhui.equator.compare.mode.EquatorMode;
import com.luhui.equator.compare.mode.EquatorModeContext;

import java.math.BigDecimal;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 17:11 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class DefaultEquatorMode implements EquatorMode {
    @Override
    public boolean equatorMode(EquatorModeContext equatorModeContext) {
        Object source = equatorModeContext.getSource();
        Object target = equatorModeContext.getTarget();
        if(source==null && target==null){
            return true;
        }
        if(source!=null){
            if(source.equals(target)){
                return true;
            }
        }
        if(target!=null){
            if(target.equals(source)){
                return true;
            }
        }
        if(source instanceof BigDecimal && target instanceof BigDecimal){
            if(((BigDecimal)source).compareTo((BigDecimal)target)==0){
                return true;
            }
        }
        return false;
    }
}
