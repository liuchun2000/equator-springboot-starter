package com.luhui.equator.springtest.annotation;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.SpringBootApp;
import com.luhui.equator.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>  </p>
 *
 * <pre> Created: 2019/12/3 19:35 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@SpringBootTest(classes= SpringBootApp.class)
@RunWith(SpringRunner.class)
public class AppAnnotationSpringTest {

    @Autowired
    private StudentAnnotationSpringEquator studentEquator;

    /**
     * 无任何配置，默认比较方式，只比较相同属性
     */
    @Test
    public void defaultTest(){
        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");
        final List<EquatorResult> equator = studentEquator.defaultEquator(stu1, stu2);
        assert equator.size()==2;
    }

    @Test
    public void annotationTest(){
        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");
        final List<EquatorResult> equator = studentEquator.annotationEquator1(stu1, stu2);
        assert equator.size()==1;
    }

    @Test
    public void annotation2Test(){
        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");
        final List<EquatorResult> equator = studentEquator.annotationEquator2(stu1, stu2);
        assert equator.size()==2;
    }

    @Test
    public void annotation3Test(){
        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");
        final List<EquatorResult> equator = studentEquator.annotationEquator3(stu1, stu2);
        assert equator.size()==1;
    }

    @Test
    public void annotation4Test(){
        Student stu1 = new Student(5,"小明");
        Student stu2 = new Student(2,"小红");
        final List<EquatorResult> equator = studentEquator.annotationEquator4(stu1, stu2);
        assert equator.size()==0;

        final List<EquatorResult> equator2 = studentEquator.annotationEquator4(stu2, stu1);
        assert equator2.size()==1;
    }

    @Test
    public void annotation5Test(){
        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");
        final List<EquatorResult> equator = studentEquator.annotationEquator5(stu1, stu2,true);
        assert equator.size()==0;

        final List<EquatorResult> equator2 = studentEquator.annotationEquator5(stu1, stu2,false);
        assert equator2.size()==1;
    }
}
