package com.luhui.equator.compare;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.compare.mode.ObjectCompareContext;

import java.util.List;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/4 13:36 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public interface ObjectCompare {

    /**
     * 比较现个对象
     * @param compareContext
     * @param <T>
     * @return
     */
    <T extends EquatorResult> List<T> compare(ObjectCompareContext compareContext);
}
