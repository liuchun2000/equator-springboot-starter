package com.luhui.equator.map;

import com.luhui.equator.EquatorConfiguration;
import com.luhui.equator.compare.mode.EquatorType;
import lombok.Setter;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> 参数映射构建者 </p>
 *
 * <pre> Created: 2019/11/27 11:19 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public abstract class MapProcessor {

    @Setter
    private MapProcessor next;

    protected final EquatorConfiguration equatorConfiguration;

    public MapProcessor(EquatorConfiguration equatorConfiguration) {
        this.equatorConfiguration = equatorConfiguration;
    }


    /**
     * 获取对应方法的Map映射
     * @param method
     * @return
     */
    public final EquatorMap build(Method method){
        if(matches(method)){
            final EquatorMap currentMaps = doBuild(method);
            if(currentMaps!=null){
                return currentMaps;
            }
            if(currentMaps==null && next!=null){
                return next.doBuild(method);
            }
        }else{
            if(next!=null){
                return next.doBuild(method);
            }
        }
        return null;
    }

    /**
     * 是否直接执行下个处理器
     * @param method
     * @return
     */
    protected abstract boolean matches(Method method);

    /**
     * 构建参数
     * @param method
     * @return
     */
    protected abstract EquatorMap doBuild(Method method);

    /**
     * 填充默认映射
     * @param sourceType
     * @param targetType
     * @param equatorMap
     * @param excludes
     */
    protected void buildByDefaultField(Class sourceType,Class targetType,EquatorMap equatorMap,String[] excludes){
        //对相同字段生成对象映射
        final List<String> targetFieldNames = FieldUtils.getAllFieldsList(targetType).stream()
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .map(field -> field.getName())
                .collect(Collectors.toList());

        final Set<EquatorFieldMapping> fieldMappings = equatorMap.getEquatorFieldMappings();
        final List<String> sourceList = fieldMappings.stream().map(mapping -> mapping.getSource()).collect(Collectors.toList());
        final List<Field> sourceFieldList = FieldUtils.getAllFieldsList(sourceType).stream()
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> !sourceList.contains(field.getName()))
                .filter(field -> targetFieldNames.contains(field.getName()))
                .filter(field -> !Arrays.asList(excludes).contains(field.getName()))
                .collect(Collectors.toList());

        for (Field field : sourceFieldList) {
            EquatorFieldMapping equatorFieldMapping = new EquatorFieldMapping(field.getName(),field.getName(),
                    EquatorType.DEFAULT,null, null);
            fieldMappings.add(equatorFieldMapping);
        }
    }

}
