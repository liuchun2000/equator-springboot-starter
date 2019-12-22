package com.luhui.equator.springtest.annotation;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.Student;
import com.luhui.equator.annotations.EquatorMapping;
import com.luhui.equator.annotations.EquatorMappings;
import com.luhui.equator.annotations.EquatorParam;
import com.luhui.equator.compare.mode.EquatorType;
import com.luhui.equator.spring.Equator;

import java.util.List;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/27 10:29 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@Equator
public interface StudentAnnotationSpringEquator {

    /**
     * 不添加任何配置，对比所有相同属性的字段
     * @param student1
     * @param student2
     * @return
     */
    List<EquatorResult> defaultEquator(Student student1, Student student2);

    /**
     * 注解方式仅比较id属性
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(value = {
            @EquatorMapping(source = "id",target = "id")
    },byDefault = false)
    List<EquatorResult> annotationEquator1(Student student1, Student student2);


    /**
     * 注解方式，对没有配置的字段自动填充相同属性的映射
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(value = {
            @EquatorMapping(source = "id",target = "id")
    })
    List<EquatorResult> annotationEquator2(Student student1, Student student2);

    /**
     * 注解方式，走默认配置，但排队部除字段自动配置
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(excludes = "name")
    List<EquatorResult> annotationEquator3(Student student1, Student student2);


    /**
     * 注解方式，通过表达式比较两个字段是否相等
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(value = {
            @EquatorMapping(source = "id",
                    target = "id",
                    equalsType = EquatorType.EXPRESSION,
                    expression = "stu1.id==5")
    },byDefault = false)
    List<EquatorResult> annotationEquator4(@EquatorParam("stu1") Student student1, Student student2);


    /**
     * 注解方式，通过表达式比较两个字段是否相等,表达式中可以使用springBean，并且可以传递这个方法所需要传递的其他参数
     * 注意：方法签名参数可以定义多于两个参数，但前两个参数必须是被比较的两个对象，从第三个参数开始不做限制
     * @param student1
     * @param student2
     * @return
     */
    @EquatorMappings(value = {
            @EquatorMapping(source = "id",
                    target = "id",
                    equalsType = EquatorType.EXPRESSION,
                    expression = "beanTest.test1(student1,student2,test)")
    },byDefault = false)
    List<EquatorResult> annotationEquator5(@EquatorParam("student1") Student student1,
                                           @EquatorParam("student2") Student student2,
                                           @EquatorParam("test") boolean test);
}
