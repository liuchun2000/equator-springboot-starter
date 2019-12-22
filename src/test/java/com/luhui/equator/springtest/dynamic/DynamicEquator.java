package com.luhui.equator.springtest.dynamic;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.spring.Equator;

import java.util.List;

/**
 * 这里使用一个动态构造器，会每次去构造这个对象，这个形式不会缓存，因此性能会比其他方式低，不过也一种实现思路
 * @author luhui
 * @version 1.0
 * @date 2019/12/22 20:25
 */
@Equator
public interface DynamicEquator {

    /**
     * 这里演示一个，假设你写的很灵活,你写了个通用的方法
     * @param obj1
     * @param obj2
     * @return
     */

    List<EquatorResult> equator(Object obj1,Object obj2);
}
