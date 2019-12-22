package com.luhui.equator.test;

import com.luhui.equator.EquatorConfiguration;
import com.luhui.equator.EquatorRegistry;
import com.luhui.equator.EquatorResult;
import com.luhui.equator.Student;

import java.util.List;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/11/28 10:12 </pre>
 *
 * @author hlu
 * @version 1.0
 */
public class MainTest {

    public static void main(String[] args) {
        EquatorConfiguration equatorConfiguration = new EquatorConfiguration();
        final EquatorRegistry equatorRegistry = equatorConfiguration.getEquatorRegistry();
        equatorRegistry.addEquator(StudentEquator.class);
        final StudentEquator studentEquator = equatorRegistry.getEquator(StudentEquator.class);

        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");

        final List<EquatorResult> results = studentEquator.equator(stu1, stu2);
        System.out.println(results);
    }
}
