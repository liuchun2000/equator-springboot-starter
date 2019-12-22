package com.luhui.equator.springtest.dynamic;

import com.luhui.equator.EquatorResult;
import com.luhui.equator.SpringBootApp;
import com.luhui.equator.Student;
import com.luhui.equator.springtest.annotation.StudentAnnotationSpringEquator;
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
public class AppDynamicSpringTest {

    @Autowired
    private DynamicEquator dynamicEquator;

    @Test
    public void test(){
        Student student = new Student(1,"小明");
        Student student2 = new Student(2,"小明");
        assert dynamicEquator.equator(student,student2).size()==1;
    }
}
