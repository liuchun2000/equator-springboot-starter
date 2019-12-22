package com.luhui.equator;

import lombok.Data;

/**
 * <p>
 *     对象比较结果集对象，对于程序员定义的Equator接口返回值而言，返回的类型必须是一个List，泛型为EquatorResult或EquatorResult子类的约束
 *
 * example 1   使用注解形式映射对象映射关系：
 * public interface ObjectEquator {
 *     @EquatorMappings(value = {
 *             @EquatorMapping(source = "id", target = "id"),
 *             @EquatorMapping(source = "name", target = "name")
 *         }
 *     )
 *     List<EquatorResult> equator(Object obj1, Object obj2);
 * }
 *
 * example 2   使用代码形式配置映射对象映射关系(如果需要从数据库读取配置请使用此种方式):
 * public interface ObjectEquator {
 *
 *     @EquatorProvider(provider = MyProvider.class)
 *     List<MyEquatorResult> equator(Object obj1, Object obj2);
 * }
 *
 * </p>
 *
 * <pre> Created: 2019/11/27 10:30 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Data
public class EquatorResult {

   private String sourceField;
   private Object sourceValue;
   private String targetField;
   private Object targetValue;
   private String fieldName;

}
