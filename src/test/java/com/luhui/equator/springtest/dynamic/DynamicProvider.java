package com.luhui.equator.springtest.dynamic;

import com.luhui.equator.Student;
import com.luhui.equator.annotations.IDynamicEquatorProvider;
import com.luhui.equator.compare.mode.ObjectCompareContext;
import com.luhui.equator.map.EquatorFieldMapping;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 根据方法入参动态构建比较的字段
 * @author luhui
 * @version 1.0
 * @date 2019/12/22 20:27
 */
@Component
public class DynamicProvider implements IDynamicEquatorProvider {

    @Override
    public Set<EquatorFieldMapping> provide(ObjectCompareContext objectCompareContext) {
        //获取到了方法入参
        Object[] invokeMethodArgs = objectCompareContext.getInvokeMethodArgs();
        Object source = invokeMethodArgs[0];
        Object target = invokeMethodArgs[1];
        if(source.getClass()==Student.class && target == Student.class){
            //假设传递的都是Student类型
            Set<EquatorFieldMapping> equatorFieldMappings = new HashSet<>();
            EquatorFieldMapping equatorFieldMapping = new EquatorFieldMapping("id","id");
            equatorFieldMappings.add(equatorFieldMapping);
            return equatorFieldMappings;
        }
        return null;
    }
}
