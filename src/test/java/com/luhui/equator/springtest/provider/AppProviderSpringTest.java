package com.luhui.equator.springtest.provider;

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
 * <pre> Created: 2019/12/5 15:54 </pre>
 *
 * @author hlu
 * @version 1.0
 */
@SpringBootTest(classes= SpringBootApp.class)
@RunWith(SpringRunner.class)
public class AppProviderSpringTest {

    @Autowired
    private StudentProviderSpringEquator studentProviderSpringEquator;

    @Test
    public void providerTest1(){
        Student stu1 = new Student(1,"小明");
        Student stu2 = new Student(2,"小红");

        final List<EquatorResult> results = studentProviderSpringEquator.provider1(stu1, stu2);
        assert results.size()==1;
    }
}
