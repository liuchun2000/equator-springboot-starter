package com.luhui.equator.compare.mode.impl;

import com.luhui.equator.compare.mode.EquatorMode;
import com.luhui.equator.compare.mode.EquatorModeContext;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 17:10 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class AddressEquatorMode implements EquatorMode {

    @Override
    public boolean equatorMode(EquatorModeContext equatorModeContext) {
        return equatorModeContext.getSource() == equatorModeContext.getTarget();
    }
}
